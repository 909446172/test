package com.demo.rabbit;

import com.demo.rabbit.api.StreamClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitProviderTest {


    @Autowired
    StreamClient streamClient ;

    @Autowired
    RabbitOperations rabbitOperations ;



   public String  test1() {
       return "a";
   }
    @Test
    public void test(){

        Message<String> dd1 = org.springframework.integration.support.MessageBuilder.withPayload("dafafad").build();

        boolean send = streamClient.output1111().send(dd1);
        System.out.println(send);


        //  streamClient.output().send();

//        streamClient.output().send(MessageBuilder.withPayload("it is test message.").build());
    }
}
