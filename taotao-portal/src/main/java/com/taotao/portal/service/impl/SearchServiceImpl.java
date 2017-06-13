package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

/**
 * <p>Title:com.taotao.portal.service.impl</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/11.
 */
@Controller
public class SearchServiceImpl implements SearchService {
    @Value(value = "${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;
    @Override
    public SearchResult search(String keyword, int page, int rows) {
        HashMap<String, String> param = new HashMap<>();
        param.put("keyword", keyword);
        param.put("page", page + "");
        param.put("rows", rows + "");

        //调用服务
        String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
        SearchResult searchResult = (SearchResult) taotaoResult.getData();

        return searchResult;
    }
}
