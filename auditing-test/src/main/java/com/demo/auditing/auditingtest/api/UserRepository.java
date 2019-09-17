package com.demo.auditing.auditingtest.api;


import com.demo.auditing.auditingtest.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {




}
