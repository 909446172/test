package com.address.customIp;


import com.address.customIp.resource.*;
import com.address.util.HttpclientUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;

import static com.address.customIp.IPUtil.getCityInfo;

public class IPCustomTest {


    public static void main(String[] args)  throws Exception{

        String dbPath = IPCustomTest.class.getResource("/ip2region.db").getPath();
        RandomAccessFile      raf = new RandomAccessFile(dbPath, "r");
        byte[] buf = new byte[(int) raf.length()];
        raf.seek(0L);
        raf.readFully(buf,0, (int) buf.length);
        DbConfig config = new DbConfig();
        CustomIPOfSearch customIPOfSearch = new CustomIPOfSearch(config, buf);
            RestTemplate restTemplate = new RestTemplate();


        long time  = System.currentTimeMillis() ;
        int i  = 0 ;
        while(i++<10000){
            String ip = String.valueOf(String.valueOf((int) ((Math.random() + 1) * 100) + "." + String.valueOf((int) ((Math.random() + 1) * 100))
                    + "." + String.valueOf((int) ((Math.random() + 1) * 100)) + "." + String.valueOf((int) ((Math.random() + 1) * 100))));
      //      System.err.println(customIPOfSearch.memorySearch(ip) + "\t"+ip+"\t"+i);

            DataBlock dataBlock = customIPOfSearch.memorySearch(ip);
             String s = HttpclientUtil.doGet("http://freeapi.ipip.net/"+ip);
           // String s = "['美国']";
            String[] strings = JSONObject.parseObject(s, String[].class);
            String[] split = dataBlock.getRegion().split("\\|");
            System.out.println("------------------------------------------");
            System.out.println(i);
                System.out.print(dataBlock+"\t\t"+s+"\t\t");
            if (StringUtils.compareIgnoreCase(split[0],strings[0])==0 ) {
                System.out.println("\t\t配对成功");
            }
            System.out.println();



        }
        System.out.println("用时:"+((System.currentTimeMillis()-time)));
    }
}