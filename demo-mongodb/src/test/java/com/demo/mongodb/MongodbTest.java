package com.demo.mongodb;


import com.demo.mongodb.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTest {

    @Autowired
    MongoDbFactory mongoDbFactory ;

    @Test
    public void test1(){

        MongoOperations mongoOperations = new MongoTemplate(mongoDbFactory);
        mongoOperations.insert(new Person("AFAF",23));



    }












}
