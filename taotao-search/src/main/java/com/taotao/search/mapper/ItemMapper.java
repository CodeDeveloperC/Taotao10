package com.taotao.search.mapper;

import com.taotao.search.pojo.SearchItem;

import java.util.List;

/**
 * <p>Title:com.taotao.search.mapper</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/11.
 */
public interface ItemMapper {
    List<SearchItem> getItemList();
}
