package org.example;

import java.util.HashMap;

public class Index {
    public HashMap<String, Profile> userList = new HashMap<String, Profile>();

    public boolean logIn(String username, String password) {

        if (userList.containsKey(username)) {
            return userList.get(username).getPassword().equals(password);
        }

        return false;
    }

    public void addUsers(String username, String password) {
        userList.put(username, new Profile(username, password));
    }
}