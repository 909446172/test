package com.demo.mongodb;


import com.demo.mongodb.api.FollowingRepository;
import com.demo.mongodb.entity.*;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.annotation.Validated;

import java.util.*;

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
    public void testMongo20() {
        List<Provider> providers = new ArrayList<>();
        providers.add(new Provider("aaa", "aaa"));
        providers.add(new Provider("bbb", "bbb"));
        providers.add(new Provider("ccc", "ccc"));
        mongoOperations.insert(new User("11", "11", providers));
        mongoOperations.updateFirst(Query.query(Criteria.where("id").is("5d82fc7b3b65773884cd50d1")), Update.update("provider", providers),User.class);


    }

    @Test
    public void testMongo19() {
        List<Provider> providers = new ArrayList<>();
        providers.add(new Provider("aaa", "aaa"));
        providers.add(new Provider("bbb", "bbb"));
        providers.add(new Provider("ccc", "ccc"));
        mongoOperations.insert(new User("11", "11", providers));
        mongoOperations.updateFirst(Query.query(Criteria.where("id").is("5d82fc7b3b65773884cd50d1")), Update.update("provide", providers),User.class);


    }

    @Test
    public void testmongo18() {
        List<Person> age = mongoOperations.find(Query.query(new Criteria().andOperator(Criteria.where("age").gt(10))
                .andOperator(Criteria.where("age").lt(20))



                )
                , Person.class);
        System.out.println(age);


    }

    @Test
    public void testmongo14() {
//        FaceInfo faceInfo = faceInfoService.insertUserFaceInfo(new FaceInfo("3", "imgurl", 18, "male", 80f, 1, "token", new Date(), new Date()));
//        FaceInfo faceInfo1 = faceInfoService.insertUserFaceInfo(new FaceInfo("2", "imgurl", 18, "male", 80f, 1, "token", new Date(), new Date()));
//        User insert = mongoOperations.insert(new User( "21", "12",Arrays.asList(providers)));
    //    System.out.println(insert);

    }

    @Test
    public void testmongo16() {
        Provider name = mongoOperations.insert(new Provider("234", "name"));
        System.out.println(name);

    }
    @Test
    public void testmongo15(){
        List<User> firstname = mongoOperations.find(query(where("firstname").is("21")), User.class);
        System.out.println(firstname);

    }


    @Test
    public void locationtest4(){
        Box box = new Box(new Point(10, 10), new Point(50, 50));
        List<Local> venues =
                mongoOperations.find(new Query(Criteria.where("location").within(box)), Local.class);
        for (Local ll : venues) {
            System.out.println(ll);

        }

    }

@Test
    public void locationtest3(){
        Point point = new Point(73.99171, 40.738868);
        List<Local> venues =
                mongoOperations.find(new Query(Criteria.where("location").near(point).maxDistance(10)), Local.class);
    for (Local ll : venues) {
        System.out.println(ll);

    }

    }


    @Test
    public void locationTest1() {


        Point location = new Point(73.99171, 40.738868);
        NearQuery query = NearQuery.near(location).maxDistance(new Distance(10, Metrics.NEUTRAL));

        GeoResults<Local>  gg= mongoOperations.geoNear(query, Local.class);
        Iterator<GeoResult<Local>> iterator = gg.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }

    @Test
    public void locationtest2() {

        mongoOperations.dropCollection(Local.class);


    }

    @Test
    public void locationTest() {


        for (int i = 0; i < 100; i++) {
            System.out.println(new Local(i + "", new Location(((int) ((Math.random()) * 10000) / 100.0), ((int) ((Math.random()) * 10000 - 20) / 100.0))));
            mongoOperations.insert(new Local(i + "", new Location(((int) ((Math.random()) * 10000) / 100.0), ((int) ((Math.random()) * 10000 - 20) / 100.0))));
        }

    }

    @Test
    public void testmongo13() {
        mongoOperations.find(query(new Criteria().andOperator(Criteria.where("provider.$id").is("1"))), User.class);


    }


    @Test
    public void testmongo12() {

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
    public void findalltest() {
        PageRequest of = PageRequest.of(5, 5, Sort.Order.desc("firstname").getDirection());
//        List<User> all = userRepository.findAllByUsernameNotNull(of);
//
//        System.out.println(of);
//        System.out.println(all);
//
//        System.out.println(all.size());


    }

    @Test
    public void dbreftest() {
//        Provider provider = new Provider();
//        provider.setId("5d75fd9de00eac34bc32a625");
//        provider.setName("aaaaaaaa");
//        for (int i = 0; i < 100; i++) {
//            //User save = userRepository.save(new User(""+i, ""+i, ""+i,provider));
//        }


//        Optional<User> byProvId = userRepository.findByProviderId("5d75fd9de00eac34bc32a625");
//        if (byProvId.isPresent()) {
//            System.out.println(byProvId.get());
//        }


    }

    @Test
    public void testenum() {
        System.out.println(EnumTest.SPRING.toString().equals("SPRING"));

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
