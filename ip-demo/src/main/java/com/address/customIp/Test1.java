package com.address.customIp;

import com.address.customIp.resource.RequestAttr;
import com.address.customIp.resource.ResultAddress;
import com.address.util.HttpclientUtil;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.http.client.utils.HttpClientUtils;
import org.bson.Document;
import org.junit.Test;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;

public class Test1 {

    byte[] b ;
    String path = "C:\\Users\\Song\\Downloads\\ip2region-1.9.0-release\\ip2region-1.9.0-release\\data\\ip2region.db";


    @Test
    public void test1() throws IOException {
        long time = System.currentTimeMillis();
        RandomAccessFile randomAccessFile = new RandomAccessFile(path,"r");
        long pos =0L;
        int i = 0 ;
        while(i++<10000) {
            byte[] b = new byte[16];
            randomAccessFile.seek(pos);
            randomAccessFile.readFully(b,0,b.length);
            System.out.println(new String(b,0,b.length,"UTF-8"));
            pos++;
            if(pos>=randomAccessFile.length()){
                break;
            }

        }
        time = System.currentTimeMillis()-time;
        System.out.println("用时:"+time);



    }


    @Test
    public void test2() throws IOException {
        long time = System.currentTimeMillis();
        RandomAccessFile randomAccessFile = new RandomAccessFile(path,"r");
        byte[] b = new byte[(int)randomAccessFile.length()];
        randomAccessFile.seek(0L);
        randomAccessFile.readFully(b,0,b.length);
        int i = 0 ;
        int pos = 0;
        while(i++<10000) {

            System.out.println(new String(b,pos,14,"UTF-8"));
            pos+=14;
        }
        time = System.currentTimeMillis()-time;
        System.out.println("用时:"+time);



    }


    @Test
    public void test3(){
        String ip = String.valueOf(String.valueOf((int) ((Math.random() + 1) * 100) + "." + String.valueOf((int) ((Math.random() + 1) * 100))
                + "." + String.valueOf((int) ((Math.random() + 1) * 100)) + "." + String.valueOf((int) ((Math.random() + 1) * 100))));
        ip = "183.14.135.174";

    String s = HttpclientUtil.doGet("http://freeapi.ipip.net/118.28.8.8");
        String a = "[\"中国\",\"天津\",\"天津\",\"\",\"鹏博士\"]";


      //  s.indexOf("\"");





     //   System.out.println(s);
        String[] strings = JSONObject.parseObject(a, String[].class);
        System.out.println(strings[0]);
        //   System.out.println(resultAddress);


    }













}
