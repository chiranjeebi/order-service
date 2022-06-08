package com.mycompany.orderservice.service;

import com.mycompany.orderservice.client.feign.BookFeignClient;
import com.mycompany.orderservice.dto.BookDTO;
import com.mycompany.orderservice.dto.OrderDTO;
import com.mycompany.orderservice.entity.OrderEntity;
import com.mycompany.orderservice.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookFeignClient bookFeignClient;
    @Override
    public OrderDTO placeOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        StringBuilder sb = new StringBuilder();
        for(BookDTO bdto: orderDTO.getBooks()){
            sb.append(bdto.getBookId().toString());
            sb.append(",");
        }
        sb.deleteCharAt(sb.toString().length() - 1);
        orderEntity.setUserId(orderDTO.getUserId());
        orderEntity.setBookIds(sb.toString());
        orderEntity = orderRepository.save(orderEntity);
        BeanUtils.copyProperties(orderEntity, orderDTO);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> getAllOrders(Long userId) {

        List<OrderEntity> orderEntities = orderRepository.findAllByUserId(userId);

        ResponseEntity<BookDTO> re = null;
        List<BookDTO> dtoList = new ArrayList<>();
        List<OrderDTO> orderDtoList = new ArrayList<>();
        BookDTO bookDTO = null;
        OrderDTO orderDTO = null;

        for (OrderEntity oe: orderEntities){
            String[] bookIds = oe.getBookIds().split(",");
            orderDTO = new OrderDTO();
            for(String bookId: bookIds){
                re = bookFeignClient.getBook(Long.parseLong(bookId));
                bookDTO = re.getBody();
                dtoList.add(bookDTO);
            }
            orderDTO.setId(oe.getId());
            orderDTO.setBooks(dtoList);
            orderDTO.setUserId(userId);
            orderDtoList.add(orderDTO);
        }

        return orderDtoList;
    }
}
