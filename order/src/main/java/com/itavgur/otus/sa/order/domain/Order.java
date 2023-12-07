package com.itavgur.otus.sa.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(schema = "orders", name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(generator = "orders_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "orders_seq", schema = "orders", allocationSize = 1, sequenceName = "orders_order_id_seq")
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "customer_id")
    private Long customerId;

    private String description;

    private BigDecimal sum;

    @Column(name = "state_id")
    @Enumerated(EnumType.ORDINAL)
    private OrderState state;

}