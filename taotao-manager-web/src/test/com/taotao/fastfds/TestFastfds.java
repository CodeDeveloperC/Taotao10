package com.taotao.fastfds;

import org.csource.fastdfs.*;
import org.junit.Test;

/**
 * <p>Title:com.taotao.fastfds</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/8.
 */
public class TestFastfds {
    @Test
    public void testUpload() throws Exception{
        //1.将FastDFS提供的jar包添加到工程中
        //2、初始化全局配置。加载一个配置文件
        ClientGlobal.init("H:\\javasefuxi\\Taotao01\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
        //3、创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //4、创建一个TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //5、声明一个StorageServer对象，null
        StorageServer storageServer= null;
        //6、获得StorageServer对象
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //7、直接电泳StorageClient对象方法上传文件即可
        String[] strings = storageClient.upload_file("D:\\360安全浏览器下载\\zhuomian.jpg", "jpg", null);
        for (String string:
             strings) {
            System.out.println(string);
        }
    }

    @Test
    public void testDastDFSClient() throws Exception{
        FastDFSClient fastDFSClient = new FastDFSClient("H:\\javasefuxi\\Taotao01\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");

        String string = fastDFSClient.uploadFile("D:\\360安全浏览器下载\\12.jpg", "jpg");
        System.out.println(string);
    }

}
