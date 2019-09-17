package com.demo.rabbit;

import com.demo.rabbit.api.StreamClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
//@DirtiesContext
public class RabbitConsumerTest {


    @Autowired
    StreamClient streamClient ;

    @Test
    public void test() throws IOException {


        Message<String> dd1 = org.springframework.integration.support.MessageBuilder.withPayload("dafafad").build();

        boolean send = streamClient.output1111().send(dd1);
        System.out.println(send);
        System.in.read();
        //  streamClient.output().send();

//        streamClient.output().send(MessageBuilder.withPayload("it is test message.").build());
    }
}
