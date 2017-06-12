package com.taotao;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * <p>Title:com.taotao</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/10.
 */
public class HttpClient {

    @Test
    public void testHeepGet() throws Exception{
        /**
         * 第一步：把httpClient使用的jar包添加到工程中
         * 第二步：创建一个httpclient的测试模块
         * 地散步：创建测试方法
         * 第四步：创建一个httpClient对象
         */
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //第五步：创建一个HttpGet对象，需要制定一个请求的URL
        HttpGet httpGet = new HttpGet("https://www.google.com");
        //第六步：执行请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //第七步：接受返回结果。httpClient对象
        HttpEntity entity = response.getEntity();
        //第8步：取响应的内容
        String html = EntityUtils.toString(entity);
        System.out.println(html);
        //第九步：关闭response，httpClient
        response.close();
        httpClient.close();
    }

    @Test
    public void testHttpPost() throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8082/testhttppost.html");
        ArrayList<NameValuePair> formList = new ArrayList<>();

        formList.add(new BasicNameValuePair("name", "车位"));
        formList.add(new BasicNameValuePair("pass", "1234"));

        StringEntity entity = new UrlEncodedFormEntity(formList, "utf-8");
        post.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity httpEntity = response.getEntity();
        String result = EntityUtils.toString(httpEntity);
        System.out.println(result);

        response.close();
        httpClient.close();
    }

    @Test
    public void testURLencode() throws Exception{
        System.out.println(URLEncoder.encode("陈伟","utf-8"));
    }
}
