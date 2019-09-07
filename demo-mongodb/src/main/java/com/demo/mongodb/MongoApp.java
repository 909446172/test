package com.demo.mongodb;

import com.demo.mongodb.entity.Person;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.query.*;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


public class MongoApp {

  //private static final Log log = LogFactory.getLog(MongoApp.class);
  private static Logger log = Logger.getLogger(MongoApp.class);

  public static void main(String[] args) throws Exception {

    MongoOperations mongoOps = new MongoTemplate(new MongoClient(), "database");

            Query query = new Query(Criteria.where("d").is("a"));

//    mongoOps.query(Person.class)
//            .distinct("lastname")
//            .as(String.class)
//            .all();


//    TextQuery.searching(new TextCriteria().phrase("coffee cake"));
//    Collation collation = Collation.of("fr")
//
//            .strength(Collation.ComparisonLevel.secondary()
//                    .includeCase())
//
//            .numericOrderingEnabled()
//
//            .alternate(Collation.Alternate.shifted().punct())
//
//            .forwardDiacriticSort()
//
//            .normalizationEnabled();

    Collation collation = Collation.of("de");

    AggregationOptions options = AggregationOptions.builder().collation(collation).build();

    Aggregation aggregation = newAggregation(
            project("tags"),
            unwind("tags"),
            group("tags")
                    .count().as("count")
    ).withOptions(options);

   // AggregationResults<TagCount> results = mongoOps.aggregate(aggregation, "tags", TagCount.class);

  }
}