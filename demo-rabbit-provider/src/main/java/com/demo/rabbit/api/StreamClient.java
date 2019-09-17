package com.demo.rabbit.api;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {  //消息接受发送接口

    @Input("testMessageInput")
    SubscribableChannel input();  //用于接受消息

    @Output("testMessageOutput")
    MessageChannel output1111();  //用于发送消息

}
