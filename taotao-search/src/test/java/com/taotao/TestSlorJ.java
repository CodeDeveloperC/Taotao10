package com.taotao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * <p>Title:com.taotao</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/11.
 */
public class TestSlorJ {

    @Test
    public void testSolrJ() throws Exception{
        HttpSolrServer solrServer = new HttpSolrServer("http://192.168.59.128:8080/solr");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "soktjtest1");
        document.addField("item_title","测试商品");
        document.addField("item_sell_point", "卖点");

        solrServer.add(document);
        solrServer.commit();
    }

    @Test
    public void testQuery() throws Exception{
        HttpSolrServer solrServer = new HttpSolrServer("http://192.168.59.128:8080/solr");

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");

        QueryResponse response = solrServer.query(solrQuery);

        SolrDocumentList solrDocuments = response.getResults();

        for (SolrDocument solrDocument : solrDocuments) {
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_sell_point"));
        }
    }

    @Test
    public void testSolrCloud() throws Exception{
        CloudSolrServer solrServer = new CloudSolrServer("192.168.59.128:2181,192.168.59.128:2182,192.168.59.128:2183");
        solrServer.setDefaultCollection("collection2");

        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "陈伟");
        document.addField("item_title", "陈伟1");

        solrServer.add(document);
        solrServer.commit();
    }
}
