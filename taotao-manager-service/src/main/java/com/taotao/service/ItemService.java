package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * Created by root on 2017/6/6.
 */
public interface ItemService {
    TbItem getItemById(Long itemId);
    EasyUIDataGridResult getItemList(int page,int rows);

    TaotaoResult createItem(TbItem item, String desc,String itemParam);

    String getItemParamHtml(Long itemId);
}
