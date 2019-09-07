package com.demo.mongodb.entity;


import com.demo.mongodb.api.PersonRepository;

import java.util.List;

class SomeClient {



     private final  PersonRepository repository;




    SomeClient(PersonRepository repository) {

        this.repository = repository;
    }

    void doSomething() {

//        List<Person> persons = repository.findByLastname("Matthews");
    }


}