//package com.address.ip;
//
//import junit.framework.TestCase;
//
//public class IPtest extends TestCase {
//
//    public void testIp() {
//        //指定纯真数据库的文件名，所在文件夹
//        IPSeeker ip = new IPSeeker("GeoLiteCity.dat", "C:\\Users\\Song\\Downloads\\smzy_lbqqwry.dat");
//        //测试IP 58.20.43.13
//
////        int i = 0 ;
////        while(i++<1000){
////
////            String address = String.valueOf(183+"."+String.valueOf((int)((Math.random()+1)*100))
////                    +"."+String.valueOf((int)((Math.random()+1)*100))+"."+String.valueOf((int)((Math.random()+1)*100)));
////            System.out.println(address+"\t");
////            System.out.print(ip.getIPLocation(address
////            ).getCountry());
////            System.out.println("\t");
////            System.out.println(ip.getIPLocation(address
////            ).getArea());
////
////        }
//
//
//        System.out.println(ip.getIPLocation("185.244.12.0").getCountry() + ":" + ip.getIPLocation("185.244.12.0").getArea());
//
//
//    }
//}