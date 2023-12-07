package com.itavgur.otus.sa.order.service;

import com.itavgur.otus.sa.order.domain.Order;
import com.itavgur.otus.sa.order.domain.OrderState;
import com.itavgur.otus.sa.order.exception.NotFoundException;
import com.itavgur.otus.sa.order.repository.OrderRepository;
import com.itavgur.otus.sa.order.web.dto.OrderDto;
import com.itavgur.otus.sa.order.web.dto.PersonDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class OrderService {

    static final String ORDER_IDEMPOTENCY_KEY = "orderIdmKeys:";

    OrderRepository orderRepository;

    RedisTemplate<String, String> redisTemplate;

    public OrderDto getOrder(Integer orderId, PersonDto personDto) {
        Order order = getOrderById(orderId);
        checkAccessToOrder(personDto, order);
        if (OrderState.DELETED.equals(order.getState())) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return getPersonDto(order);
    }

    @Transactional
    public OrderDto createOrder(OrderDto orderDto, PersonDto personDto) {

        checkIdempotencyKey(orderDto.getIdempotencyKey(), personDto.getId());

        Order order = getOrder(orderDto);
        order.setId(null);
        order.setCustomerId(personDto.getId());
        Order orderNew = orderRepository.save(order);
        return getPersonDto(orderNew);
    }

    private void checkIdempotencyKey(String key, Long customerId) {

        if (key == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idempotencyKey can't be null ");
        }

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String foundKey = ops.get(ORDER_IDEMPOTENCY_KEY + key);
        if (foundKey == null) {
            ops.set(ORDER_IDEMPOTENCY_KEY + key, customerId.toString());
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

    }

    public OrderDto updateOrder(OrderDto newOrderDto, PersonDto personDto) {
        Order order = getOrderById(newOrderDto.getId());
        if (OrderState.DELETED.equals(order.getState())) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        checkAccessToOrder(personDto, order);
        Order newOrder = getOrder(newOrderDto);
        newOrder.setCustomerId(personDto.getId());
        Order save = orderRepository.save(newOrder);
        return getPersonDto(save);
    }

    public void deleteOrder(Integer id, PersonDto personDto) {
        Order order = getOrderById(id);
        checkAccessToOrder(personDto, order);
        order.setState(OrderState.DELETED);
        orderRepository.save(order);
    }

    private Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    private void checkAccessToOrder(PersonDto personDto, Order order) {

        if (!order.getCustomerId().equals(personDto.getId())) {
            throw new NotFoundException();
        }

    }

    private OrderDto getPersonDto(Order order) {
        return new OrderDto(
                order.getId(),
                null,
                order.getCustomerId(),
                order.getDescription(),
                order.getSum(),
                order.getState()
        );
    }

    private Order getOrder(OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                orderDto.getCustomerId(),
                orderDto.getDescription(),
                orderDto.getSum(),
                orderDto.getState()
        );
    }
}