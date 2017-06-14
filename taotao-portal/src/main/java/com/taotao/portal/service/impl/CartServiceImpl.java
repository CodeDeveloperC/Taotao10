package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:com.taotao.portal.service.impl</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/14.
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ItemService itemService;
    @Value("${COOKIE_EXPIRE}")
    private Integer COOKIE_EXPIRE;

    @Override
    public TaotaoResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {

        List<CartItem> list = getCartItemList(request);

        boolean haveAddedFlag = false;

        for (CartItem cartItem : list) {
            if (cartItem.getId().longValue() == itemId) {
                cartItem.setNum(cartItem.getNum() + num);
                haveAddedFlag=true;
                break;
            }
        }

        if (!haveAddedFlag) {
            TbItem item = itemService.getItemById(itemId);
            CartItem cartItem = new CartItem();
            cartItem.setId(itemId);
            cartItem.setNum(num);
            cartItem.setPrice(item.getPrice());
            cartItem.setTitle(item.getTitle());
            if (StringUtils.isNotBlank(item.getImage())) {
                String image = item.getImage();
                String[] s = image.split(",");
                cartItem.setImage(s[0]);
            }

            list.add(cartItem);
        }

        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(list),COOKIE_EXPIRE,true);

        return TaotaoResult.ok();
    }

    @Override
    public List<CartItem> getCartItems(HttpServletRequest request) {
        return getCartItemList(request);
    }

    @Override
    public TaotaoResult updateCartItem(long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {

        List<CartItem> cartItemList = getCartItemList(request);
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getId() == itemId) {
                cartItem.setNum(num);
                break;
            }
        }
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(cartItemList),COOKIE_EXPIRE,true);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> cartItemList = getCartItemList(request);
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getId() == itemId) {
                cartItemList.remove(cartItem);
                break;
            }
        }
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(cartItemList),COOKIE_EXPIRE,true);
        return TaotaoResult.ok();
    }

    private List<CartItem> getCartItemList(HttpServletRequest request) {
        try {
            String json = CookieUtils.getCookieValue(request, "TT_CART", true);
            List<CartItem> list = JsonUtils.jsonToList(json, CartItem.class);
            return list == null ? new ArrayList<CartItem>() : list;
        } catch (Exception e) {
            return new ArrayList<CartItem>();
        }
    }
}
