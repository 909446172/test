package com.address.customIp.resource;

import com.mongodb.MongoClient;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;

public class PersistentationIPByMongodb {


    public static long ip2long( String ip )
    {
        String[] p = ip.split("\\.");
        if ( p.length != 4 ) return 0;

        int p1 = ((Integer.valueOf(p[0]) << 24) & 0xFF000000);
        int p2 = ((Integer.valueOf(p[1]) << 16) & 0x00FF0000);
        int p3 = ((Integer.valueOf(p[2]) <<  8) & 0x0000FF00);
        int p4 = ((Integer.valueOf(p[3]) <<  0) & 0x000000FF);

        return ((p1 | p2 | p3 | p4) & 0xFFFFFFFFL);
    }

    public static IPAddress setIpCode(IPAddress ipAddress ){
        long l = ip2long(ipAddress.getIp1());
        long l1 = ip2long(ipAddress.getIp2());
        ipAddress.setIp1Code(l);
        ipAddress.setIp2Code(l1);
        return ipAddress;
    }


    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Song\\Downloads\\ip2region-1.9.0-release\\ip2region-1.9.0-release\\data\\ip.merge.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        MongoOperations mongoOps = new MongoTemplate(new MongoClient(), "database");
        IPAddress ipAddress = new IPAddress();
        String s = bufferedReader.readLine();
        String[] split1 = s.split("\\|");
        ipAddress.setIp1(split1[0]);
        ipAddress.setIp2(split1[1]);
        ipAddress.setCountry(split1[split1.length-1]);
        ipAddress.setRegion(split1[split1.length-2]);
        IPAddress ipAddress1 = setIpCode(ipAddress);
        mongoOps.save(ipAddress1);

        int i = 0 ;
        while(true){
            String s1 = bufferedReader.readLine();
            System.out.println(s1);

            String[] split = s1.split("\\|");

            ipAddress.setIp1(split[0]);
            ipAddress.setIp2(split[1]);
            if(split[2].equals("0")){
                ipAddress.setCountry(split[3]);
            }else{
                ipAddress.setCountry(split[2]);
            }
            if(split[4].equals("0")){
                ipAddress.setRegion(split[5]);
            }else{
                ipAddress.setRegion(split[4]);
            }
            IPAddress ipAddress11 = setIpCode(ipAddress);
            mongoOps.save(ipAddress11);

            if (StringUtils.isEmpty(s1)) {
                break;
            }









        }
//        Query query = new Query(Criteria.where("d").is("a"));


    }




}
