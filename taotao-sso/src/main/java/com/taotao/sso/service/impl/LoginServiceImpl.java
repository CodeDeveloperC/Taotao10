package com.taotao.sso.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.component.JetisClient;
import com.taotao.sso.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * <p>Title:com.taotao.sso.service.impl</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/13.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private JetisClient jetisClient;

    @Value("${REDIS_SESSION_KEY}")
    private String REDIS_SESSION_KEY;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;
    @Override
    public TaotaoResult login(String userName, String passWord, HttpServletRequest request, HttpServletResponse response) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            return TaotaoResult.build(400, "用户名或密码错误");
        }

        TbUser user = list.get(0);
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(passWord.getBytes()))) {
            return TaotaoResult.build(400, "用户名或密码错误");
        }

        String token = UUID.randomUUID().toString();
        user.setPassword(null);
        jetisClient.set(REDIS_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
        jetisClient.expire(REDIS_SESSION_KEY + ":" + token, SESSION_EXPIRE);
        CookieUtils.setCookie(request, response, "TT_TOKEN", token);

        return TaotaoResult.ok(token);
    }

    @Override
    public TaotaoResult getUserByToken(String token) {
        String json = jetisClient.get(REDIS_SESSION_KEY + ":" + token);
        if (StringUtils.isBlank(json)) {
            return TaotaoResult.build(400, "用户session已经过期");
        }
        TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
        jetisClient.expire(REDIS_SESSION_KEY + ":" + token, SESSION_EXPIRE);

        return TaotaoResult.ok(user);
    }
}
