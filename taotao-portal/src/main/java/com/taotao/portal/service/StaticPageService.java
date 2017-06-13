package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * <p>Title:com.taotao.portal.service</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/13.
 */
public interface StaticPageService {
    TaotaoResult getItemHtml(Long itemId) throws Exception;
}
