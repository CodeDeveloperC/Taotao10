package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>Title:com.taotao.portal.service</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/14.
 */
public interface CartService {
    TaotaoResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);

    List<CartItem> getCartItems(HttpServletRequest request);

    TaotaoResult updateCartItem(long itemId, Integer num, HttpServletRequest request,HttpServletResponse response);
    TaotaoResult deleteCartItem(long itemId, HttpServletRequest request,HttpServletResponse response);
}
