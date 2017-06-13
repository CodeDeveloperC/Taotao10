package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title:com.taotao.sso.service</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/13.
 */
public interface LoginService {
    TaotaoResult login(String userName, String passWord, HttpServletRequest request, HttpServletResponse response);

    TaotaoResult getUserByToken(String token);
}
