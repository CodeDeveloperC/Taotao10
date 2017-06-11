package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * <p>Title:com.taotao.search.service</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/11.
 */
public interface SearchService {
    SearchResult search(String queryString,int page,int rows) throws Exception;
}
