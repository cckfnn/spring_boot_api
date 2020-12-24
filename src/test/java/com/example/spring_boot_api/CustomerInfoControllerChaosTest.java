package com.example.spring_boot_api;

import com.example.spring_boot_api.dto.CustomerDataDto;
import com.example.spring_boot_api.dto.CustomerDto;
import com.example.spring_boot_api.dto.HoldItemDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"chaos-monkey", "chaos-delay"})
public class CustomerInfoControllerChaosTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private ObjectMapper mapper;

    private CustomerDataDto customerDataForMockServer;

    private HoldItemDto holdItemForMockServer;


    private void addMockEndpointWithHttpStatus(String uri, HttpStatus httpStatus, Object mockResponse) throws URISyntaxException, JsonProcessingException {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(uri)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(httpStatus)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(mockResponse))
                );
    }

    @Before
    public void init() {

        customerDataForMockServer = CustomerDataDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .isCorporateClient(true)
                .serviceLevel(2)
                .build();

        holdItemForMockServer = HoldItemDto.builder()
                .id(1L)
                .GUIDRegister("GUIDRegister")
                .GUIDExternal("GUIDExternal")
                .GUIDRequest("GUIDRequest")
                .needReservation(true)
                .registerType("registerType")
                .summa(new BigDecimal(5000))
                .currency("currency")
                .priority(1)
                .dateVal("dateVal")
                .build();

    }

    @Test(timeout = 8000)
    public void testCustomerInfoController_delay() throws URISyntaxException, JsonProcessingException {

        addMockEndpointWithHttpStatus("https://localhost:8082/customers/1", HttpStatus.OK, customerDataForMockServer);
        addMockEndpointWithHttpStatus("https://localhost:8081/customers/1/holds", HttpStatus.OK, holdItemForMockServer);

        ResponseEntity<CustomerDto> response = testRestTemplate.getForEntity("http://localhost:8080/customers-info/1", CustomerDto.class);

        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        CustomerDto actualCustomerDto = response.getBody();

        // assert
        assertEquals(Long.valueOf(1L), actualCustomerDto.getCustomerDataDto().getId());
        assertEquals("John", actualCustomerDto.getCustomerDataDto().getFirstName());
        assertEquals("Doe", actualCustomerDto.getCustomerDataDto().getLastName());
        assertTrue(actualCustomerDto.getCustomerDataDto().getIsCorporateClient());
        assertEquals(Integer.valueOf(2), actualCustomerDto.getCustomerDataDto().getServiceLevel());

        assertEquals(Long.valueOf(1L), actualCustomerDto.getHoldItemDto().getId());
        assertEquals("GUIDRegister", actualCustomerDto.getHoldItemDto().getGUIDRegister());
        assertEquals("GUIDExternal", actualCustomerDto.getHoldItemDto().getGUIDExternal());
        assertEquals("GUIDRequest", actualCustomerDto.getHoldItemDto().getGUIDRequest());

    }

    @Test(timeout = 4050)
    public void testCustomerInfoController_delayAsync() throws URISyntaxException, JsonProcessingException {

        addMockEndpointWithHttpStatus("https://localhost:8082/customers/1", HttpStatus.OK, customerDataForMockServer);
        addMockEndpointWithHttpStatus("https://localhost:8081/customers/1/holds", HttpStatus.OK, holdItemForMockServer);

        ResponseEntity<CustomerDto> response = testRestTemplate.getForEntity("http://localhost:8080/customers-info-async/1", CustomerDto.class);

        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        CustomerDto actualCustomerDto = response.getBody();

        // assert
        assertEquals(Long.valueOf(1L), actualCustomerDto.getCustomerDataDto().getId());
        assertEquals("John", actualCustomerDto.getCustomerDataDto().getFirstName());
        assertEquals("Doe", actualCustomerDto.getCustomerDataDto().getLastName());
        assertTrue(actualCustomerDto.getCustomerDataDto().getIsCorporateClient());
        assertEquals(Integer.valueOf(2), actualCustomerDto.getCustomerDataDto().getServiceLevel());

        assertEquals(Long.valueOf(1L), actualCustomerDto.getHoldItemDto().getId());
        assertEquals("GUIDRegister", actualCustomerDto.getHoldItemDto().getGUIDRegister());
        assertEquals("GUIDExternal", actualCustomerDto.getHoldItemDto().getGUIDExternal());
        assertEquals("GUIDRequest", actualCustomerDto.getHoldItemDto().getGUIDRequest());
    }

    @Test(timeout = 4050)
    public void testCustomerInfoController_delayWithHystryx() throws URISyntaxException, JsonProcessingException {

        addMockEndpointWithHttpStatus("https://localhost:8082/customers/1", HttpStatus.OK, customerDataForMockServer);
        addMockEndpointWithHttpStatus("https://localhost:8081/customers/1/holds", HttpStatus.INTERNAL_SERVER_ERROR, null);
//        addMockEndpointWithHttpStatus("https://localhost:8081/customers/1/holds", HttpStatus.OK, holdItemForMockServer);

        ResponseEntity<CustomerDto> response = testRestTemplate.getForEntity("http://localhost:8080/customers-info/1", CustomerDto.class);

        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        CustomerDto actualCustomerDto = response.getBody();

        // assert

        assertEquals(Long.valueOf(1L), actualCustomerDto.getCustomerDataDto().getId());
        assertEquals("John", actualCustomerDto.getCustomerDataDto().getFirstName());
        assertEquals("Doe", actualCustomerDto.getCustomerDataDto().getLastName());
        assertTrue(actualCustomerDto.getCustomerDataDto().getIsCorporateClient());
        assertEquals(Integer.valueOf(2), actualCustomerDto.getCustomerDataDto().getServiceLevel());

        assertEquals("hystryxGuidReg", actualCustomerDto.getHoldItemDto().getGUIDRegister());
        assertEquals(Long.valueOf(1L), actualCustomerDto.getHoldItemDto().getId());
        assertEquals("hystryxGuidEx", actualCustomerDto.getHoldItemDto().getGUIDExternal());
        assertEquals("hystryxRequest", actualCustomerDto.getHoldItemDto().getGUIDRequest());
    }
}
