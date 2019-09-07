package com.demo.mongodb.controller;

import com.demo.mongodb.api.PersonRepository;
import com.demo.mongodb.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
class PersonController {

//  @Autowired
//  PersonRepository repository;
//
//  @RequestMapping(value = "/persons", method = RequestMethod.GET)
//  HttpEntity<PagedResources<Person>> persons(Pageable pageable,
//                                             PagedResourcesAssembler assembler) {
//
//    Page<Person> persons = repository.findAll(pageable);
//    return new ResponseEntity<>(assembler.toResources(persons), HttpStatus.OK);
//  }
}