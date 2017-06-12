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
 * @Author: ˾��ܲ
 * @data: 2017/6/10.
 */
public class HttpClient {

    @Test
    public void testHeepGet() throws Exception{
        /**
         * ��һ������httpClientʹ�õ�jar����ӵ�������
         * �ڶ���������һ��httpclient�Ĳ���ģ��
         * ��ɢ�����������Է���
         * ���Ĳ�������һ��httpClient����
         */
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //���岽������һ��HttpGet������Ҫ�ƶ�һ�������URL
        HttpGet httpGet = new HttpGet("https://www.google.com");
        //��������ִ������
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //���߲������ܷ��ؽ����httpClient����
        HttpEntity entity = response.getEntity();
        //��8����ȡ��Ӧ������
        String html = EntityUtils.toString(entity);
        System.out.println(html);
        //�ھŲ����ر�response��httpClient
        response.close();
        httpClient.close();
    }

    @Test
    public void testHttpPost() throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8082/testhttppost.html");
        ArrayList<NameValuePair> formList = new ArrayList<>();

        formList.add(new BasicNameValuePair("name", "��λ"));
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
        System.out.println(URLEncoder.encode("��ΰ","utf-8"));
    }
}
