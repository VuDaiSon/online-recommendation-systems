package com.example.recommendershop.dto.order.response;

import com.example.recommendershop.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse extends BaseDto {
    private Date date;
    private Double totalValue;
    private String status;
    private String address;
    private String number;
    private String receiver;
    private Integer shippingFee;
    private String paymentMethod;
}
