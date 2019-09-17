package com.demo.mongodb;


import com.demo.mongodb.api.FollowingRepository;
import com.demo.mongodb.entity.EnumTest;
import com.demo.mongodb.entity.Following;
import com.demo.mongodb.entity.Provider;
import com.demo.mongodb.entity.User;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTest {
//
//    @Autowired
//    UserRepository userRepository;

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    MongoMappingContext mongoMappingContext;

    @Autowired
    MongoDbFactory mongoDbFactory;

    @Autowired
    FollowingRepository followingRepository;

    @Autowired
    MongoOperations mongoOperations;


    @Test
    public void testmongo14(){
        User insert = mongoOperations.insert(new User("122232", "21", "12"));
        System.out.println(insert);


    }


    @Test
    public void testmongo13(){
        mongoOperations.find(query(new Criteria().andOperator(Criteria.where("provider.$id").is("1"))),User.class);




    }


@Test
    public void testmongo12(){

        List<User> users = mongoOperations.find(
                query(new Criteria()
                        .andOperator(
                                where("provider.$id").is(new ObjectId("5d75fd9de00eac34bc32a625"))
                        )),
                User.class);
        System.out.println(users);


    }

    @Test
    public void testmongo11() {
        Document max = new Document("_id", "$username");
        max.append("max_value", new Document("$max", "$username"));
        Document queryDoc = new Document("$group", max);
        List<Document> q = new ArrayList<>();
        q.add(queryDoc);

        MongoCursor<Document> user = mongoOperations.getCollection("user").aggregate(q).iterator();
        while (user.hasNext()) {
            System.out.println(user.next());

        }




    }



    @Test
    public void findalltest(){
        PageRequest of = PageRequest.of(5, 5,Sort.Order.desc("firstname").getDirection());
//        List<User> all = userRepository.findAllByUsernameNotNull(of);
//
//        System.out.println(of);
//        System.out.println(all);
//
//        System.out.println(all.size());


    }

    @Test
    public void dbreftest() {
        Provider provider = new Provider();
        provider.setId("5d75fd9de00eac34bc32a625");
        provider.setName("aaaaaaaa");
        for(int i = 0 ; i < 100 ; i++){
            //User save = userRepository.save(new User(""+i, ""+i, ""+i,provider));
        }



//        Optional<User> byProvId = userRepository.findByProviderId("5d75fd9de00eac34bc32a625");
//        if (byProvId.isPresent()) {
//            System.out.println(byProvId.get());
//        }


    }

    @Test
    public void testenum(){
        System.out.println(EnumTest.SPRING.toString() .equals("SPRING") );

    }


    @Test
    public void followingRepositoryTest() {
        Optional<Following> byFollowerIdAndFollowedId = followingRepository.findByFollowerIdAndFollowedId("5b18e05afef67c4c0f9d0930", "5b18cc472cc6700bc0823a4d");


        if (byFollowerIdAndFollowedId.isPresent()) {
            Following following = byFollowerIdAndFollowedId.get();
            System.out.println(following);
        }


    }


    @Test
    public void test1() {
//       User byUsername = userRepository.findByUsername("abc");
//        System.out.println(byUsername);
//        boolean b = userRepository.existsByIdOrUsername("a", "abc");
//        System.out.println(b);

//        System.out.println(userRepository);
//        System.out.println(beanFactory);
//        System.out.println(mongoMappingContext);
//        System.out.println(mongoDbFactory);

    }


}
