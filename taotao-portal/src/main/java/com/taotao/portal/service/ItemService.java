package com.taotao.portal.service;

import com.taotao.pojo.TbItem;

/**
 * <p>Title:com.taotao.portal.service</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/12.
 */
public interface ItemService {

    TbItem getItemById(Long itemId);

    String getItemDescById(Long itemId);

    String getItemParamById(Long itemId);
}
