package com.mbristow.taurus.repository;

import com.mbristow.taurus.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long > {

}
