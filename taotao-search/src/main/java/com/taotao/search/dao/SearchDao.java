package com.taotao.search.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * <p>Title:com.taotao.search.dao</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/11.
 */
public interface SearchDao {
    SearchResult search(SolrQuery solrQuery) throws Exception;
}
