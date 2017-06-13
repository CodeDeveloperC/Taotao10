package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * <p>Title:com.taotao.sso.service</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/13.
 */
public interface RegisterService {
    TaotaoResult checkData(String param, int type);

    TaotaoResult register(TbUser user);
}
