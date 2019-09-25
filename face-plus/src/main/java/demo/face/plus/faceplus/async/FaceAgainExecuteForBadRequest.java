package demo.face.plus.faceplus.async;

import demo.face.plus.faceplus.config.FaceSecretConfig;
import demo.face.plus.faceplus.face.FaceUrl;
import demo.face.plus.faceplus.util.HttpClientUtils;
import demo.face.plus.faceplus.util.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @Author zyy
 * @Date 2019/9/23 21:00
 * @Version 1.0
 */
@Component
public class FaceAgainExecuteForBadRequest {

    @Autowired
    FaceSecretConfig faceSecretConfig;

    @Async
    public void FaceApiRollBack(String url , Map<String,String> requestParam) {
        int i = 0;
        int number = Integer.valueOf(faceSecretConfig.getBadNumber());
        while (i < number) {
            i++;
            String s = HttpClientUtils.doPost(url, requestParam);
            ThreadUtils.sleepThread(500L);
            if (!StringUtils.isEmpty(s)) {     // TODO: 2019/9/23  如果执行次数还失败的话进行其他的策略;
              break;
            }

        }
    }


}
