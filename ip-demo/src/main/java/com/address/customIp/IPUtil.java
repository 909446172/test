package com.address.customIp;


import com.address.customIp.resource.DataBlock;
import com.address.customIp.resource.DbConfig;
import com.address.customIp.resource.Util;

import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;

public class IPUtil {

    private static  int choice = 0 ;

    public static String getCityInfo(String ip ,byte[] b){
 
        //db
        String dbPath = IPUtil.class.getResource("/ip2region.db").getPath();
 
        File file = new File(dbPath);
        if ( file.exists() == false ) {
            System.out.println("Error: Invalid ip2region.db file");
        }
 
        //查询算法
        int algorithm = DbSearcher.BTREE_ALGORITHM; //B-tree
        //      algorithm =    DbSearcher.BINARY_ALGORITHM; //Binary
                        //DbSearcher.MEMORY_ALGORITYM //Memory

         algorithm = choice;


        try {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, dbPath);
 
            //define the method
            Method method = null;
            switch ( algorithm )
            {
            case DbSearcher.BTREE_ALGORITHM:
                method = searcher.getClass().getMethod("btreeSearch", String.class);
                break;
            case DbSearcher.BINARY_ALGORITHM:
                method = searcher.getClass().getMethod("binarySearch", String.class);
                break;
            case DbSearcher.MEMORY_ALGORITYM:
                method = searcher.getClass().getMethod("memorySearch", String.class,byte [].class);
                break;
                case 4:
                    method = searcher.getClass().getMethod("customSearch", String.class);
            }
 
            DataBlock dataBlock = null;
            if ( Util.isIpAddress(ip) == false ) {
                System.out.println("Error: Invalid ip address");
            }
 
            dataBlock  = (DataBlock) method.invoke(searcher, ip ,b);
 
            return dataBlock.getRegion();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return null;
    }
 
 
    public static void main(String[] args)  throws Exception{
        long time  = System.currentTimeMillis() ;
        int i  = 0 ;
        choice = 3;
        DbSearcher .raf = new RandomAccessFile(IPUtil.class.getResource("/ip2region.db").getPath(), "r");
        byte[] b = new byte[(int) DbSearcher.raf.length()];
        while(i++<10000){
            String ip = String.valueOf(String.valueOf((int) ((Math.random() + 1) * 100) + "." + String.valueOf((int) ((Math.random() + 1) * 100))
                    + "." + String.valueOf((int) ((Math.random() + 1) * 100)) + "." + String.valueOf((int) ((Math.random() + 1) * 100))));
            System.err.println(getCityInfo(ip,b));
            System.out.println(i);

        }
        System.out.println("用时:"+((System.currentTimeMillis()-time)));
        System.err.println(getCityInfo("220.248.12.158",b));
    }
}