package com.order.service.orderService;

import com.order.service.common.OrderRequestDTO;
import com.order.service.common.OrderResponseDTO;

public interface OrderService {

    OrderResponseDTO saveOrder(OrderRequestDTO orderRequest);

}
