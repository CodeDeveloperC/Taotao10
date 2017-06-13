package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * <p>Title:com.taotao.portal.service</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/11.
 */
public interface SearchService {
    SearchResult search(String keyword, int page, int rows);
}
