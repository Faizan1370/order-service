package com.order.service.orderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.order.service.common.OrderRequestDTO;
import com.order.service.common.OrderResponseDTO;
import com.order.service.common.PaymentDTO;
import com.order.service.entity.Order;
import com.order.service.repository.OrderRepository;

@Service
@RefreshScope
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    // @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private final String ENDPOINT_URI = "http://PAYMENT-SERVICE/payment/order-payment";

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public OrderResponseDTO saveOrder(OrderRequestDTO orderRequest) {
        logger.info("Book Order request(Order Service) :" + orderRequest);
        final Order order = Order.builder().name(orderRequest.getName()).qty(orderRequest.getQty()).price(orderRequest.getPrice()).itemId(orderRequest.getItemId()).build();
        orderRepo.save(order);
        // payment API
        final PaymentDTO payment = PaymentDTO.builder().OrderId(order.getId()).amount(order.getPrice()).build();
        logger.info("***************************** :" + ENDPOINT_URI);
        final PaymentDTO paymentResponse = restTemplate.postForObject(ENDPOINT_URI, payment, PaymentDTO.class);
        final OrderResponseDTO orderResponseDTO = OrderResponseDTO.builder().order(order).payment(paymentResponse).build();
        logger.info("Book Order Response(Order Service) :" + orderResponseDTO);
        return orderResponseDTO;

    }

}
