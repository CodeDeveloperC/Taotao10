package com.taotao.sso.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:com.taotao.sso.service.impl</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/13.
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TaotaoResult checkData(String param, int type) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        //1、2、3分别代表username、phone、email
        if (1 == type) {
            criteria.andUsernameEqualTo(param);
        } else if (2 == type) {
            criteria.andPhoneEqualTo(param);
        } else if (3 == type) {
            criteria.andEmailEqualTo(param);
        }
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        if (tbUsers == null || tbUsers.isEmpty()) {
            return TaotaoResult.ok(true);
        }
        return TaotaoResult.ok(false);
    }

    @Override
    public TaotaoResult register(TbUser user) {
        if (StringUtils.isBlank(user.getUsername())
                || StringUtils.isBlank(user.getPassword())) {
            return TaotaoResult.build(400, "用户名或密码不能为空");
        }
        //检验数据是否重复
        TaotaoResult taotaoResult = checkData(user.getUsername(), 1);
        if (!(boolean)taotaoResult.getData()) {
            return TaotaoResult.build(400, "用户名已经注册");
        }

        TaotaoResult taotaoResult2 = checkData(user.getPhone(), 2);
        if (!(boolean)taotaoResult2.getData()) {
            return TaotaoResult.build(400, "电话已经注册");
        }

        TaotaoResult taotaoResult3 = checkData(user.getEmail(), 3);
        if (!(boolean)taotaoResult3.getData()) {
            return TaotaoResult.build(400, "邮箱已经注册");
        }

        //补全日期数据
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        tbUserMapper.insert(user);
        return TaotaoResult.ok();
    }
}
