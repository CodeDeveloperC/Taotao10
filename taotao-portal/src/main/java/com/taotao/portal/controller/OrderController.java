package com.taotao.portal.controller;

import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.OrderInfo;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>Title:com.taotao.portal.controller</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/14.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/order-cart")
    public String showOrderCart(Model model, HttpServletRequest request) {
        List<CartItem> list = cartService.getCartItems(request);
        model.addAttribute("cartList", list);
        return "order-cart";
    }


    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo,Model model, HttpServletRequest request) {
        TbUser user = (TbUser) request.getAttribute("user");
        orderInfo.setUserId(user.getId());
        orderInfo.setBuyerNick(user.getUsername());

        String orderId = orderService.createOrder(orderInfo);
        model.addAttribute("orderId", orderId);
        model.addAttribute("payment", orderInfo.getPayment());

        DateTime dateTime = new DateTime();
        dateTime  = dateTime.plusDays(3);
        model.addAttribute("date", dateTime.toString("yyyy-MM-dd"));
        return "success";
    }
}
