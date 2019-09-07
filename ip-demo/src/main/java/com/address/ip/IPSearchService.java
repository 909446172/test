package com.address.ip;

import com.maxmind.geoip.LookupService;

import java.io.IOException;
import java.net.InetAddress;

public class IPSearchService {
    public static void main(String[] args) throws IOException {
        String geoLiteCityFilePath = Thread.currentThread().getContextClassLoader().getResource("GeoLiteCity.dat").getPath();
        LookupService lookupService = new LookupService(geoLiteCityFilePath, LookupService.GEOIP_MEMORY_CACHE);
        int i = 0;
        long time  = System.currentTimeMillis();

        while (i++ < 10000) {

            String ip = String.valueOf(183 + "." + String.valueOf((int) ((Math.random() + 1) * 100))
                    + "." + String.valueOf((int) ((Math.random() + 1) * 100)) + "." + String.valueOf((int) ((Math.random() + 1) * 100)));

            //  String ip = "222.62.95.0";
            InetAddress inetAddress = InetAddress.getByName(ip);
            System.out.println("address:"+ip);
            System.out.println("areaCode:" + lookupService.getLocation(inetAddress).area_code);
            System.out.println("city:" + lookupService.getLocation(inetAddress).city);
            System.out.println("countryCode:" + lookupService.getLocation(inetAddress).countryCode);
            System.out.println("countryName:" + lookupService.getLocation(inetAddress).countryName);
            System.out.println("postalCode:" + lookupService.getLocation(inetAddress).postalCode);
            System.out.println("region:" + lookupService.getLocation(inetAddress).region);
            System.out.println("dma_code:" + lookupService.getLocation(inetAddress).dma_code);
            System.out.println("latitude:" + lookupService.getLocation(inetAddress).latitude);
            System.out.println("longitude:" + lookupService.getLocation(inetAddress).longitude);
            System.out.println("metro_code:" + lookupService.getLocation(inetAddress).metro_code);
            System.out.println("-----------------------------------------------------------------------------------");
        }
        time = System.currentTimeMillis() - time;
        System.out.println(time);



    }
}

