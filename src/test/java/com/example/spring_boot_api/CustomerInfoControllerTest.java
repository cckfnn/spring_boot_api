package com.example.spring_boot_api;

import com.example.spring_boot_api.dto.CustomerDataDto;
import com.example.spring_boot_api.dto.CustomerDto;
import com.example.spring_boot_api.dto.HoldItemDto;
import com.example.spring_boot_api.exceptions.ExceptionDetails;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerInfoControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private ObjectMapper mapper;

    private CustomerDataDto customerDataForMockServer;

    private HoldItemDto holdItemForMockServer;

    private void addMockEndpoint(String uri, Object mockResponse) throws URISyntaxException, JsonProcessingException {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(uri)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(mockResponse))
                );
        System.out.println(mapper.writeValueAsString(mockResponse));
    }

    private void addMockEndpointWithHttpStatus(String uri, HttpStatus httpStatus) throws URISyntaxException {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(uri)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body("")
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

    @Test
    public void testCustomerInfoController_200_OK() throws URISyntaxException, JsonProcessingException {

        // arrange
        addMockEndpoint("https://localhost:8082/customers/1", customerDataForMockServer);
        addMockEndpoint("https://localhost:8081/customers/1/holds", holdItemForMockServer);

        // act
        CustomerDto actualCustomerDto = testRestTemplate.getForObject("http://localhost:8080/customers-info/1", CustomerDto.class);

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

    @Test
    public void testCustomerInfoController_CustomerDataServiceFault() throws URISyntaxException, JsonProcessingException {

        // arrange
        addMockEndpointWithHttpStatus("https://localhost:8082/customers/1", HttpStatus.INTERNAL_SERVER_ERROR);
        addMockEndpoint("https://localhost:8081/customers/1/holds", holdItemForMockServer);

        // act
//        ExceptionDetails exceptionDetails = testRestTemplate.getForObject("http://localhost:8080/customers-info/1", ExceptionDetails.class);
        String exceptionDetails = testRestTemplate.getForObject("http://localhost:8080/customers-info/1", String.class);
        System.out.println(exceptionDetails);
        // assert
//        assertEquals("500 Internal Server Error: [no body]", exceptionDetails.getMessage());
//        assertEquals("uri=/customers-info/1", exceptionDetails.getDetails());
    }
}
