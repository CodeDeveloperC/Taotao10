package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title:com.taotao.portal.service</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/13.
 */
public interface UserService {
    TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response);
}
