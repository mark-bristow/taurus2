package com.mbristow.taurus.services;

import com.mbristow.taurus.config.ApplicationServiceConfig;
import com.mbristow.taurus.model.User;
import com.mbristow.taurus.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApplicationService implements ApplicationServiceConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        LOGGER.info("Create user: {}", user.getId());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) throws Exception {
        Optional< User > userDb = this.userRepository.findById(user.getId());

        if (userDb.isPresent()) {
            User userUpdate = userDb.get();
            userUpdate.setId(user.getId());
            userUpdate.setName(user.getName());
            userUpdate.setAge(user.getAge());
            userUpdate.setEmail(user.getEmail());
            userRepository.save(userUpdate);
            LOGGER.info("Update user: {}", user.getId());
            return userUpdate;
        } else {
            throw new Exception("Record not found with id");
        }
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("Get all users");
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(long userId) throws Exception {
        Optional < User > userDb = this.userRepository.findById(userId);

        if (userDb.isPresent()) {
            LOGGER.info("Get user by id: {}", userId);
            return userDb.get();
        } else {
            throw new Exception("Record not found with id");
        }
    }

    @Override
    public void deleteUser(long userId) throws Exception {
        Optional < User > userDb = this.userRepository.findById(userId);

        if (userDb.isPresent()) {
            LOGGER.info("Delete user by id: {}", userId);
            this.userRepository.delete(userDb.get());
        } else {
            throw new Exception("Record not found with id");
        }
    }
}
