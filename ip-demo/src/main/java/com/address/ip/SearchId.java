package com.address.ip;

import com.github.jarod.qqwry.IPZone;
import com.github.jarod.qqwry.QQWry;

import java.io.IOException;

public class SearchId {

    public static void main(String[] args) throws IOException {

        QQWry wry = new QQWry();
        int one = 100;
        int two = 100;
        int three = 100;
        int four = 100;
        int i = 0;

        long time = System.currentTimeMillis();
        while (i++ < 10000) {


//            four++;
//            if(four>=255){
//                four=50;
//                three++;
//            }
//            three++;
//            if (three >= 255) {
//                three = 100;
//                two++;
//            }
//            if (two >= 255) {
//                two = 100;
//                one++;
//            }



            IPZone zone = wry.findIP(one + "." + two + "." + three + "." + four);
            System.out.print(zone.getIp());
            System.out.print("---");
            System.out.print(zone.getMainInfo());
            System.out.print("---");
            System.out.println(zone.getSubInfo());

            if (one >= 255 && two >= 255 && three >= 255 && four >= 255) {
                break;
            }


        }
        time  = System.currentTimeMillis()-time;
        System.out.println(time);

//
//        String gb18030 = new String(b, 0, b.length, "GB18030");
//        System.out.println(gb18030);

    }

}
