package com.taotao.rest.service.impl;

import com.mysql.jdbc.StringUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.component.JetisClient;
import com.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title:com.taotao.rest.service.impl</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/10.
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private JetisClient jetisClient;
    @Value("${REDIS_CONTENT_KEY}")
    private String REDIS_CONTENT_KEY;
    @Override
    public List<TbContent> getContentList(Long cid) {

        //首先查找缓存
        try {
            String json = jetisClient.hget(REDIS_CONTENT_KEY, cid + "");
            if (!StringUtils.isEmptyOrWhitespaceOnly(json)) {
                List<TbContent> contentList = JsonUtils.jsonToList(json, TbContent.class);
                return contentList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //根据cid查询内容列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);

        //返回结果前，向缓存中添加数据
        try {
            jetisClient.hset(REDIS_CONTENT_KEY, cid + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public TaotaoResult syncContent(Long cid) {
        jetisClient.hdel(REDIS_CONTENT_KEY, cid + "");
        return TaotaoResult.ok();
    }
}
