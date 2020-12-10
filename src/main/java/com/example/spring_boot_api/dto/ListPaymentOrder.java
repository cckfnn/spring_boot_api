package com.example.spring_boot_api.dto;

import com.sbt.pprb.dto.srvputlegalpaymentorder.PutLegalPaymentOrderRq;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListPaymentOrder {
    private List<PutLegalPaymentOrderRq> putLegalPaymentOrderRqList = new ArrayList<>();
}
