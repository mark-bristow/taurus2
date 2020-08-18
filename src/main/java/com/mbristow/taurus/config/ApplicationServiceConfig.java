package com.mbristow.taurus.config;

import com.mbristow.taurus.model.User;

import java.util.List;

public interface ApplicationServiceConfig {
    User createUser(User user);

    User updateUser(User user) throws Exception;

    List< User > getAllUsers();

    User getUserById(long userId) throws Exception;

    void deleteUser(long id) throws Exception;
}
