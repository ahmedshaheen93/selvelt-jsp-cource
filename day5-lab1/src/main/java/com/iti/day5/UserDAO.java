package com.iti.day5;

public interface UserDAO {
    User login(String username, String password);

    User register(User user);
}
