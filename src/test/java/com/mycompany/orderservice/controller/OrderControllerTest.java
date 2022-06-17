package com.mycompany.orderservice.controller;

import com.mycompany.orderservice.dto.OrderDTO;
import com.mycompany.orderservice.repository.OrderRepository;
import com.mycompany.orderservice.service.OrderService;
import com.mycompany.orderservice.service.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)

public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;;


    @Test
    @DisplayName("scenario for placing order")
    void testPlaceOrder(){
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setId(2L);
        orderDTO.setUserId(3L);

Mockito.when(orderService.placeOrder(Mockito.any())).thenReturn(orderDTO);
        ResponseEntity<OrderDTO> responseEntity =orderController.placeOrder(orderDTO,2L);// it takes order dto and value long type

        assertEquals(2L, responseEntity.getBody().getId());
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
   }

    @Test
    @DisplayName("scenario for get all order")
    void testGetAllOrders(){
        List<OrderDTO> dtos=new ArrayList<>();

        OrderDTO dto=new OrderDTO();
        dto.setId(1l);
        dto.setUserId(1L);
      dtos.add(dto);
      Mockito.when(orderService.getAllOrders(Mockito.any())).thenReturn(dtos);

        ResponseEntity<List<OrderDTO>> re=orderController.getAllOrders(1L); //here
        Assertions.assertEquals(1, re.getBody().size());

    }

}
