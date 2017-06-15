package com.taotao.order.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.order.pojo.OrderInfo;

/**
 * <p>Title:com.taotao.order.service</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/14.
 */
public interface OrderService {
    TaotaoResult createOrder(OrderInfo orderInfo);
}
