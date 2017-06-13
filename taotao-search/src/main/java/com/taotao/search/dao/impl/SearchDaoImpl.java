package com.taotao.search.dao.impl;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchItem;
import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:com.taotao.search.dao.impl</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/11.
 */
@Repository
public class SearchDaoImpl implements SearchDao {
    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(SolrQuery solrQuery) throws Exception {

        QueryResponse response = solrServer.query(solrQuery);

        SolrDocumentList solrDocumentList = response.getResults();

        ArrayList<SearchItem> itemArrayList = new ArrayList<>();

        for (SolrDocument solrDocument : solrDocumentList) {
            SearchItem item = new SearchItem();
            item.setCategory_name((String) solrDocument.get("item_category_name"));
            item.setId((String) solrDocument.get("item_id"));
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((Long) solrDocument.get("item_price"));
            item.setSell_point((String) solrDocument.get("item_sell_point"));

            System.out.println("------------item.getId()" + item.getId());

            //取高亮
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = "";
            if (list != null && list.size() > 0) {
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) solrDocument.get("item_title");
            }
            item.setTitle(itemTitle);
            itemArrayList.add(item);
        }

        SearchResult result = new SearchResult();
        result.setItemList(itemArrayList);
        result.setRecordCount(solrDocumentList.getNumFound());
        return result;
    }
}
