package com.example.spring_boot_api;

import com.example.spring_boot_api.controllers.PaymentOrderController;
import com.example.spring_boot_api.entities.PaymentOrderEntity;
import com.example.spring_boot_api.repositories.PaymentOrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentOrderEntityControllerTest {

    private MockMvc mockMvc;
    private String xmlString;

    @Autowired
    private PaymentOrderController paymentOrderController;
    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    @Test
    public void contextLoads() {
        assertThat(paymentOrderController).isNotNull();
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(paymentOrderController).build();
        xmlString = Utils.loadConfigAsString("test.xml");
    }


    @Test
    public void testControllerPost() throws Exception {
        mockMvc.perform(
                post("/payment-orders")
                        .content(xmlString)
                        .contentType(MediaType.APPLICATION_XML)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(xpath("/PutLegalPaymentOrderRq/PayerAccountInfoBankInfoVSPNum").string("38903801775"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/docRef").string("78465654673534"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/OperationType").string("3"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/cmsBase64").string("srEdsfsdvs534sdfg54gfgdf"))

        ;

        List<PaymentOrderEntity> entityList = paymentOrderRepository.findByDocRef("78465654673534");

        assertEquals(1, entityList.size());
        assertEquals("38903801775", entityList.get(0).getPayerAccountInfoBankInfoVSPNum());
        assertEquals("78465654673534", entityList.get(0).getDocRef());
        assertEquals("3", entityList.get(0).getOperationType());
        assertEquals("srEdsfsdvs534sdfg54gfgdf", entityList.get(0).getCmsBase64());
    }

}
