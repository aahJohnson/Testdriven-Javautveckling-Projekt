package org.example;

import org.example.enums.Permissions;

public class Profile {

    private String token;
    private String password;
    private Permissions accountPermissions;
    private Permissions provisionsCalcPermissions;

    public Profile(String username, String password, Permissions accountPermissions, Permissions provisionsCalcPermissions) {
        this.password = password;
        this.accountPermissions = accountPermissions;
        this.provisionsCalcPermissions = provisionsCalcPermissions;
    }

    public Permissions getAccountPermissions() {
        return accountPermissions;
    }

    public Permissions getProvisionsCalcPermissions() {
        return provisionsCalcPermissions;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}