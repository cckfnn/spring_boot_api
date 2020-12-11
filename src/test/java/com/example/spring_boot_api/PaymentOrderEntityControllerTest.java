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

import java.math.BigDecimal;
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
        xmlString = Utils.loadConfigAsString();
    }


    @Test
    public void testControllerPost() throws Exception {
        mockMvc.perform(
                post("/payment-orders")
                        .content(xmlString)
                        .contentType(MediaType.APPLICATION_XML)
        ).andDo(print())
                .andExpect(status().isCreated())

//                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/").nodeCount(1))

                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/transKind").string("01"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PaymentOrderNumber").string("000061"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/Date").string("2020-11-06+03:00"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PaymentSum").string("456345.13"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/CommissionSum").string("7685.56"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayerData/Name").string("OOO Shuba-Duba"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayerData/Account").string("40702810038001213108"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayerData/Inn").string("4355020124"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayerData/Kpp").string("725719119"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayerData/ClientID").string("1425473368942636823793761"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayerData/ProductUID").string("6847060478722900010"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayerData/RegisterUID").string("6847060478722900016"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayerData/BankData/Name").string("PAO SBERBANK"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayerData/BankData/Bic").string("044525225"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayerData/BankData/BankAccount").string("30101810400000000225"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayeeData/Name").string("OOO GRAVIZAPPA"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayeeData/Account").string("40702810738001117067"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayeeData/Inn").string("4437389956"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayeeData/Kpp").string("443019119"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayeeData/Type").string("2"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayeeData/ClientID").string("1424767310682788995"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayeeData/ProductUID").string("6846737252900274180"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayeeData/RegisterUID").string("6846737252900274186"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayeeData/BankData/Name").string("PAO SBERBANK"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayeeData/BankData/Bic").string("044525225"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PayeeData/BankData/BankAccount").string("30101810400000000225"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PaymentPurpose").string("55555/555"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PaymentPriority").string("5"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PaymentPriorityAuto").string("5"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PaymentSendType").string("3"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/PaymentPurposeCode").string("3"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/TaxFields/PayerStatus").string("1"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/TaxFields/UinCode").string("000000000000000000000000"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/TaxFields/Kbk").string("18210601010031000110"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/TaxFields/Oktmo").string("45319000"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/TaxFields/PaymentBasis").string("TP"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/TaxFields/TaxPeriod").string("GD.00.2019"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/TaxFields/DocumentTaxNumber").string("1820"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/TaxFields/DocumentTaxDate").string("2020-11-06"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PaymentOrder/TaxFields/TaxPayKind").string("SI"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/PayerAccountInfoBankInfoVSPNum").string("38903801775"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/docRef").string("78465654673534"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/OperationType").string("3"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/cmsBase64").string("srEdsfsdvs534sdfg54gfgdf"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/FirstSigner/IdFirstSigner").string("131321315"))
                .andExpect(xpath("/PutLegalPaymentOrderRq/FirstSigner/DateTimeFirstSigner").string("2020-11-06T03:18:33+03:00"))
        ;

        List<PaymentOrderEntity> entityList = paymentOrderRepository.findByDocRef("78465654673534");

        assertEquals(1, entityList.size());
        assertEquals("38903801775", entityList.get(0).getPayerAccountInfoBankInfoVSPNum());
        assertEquals("78465654673534", entityList.get(0).getDocRef());
        assertEquals("3", entityList.get(0).getOperationType());
        assertEquals("srEdsfsdvs534sdfg54gfgdf", entityList.get(0).getCmsBase64());
    }

//    @Test
//    public void testMessagePage() throws Exception {
//
//        mockMvc.perform(
//                post("/payment-orders")
//                        .content(xmlString)
//                        .contentType(MediaType.APPLICATION_XML)
//        )
//                .andExpect(status().isCreated())
//                .andExpect(content().xml(xmlString))
//        ;
//        System.out.println(content());
//    }

}
