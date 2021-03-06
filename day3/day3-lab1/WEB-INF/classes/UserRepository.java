package com.shaheen.repository;

import com.shaheen.model.*;

import java.util.List;
import java.sql.*;


public interface UserRepository {


    /**
     * get all users form database
     *
     * @return list<User>
     */
    public List<User> findAll();

    /**
     * get all users form database
     *
     * @return list<User>
     */
    public List<User> search(String name);
    
/**
     * get all users form database
     *
     * @return list<User>
     */
    public List<User> excuteQuery(String query) throws SQLException;
    
    public int excuteUpdate(String query) throws SQLException;

    /**
     * get user form database using his id
     *
     * @param id       user id
     * @param fullData full user data such as all friends and all groups
     * @return User
     */
    public User findById(int id, boolean fullData);

    /**
     * sing in by phone and password
     *
     * @param phone    user phone
     * @param password user password
     * @return User if exist on database Or null if not exist
     */
    public User findByPhoneAndPassword(String phone, String password);

    /**
     * insert user to database
     *
     * @param user user to insert
     * @return id of inserted user or (-1) if failed to insert
     */
    public User insertUser(User user, String password);

    /**
     * update user to database
     *
     * @param user user to update
     * @return integer number of row updated
     */
    public User updateUser(User user, String password);

    /**
     * delete user from database
     *
     * @param id user id to be deleted
     * @return integer number of row deleted or 0 if not deleted
     */
    public int delete(int id);
}
