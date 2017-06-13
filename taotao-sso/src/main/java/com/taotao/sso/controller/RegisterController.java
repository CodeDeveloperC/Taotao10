package com.taotao.sso.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Title:com.taotao.sso.controller</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/13.
 */
@Controller
@RequestMapping("/user")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param,
                            @PathVariable Integer type,
                            String callback) {
        try {
            TaotaoResult taotaoResult = registerService.checkData(param, type);
            if (StringUtils.isNotBlank(callback)) {
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(taotaoResult);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }
            return taotaoResult;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Object register(TbUser user) {
        try {
            TaotaoResult result= registerService.register(user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
