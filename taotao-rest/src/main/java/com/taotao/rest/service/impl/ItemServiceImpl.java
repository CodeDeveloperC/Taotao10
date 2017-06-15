package com.taotao.rest.service.impl;

import com.mysql.jdbc.StringUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.rest.component.JedisClient;
import com.taotao.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title:com.taotao.rest.service.impl</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/12.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value("${ITEM_BASE_INFO_KEY}")
    private String ITEM_BASE_INFO_KEY;
    @Value("${ITEM_EXPIRE_SECOND}")
    private Integer ITEM_EXPIRE_SECOND;
    @Value("${ITEM_DESC_KEY}")
    private String ITEM_DESC_KEY;
    @Value("${ITEM_PARAM_KEY}")
    private String ITEM_PARAM_KEY;

    @Override
    public TbItem getTbItemById(Long itemId) {

        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" +itemId  + ":" + ITEM_BASE_INFO_KEY);
            if (!StringUtils.isNullOrEmpty(json)) {
                TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
                return tbItem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);

        try {
            jedisClient.set(REDIS_ITEM_KEY + ":" +itemId  + ":" + ITEM_BASE_INFO_KEY, JsonUtils.objectToJson(tbItem));
            jedisClient.expire(REDIS_ITEM_KEY + ":" +itemId  + ":" + ITEM_BASE_INFO_KEY, ITEM_EXPIRE_SECOND);
        } catch (Exception e){
            e.printStackTrace();
        }
        return tbItem;
    }

    @Override
    public TbItemDesc getTbItemDescById(Long itemId) {
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" +itemId  + ":" + ITEM_DESC_KEY);
            if (!StringUtils.isNullOrEmpty(json)) {
                TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return tbItemDesc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(itemId);

        try {
            jedisClient.set(REDIS_ITEM_KEY + ":" +itemId  + ":" + ITEM_DESC_KEY, JsonUtils.objectToJson(tbItemDesc));
            jedisClient.expire(REDIS_ITEM_KEY + ":" +itemId  + ":" + ITEM_DESC_KEY, ITEM_EXPIRE_SECOND);
        } catch (Exception e){
            e.printStackTrace();
        }
        return tbItemDesc;
    }

    @Override
    public TbItemParamItem getTbItemParamById(Long itemId) {
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" +itemId  + ":" + ITEM_PARAM_KEY);
            if (!StringUtils.isNullOrEmpty(json)) {
                TbItemParamItem tbItemParamItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
                return tbItemParamItem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            TbItemParamItem tbItemParamItem = list.get(0);
            try {
                jedisClient.set(REDIS_ITEM_KEY + ":" +itemId  + ":" + ITEM_PARAM_KEY, JsonUtils.objectToJson(tbItemParamItem));
                jedisClient.expire(REDIS_ITEM_KEY + ":" +itemId  + ":" + ITEM_PARAM_KEY, ITEM_EXPIRE_SECOND);
            } catch (Exception e){
                e.printStackTrace();
            }
            return tbItemParamItem;
        }
        return null;
    }
}
