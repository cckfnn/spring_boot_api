package com.example.spring_boot_api;

import com.example.spring_boot_api.dto.PutLegalPaymentOrderRq;
import com.example.spring_boot_api.exceptions.ExceptionDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentOrderControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private PutLegalPaymentOrderRq rqWithDocRefError;
    private PutLegalPaymentOrderRq rqWithCmsBase64Error;
    private PutLegalPaymentOrderRq rqWithCmsBankAccountError;
    private PutLegalPaymentOrderRq rqWithPercentErrorMin;
    private PutLegalPaymentOrderRq rqWithPercentErrorMax;
    ResponseEntity<ExceptionDetails> responseEntity;

    @Before
    public void init() {
        rqWithDocRefError = PutLegalPaymentOrderRq.builder()
                .cmsBase64("srEdsfsdvs534sdfg54gfgdf")
                .docRef("11111") // тут допущена ошибка, символов меньше
                .operationType("3")
                .payerAccountInfoBankInfoVSPNum("38903801775")
                .percent(50)
                .build();

        rqWithCmsBase64Error = PutLegalPaymentOrderRq.builder()
                .cmsBase64(null) // ошибка, поле не может быть null
                .docRef("12345678")
                .operationType("3")
                .payerAccountInfoBankInfoVSPNum("38903801775")
                .percent(50)
                .build();

        rqWithCmsBankAccountError = PutLegalPaymentOrderRq.builder()
                .cmsBase64("srEdsfsdvs534sdfg54gfgdf")
                .docRef("12345678")
                .operationType("3")
                .payerAccountInfoBankInfoVSPNum("38903801775")
                .bankAccount("404555") // ошибка, должно начинаться с 407 или 408
                .percent(50)
                .build();

        rqWithPercentErrorMin = PutLegalPaymentOrderRq.builder()
                .cmsBase64("srEdsfsdvs534sdfg54gfgdf")
                .docRef("12345678")
                .operationType("3")
                .payerAccountInfoBankInfoVSPNum("38903801775")
                .bankAccount("404555")
                .percent(0) // ошибка, должно быть в диапазоне от 1 до 100
                .build();

        rqWithPercentErrorMax = PutLegalPaymentOrderRq.builder()
                .cmsBase64("srEdsfsdvs534sdfg54gfgdf")
                .docRef("12345678")
                .operationType("3")
                .payerAccountInfoBankInfoVSPNum("38903801775")
                .bankAccount("404555")
                .percent(101) // ошибка, должно быть в диапазоне от 1 до 100
                .build();
    }



    @Test
    public void paymentOrderDtoTestDocRefErrorSizeTest() {
        responseEntity = testRestTemplate.postForEntity("http://localhost:8080/payment-orders",
                rqWithDocRefError, ExceptionDetails.class);
        String info = Objects.requireNonNull(responseEntity.getBody()).getMessage();
        assertTrue(info.contains("Длина строки docRef должна быть от 7 до 20 символов"));
        assertEquals(500, responseEntity.getStatusCodeValue());
    }

    @Test
    public void paymentOrderDtoTestCmsBase64NullTest() {
        responseEntity = testRestTemplate.postForEntity("http://localhost:8080/payment-orders",
                rqWithCmsBase64Error, ExceptionDetails.class);
        String info = Objects.requireNonNull(responseEntity.getBody()).getMessage();
        assertEquals(500, responseEntity.getStatusCodeValue());
        assertTrue(info.contains("cmsBase64 должно быть заполнено"));

    }

    @Test
    public void PaymentOrderDtoBankAccountErrorTest() {
        responseEntity = testRestTemplate.postForEntity("http://localhost:8080/payment-orders",
                rqWithCmsBankAccountError, ExceptionDetails.class);
        String info = Objects.requireNonNull(responseEntity.getBody()).getMessage();
        assertEquals(500, responseEntity.getStatusCodeValue());
        assertTrue(info.contains("Номер счета не соответствует маске"));

    }

    @Test
    public void PaymentOrderDtoPercentMinTest() {
        responseEntity = testRestTemplate.postForEntity("http://localhost:8080/payment-orders",
                rqWithPercentErrorMin, ExceptionDetails.class);
        String info = Objects.requireNonNull(responseEntity.getBody()).getMessage();
        assertEquals(500, responseEntity.getStatusCodeValue());
        assertTrue(info.contains("начение priority не должно быть меньше 1"));

    }

    @Test
    public void PaymentOrderDtoPercentMaxTest() {
        responseEntity = testRestTemplate.postForEntity("http://localhost:8080/payment-orders",
                rqWithPercentErrorMax, ExceptionDetails.class);
        String info = Objects.requireNonNull(responseEntity.getBody()).getMessage();
        assertEquals(500, responseEntity.getStatusCodeValue());
        assertTrue(info.contains("Значение priority не должно быть больше 100"));

    }
}