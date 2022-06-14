package com.mycompany.orderservice.controller;

import com.mycompany.orderservice.dto.BookDTO;
import com.mycompany.orderservice.dto.OrderDTO;
import com.mycompany.orderservice.service.OrderService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)

public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;
@Mock
    private OrderService orderService;

    @Test
    @DisplayName("scenario for placing order")
    void testPlaceOrder(){
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setId(2L);
        orderDTO.setUserId(3L);
Mockito.when(orderService.placeOrder(Mockito.any())).thenReturn(orderDTO);
        ResponseEntity<OrderDTO> responseEntity =orderController.placeOrder(orderDTO,2L);
        assertEquals(2L, responseEntity.getBody().getId());
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());


    }

/*    @Test
    @DisplayName("scenario for get all order")
    void testGetAllOrders(){

        List<OrderDTO> orderDTOList=new ArrayList<>();
        OrderDTO orderDTO=new OrderDTO();
     orderDTO.setUserId(2L);
     orderDTO.setId(3L);
        orderDTOList.add(orderDTO);

        Mockito.when(orderService.getAllOrders(Mockito.any())).thenReturn(orderDTOList);
        ResponseEntity<List<OrderDTO>> responseEntity =  orderController.getAllOrders();
        Assertions.assertNotNull(responseEntity.getBody().size());
        Assertions.assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCodeValue());



    }*/

}
