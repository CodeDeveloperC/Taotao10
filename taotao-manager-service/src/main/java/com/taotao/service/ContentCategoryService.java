package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

/**
 * <p>Title:com.taotao.service</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/10.
 */
public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCatList(Long parentId);

    TaotaoResult insertCategory(Long parentId, String name);
}
