package com.example.spring_boot_api.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListPaymentOrder implements Serializable {
    private static final long serialVersionUID = 8617625321151856608L;
    private List<PutLegalPaymentOrderRq> putLegalPaymentOrderRqList = new ArrayList<>();
}
