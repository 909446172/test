package com.demo.mongodb.test;

import com.demo.mongodb.entity.Person;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.sun.beans.decoder.ValueObject;
import org.bson.Document;
import org.junit.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;

import java.util.Iterator;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class MongoTest {

    @Test
    public void test1() {
        MongoTemplate template = new MongoTemplate(new MongoClient(), "database");
        MongoCollection<Document> iPAddress = template.getCollection("iPAddress");
        Collation collation = Collation.of("de");

        AggregationOptions options = AggregationOptions.builder().collation(collation).build();

        Aggregation aggregation = newAggregation(
                Aggregation.count().as("age")


        ).withOptions(options);

        AggregationResults<Person> results = template.aggregate(aggregation, "person", Person.class);
        Iterator<Person> iterator = results.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }





}
