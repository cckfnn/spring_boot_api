package com.example.spring_boot_api;

import com.example.spring_boot_api.dto.CustomerDto;
import com.example.spring_boot_api.dto.HoldItemDto;
import com.example.spring_boot_api.services.HoldItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerInfoControllerTestHystryx {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testHoldItemServiceHystryx() {
        HoldItemDto holdItemDto = testRestTemplate.getForObject("http://localhost:8080/holds/1", HoldItemDto.class);
        assertEquals("hystryxDateval", holdItemDto.getDateVal());
    }

}
