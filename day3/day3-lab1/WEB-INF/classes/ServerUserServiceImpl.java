package com.shaheen.service.impl;

import com.shaheen.model.*;
import com.shaheen.service.*;
import com.shaheen.repository.impl.*;
import com.shaheen.repository.*;
import com.shaheen.config.database.connection.*;

import java.util.List;
import java.util.Map;

public class ServerUserServiceImpl implements ServerUserService {

    UserRepository userRepository ;
    public ServerUserServiceImpl(ConnectToMysql connectToMysql){   
        userRepository =  new UserRepositoryImpl(connectToMysql);
    }

    @Override

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id, boolean fullData) {
        return userRepository.findById(id,fullData);
    }

    @Override
    public User findByPhoneAndPassword(String phone, String password) {
        return userRepository.findByPhoneAndPassword(phone, password);
    }
    @Override
    public User insertUser(User user, String password) {
        return userRepository.insertUser(user, password);
    }

    @Override
    public User updateUser(User user, String password) {
        return userRepository.updateUser(user, password);
    }

    @Override
    public int delete(int id) {
        return userRepository.delete(id);
    }

}