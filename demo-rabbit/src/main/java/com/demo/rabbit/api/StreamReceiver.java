package com.demo.rabbit.api;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {  //消息接受类

    @StreamListener("testMessageInput")  //监听testMessage这个消息队列, StreamClient类中必须定义相应的Input。
//    @SendTo("testMessageOutput")
    public void  receiver(Object message) {

        System.out.println("接收到消息：" + message);
//        return "afa";
    }
}
