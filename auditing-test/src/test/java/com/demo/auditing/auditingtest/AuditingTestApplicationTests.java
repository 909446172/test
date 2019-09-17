package com.demo.auditing.auditingtest;

import com.demo.auditing.auditingtest.entity.User;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuditingTestApplicationTests  implements  ApplicationContextAware{

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    ApplicationContext  applicationContext ;



    @Test
    public void contextLoads() {
        applicationContext.publishEvent(new User("dfsf"));







    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
