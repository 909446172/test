package com.demo.mongodb.api

import com.demo.mongodb.entity.User
import org.springframework.data.repository.Repository

interface UserRepository : Repository<User, String> {

  fun findByUsername(username: String): User     

  fun findByFirstname(firstname: String?): User? 
}