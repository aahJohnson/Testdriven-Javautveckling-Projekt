package org.example;

import org.example.enums.Permissions;
import org.example.utilities.JwtUtil;
import org.example.utilities.PermissionUtils;

import java.util.HashMap;

public class Index {
    public HashMap<String, Profile> userList = new HashMap<String, Profile>();

    public String logIn(String username, String password) throws LoginFailException {

        if (userList.containsKey(username) && userList.get(username).getPassword().equals(password)) {
            return JwtUtil.generateToken(username);
        }
        throw new LoginFailException("Failed login attempt");
    }

    public void addUsers(String username, String password, Permissions accountPermissions, Permissions provisionsPermissions) {
        userList.put(username, new Profile(username, password, accountPermissions, provisionsPermissions));
    }

    public void setTokenToAllUsers(){
        userList.forEach((username, user) -> {
            user.setToken(JwtUtil.generateToken(username));
        });
    }

    public void updateUserPermissionList() {
        PermissionUtils.setProfiles(userList);
    }
}