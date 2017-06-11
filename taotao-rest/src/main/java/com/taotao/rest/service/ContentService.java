package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * <p>Title:com.taotao.rest.service</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/10.
 */
public interface ContentService {
    List<TbContent> getContentList(Long cid);

    TaotaoResult syncContent(Long cid);
}
