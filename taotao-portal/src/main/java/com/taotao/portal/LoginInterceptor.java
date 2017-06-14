package com.taotao.portal;

import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title:com.taotao.portal</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/13.
 */
public class LoginInterceptor implements HandlerInterceptor{
    @Value("${SSO_LOGIN_URL}")
    private String SSO_LOGIN_URL;
    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        TbUser user = userService.getUserByToken(httpServletRequest, httpServletResponse);
        if (user == null) {
           // System.out.println("httpServletRequest.getRequestURI():"+httpServletRequest.getRequestURI());
            httpServletResponse.sendRedirect(SSO_LOGIN_URL+"?redirectURL="+httpServletRequest.getRequestURL());
            return false;
        }

        //放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
