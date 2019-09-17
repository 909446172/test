package com.demo.mongodb.api;

import com.demo.mongodb.entity.Provider;
import com.demo.mongodb.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
//
//public interface UserRepository extends CrudRepository<User,String> {
//    public User findByUsername(String username);
//
//    public boolean existsByIdOrUsername(String id,String usrname);
//
//    public User save(User user);
//
//    public Optional<User> findByProviderId(String id);
//
//
//    List<User> findAllByUsernameNotNull(Pageable pageable);
//}
