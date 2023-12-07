package com.itavgur.otus.sa.order.repository;

import com.itavgur.otus.sa.order.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {


}