package com.demo.auditing.auditingtest.event;

import com.demo.auditing.auditingtest.entity.User;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Event {



    @EventListener
    @Async
    public void testEvent(User user){
        System.out.println(user);

    }



}
