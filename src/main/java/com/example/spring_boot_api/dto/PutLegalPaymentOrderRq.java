package com.example.spring_boot_api.dto;


import lombok.*;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@XmlRootElement(name = "PutLegalPaymentOrderRq")
public class PutLegalPaymentOrderRq implements Serializable {

    private static final long serialVersionUID = 1383504060214961234L;


    private long id;
    @NotEmpty(message = "payerAccountInfoBankInfoVSPNum должно быть заполнено")
    private String payerAccountInfoBankInfoVSPNum;

    @NotEmpty(message = "docRef должно быть заполнено")
    @Size(min = 7, max = 20, message = "Длина строки docRef должна быть от 7 до 20 символов")
    private String docRef;

    @NotEmpty(message = "operationType должно быть заполнено")
    @Size(min = 1, max = 1)
    private String operationType;

    @NotEmpty(message = "408444434343434cmsBase64 должно быть заполнено")
    private String cmsBase64;

    @Pattern(regexp = "^40[78]\\d*$", message = "Номер счета не соответствует маске")
    private String bankAccount;

    @NotNull
    @Min(value = 1, message ="Значение priority не должно быть меньше 1")
    @Max(value = 100, message ="Значение priority не должно быть больше 100")
    private int percent;
}
