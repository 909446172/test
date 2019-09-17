package com.demo.auditing.auditingtest.entity;

import lombok.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.util.Date;


@Data
public class User {

    @Id
    String id;
    @NonNull
    String name;

    @CreatedDate
    Date createTime;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public User(String name) {

        this.name = name;
    }
}
