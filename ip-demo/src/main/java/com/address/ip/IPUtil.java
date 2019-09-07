//package com.address.ip;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mongodb.util.JSON;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.*;
//
///**
//* 类名: IPUtil </br>
//* 描述: ip 工具类  </br>
//* 开发人员：souvc</br>
//* 创建时间：  2015-11-10 </br>
//* 发布版本：V1.0  </br>
// */
//public class IPUtil {
//
//    /**
//    * 方法名：getIpAddr</br>
//    * 详述：获取IP地址</br>
//    * 开发人员：souvc </br>
//    * 创建时间：2015-11-10  </br>
//    * @param request
//    * @return
//    * @throws
//     */
//    public static String getIpAddr(HttpServletRequest request) {
//        String ipAddress = request.getHeader("x-forwarded-for");
//        if (ipAddress == null || ipAddress.length() == 0
//                || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getHeader("Proxy-Client-IP");
//        }
//        if (ipAddress == null || ipAddress.length() == 0
//                || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ipAddress == null || ipAddress.length() == 0
//                || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getRemoteAddr();
//            if (ipAddress.equals("127.0.0.1")
//                    || ipAddress.equals("0:0:0:0:0:0:0:1")) {
//                // 根据网卡取本机配置的IP
//                InetAddress inet = null;
//                try {
//                    inet = InetAddress.getLocalHost();
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                }
//                ipAddress = inet.getHostAddress();
//            }
//        }
//        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
//        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
//                                                            // = 15
//            if (ipAddress.indexOf(",") > 0) {
//                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
//            }
//        }
//        return ipAddress;
//    }
//
//
//    /**
//    * 方法名：getIpRealAddr</br>
//    * 详述：获取真实名称</br>
//    * 开发人员：souvc</br>
//    * 创建时间：2015-11-10  </br>
//    * @param ip
//    * @return
//    * @throws
//     */
//    public static String getIpRealAddr(String ip) {
//        //指定纯真数据库的文件名，所在文件夹
//        String address = "";
//        try {
//            IPSeeker ipseeker=new IPSeeker("IpToAdd.Dat", "E:\\ipdata");
//            address = ipseeker.getIPLocation(ip).getCountry();
//        } catch(Exception e) {
//        }
//        //获得国家地区
//        return address;
//    }
//
//    /**
//    * 方法名：getArea</br>
//    * 详述：获取运营商 </br>
//    * 开发人员：souvc </br>
//    * 创建时间：2015-11-10  </br>
//    * @param ip
//    * @return
//    * @throws
//     */
//    public static String getArea(String ip) {
//        //指定纯真数据库的文件名，所在文件夹
//        String area = "";
//        try {
//            IPSeeker ipseeker=new IPSeeker("IpToAdd.Dat", "E:\\ipdata");
//            area=ipseeker.getArea(ip);
//        } catch(Exception e) {
//        }
//        return area;
//    }
//
//
//
//    /**
//    * 方法名：getIpInfo</br>
//    * 详述：通过IP获取地址(需要联网，调用淘宝的IP库)</br>
//    * 开发人员：souvc </br>
//    * 创建时间：2015-11-10  </br>
//    * @param ip
//    * @return
//    * @throws
//     */
//    public static String getIpInfo(String ip) {
//        if (ip.equals("本地")) {
//            ip = "127.0.0.1";
//        }
//        String info = "";
//        try {
//            URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
//            HttpURLConnection htpcon = (HttpURLConnection) url.openConnection();
//            htpcon.setRequestMethod("GET");
//            htpcon.setDoOutput(true);
//            htpcon.setDoInput(true);
//            htpcon.setUseCaches(false);
//
//            InputStream in = htpcon.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
//            StringBuffer temp = new StringBuffer();
//            String line = bufferedReader.readLine();
//            while (line != null) {
//                temp.append(line).append("\r\n");
//                line = bufferedReader.readLine();
//            }
//            bufferedReader.close();
//            JSONObject obj = (JSONObject) JSON.parse(temp.toString());
//            if (obj.getIntValue("code") == 0) {
//                JSONObject data = obj.getJSONObject("data");
//                info += data.getString("country") + " ";
//                info += data.getString("region") + " ";
//                info += data.getString("city") + " ";
//                info += data.getString("isp");
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return info;
//    }
//
//
//    /**
//    * 方法名：main</br>
//    * 详述：测试方法</br>
//    * 开发人员：souvc </br>
//    * 创建时间：2015-11-10  </br>
//    * @param args
//    * @throws
//     */
//    public static void main(String[] args) {
//        String country =getIpRealAddr("14.23.59.170");
//        String area =getArea("14.23.59.170");
//        String getIpInfo =getIpInfo("14.23.59.170");
//        System.out.println(country);
//        System.out.println(area);
//        System.out.println(getIpInfo);
//
//
//    }
//
//}