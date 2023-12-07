package com.itavgur.otus.sa.order.web.dto;

import com.itavgur.otus.sa.order.domain.OrderState;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class OrderDto {

    Integer id;
    String idempotencyKey;
    Long customerId;
    String description;
    BigDecimal sum;
    OrderState state;

}