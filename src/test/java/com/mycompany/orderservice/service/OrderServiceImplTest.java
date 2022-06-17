package com.mycompany.orderservice.service;

import com.mycompany.orderservice.dto.OrderDTO;
import com.mycompany.orderservice.entity.OrderEntity;
import com.mycompany.orderservice.repository.OrderRepository;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @InjectMocks
    private OrderRepository orderRepository;

    @Test
    void testPlaceOrder(){
        OrderEntity oe =new OrderEntity();
        oe.setUserId(1l);
        oe.setId(2l);

      oe= orderRepository.save(oe);

    }




}
