package com.taotao.rest.service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

/**
 * <p>Title:com.taotao.rest.service</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/12.
 */
public interface ItemService {
    TbItem getTbItemById(Long itemId);

    TbItemDesc getTbItemDescById(Long itemId);

    TbItemParamItem getTbItemParamById(Long itemId);
}
