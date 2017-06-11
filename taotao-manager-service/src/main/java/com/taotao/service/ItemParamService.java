package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * <p>Title:com.taotao.service</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/8.
 */
public interface ItemParamService {
    TaotaoResult getItemParaByCid(Long cid);

    TaotaoResult insertItemParam(Long cid, String paramData);
}
