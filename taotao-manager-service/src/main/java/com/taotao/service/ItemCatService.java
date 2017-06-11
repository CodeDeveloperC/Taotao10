package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * <p>Title:com.taotao.service</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/6.
 */
public interface ItemCatService {
    public List<EasyUITreeNode> getItemCatList(long parentId);
}
