package org.example;

import org.example.utilities.JwtUtil;

import java.util.HashMap;

public class Index {
    public HashMap<String, Profile> userList = new HashMap<String, Profile>();

    public String logIn(String username, String password) throws LoginFailException {

        if (userList.containsKey(username) && userList.get(username).getPassword().equals(password)) {
            return JwtUtil.generateToken(username);
        }
        throw new LoginFailException("Failed login attempt");
    }

    public void addUsers(String username, String password) {
        userList.put(username, new Profile(username, password));
    }
}