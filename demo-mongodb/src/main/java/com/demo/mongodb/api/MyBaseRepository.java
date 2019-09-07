package com.demo.mongodb.api;

import com.demo.mongodb.entity.EmailAddress;
import com.demo.mongodb.entity.User;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import com.google.common.base.Optional;

@NoRepositoryBean
public  interface MyBaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

  Optional<T> findById(ID id);

  <S extends T> S save(S entity);
}

