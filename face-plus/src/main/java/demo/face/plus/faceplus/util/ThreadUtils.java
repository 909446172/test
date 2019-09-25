package demo.face.plus.faceplus.util;

/**
 * @Author zyy
 * @Date 2019/9/23 14:26
 * @Version 1.0
 */
public class ThreadUtils {

    public static void  sleepThread(Long millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
