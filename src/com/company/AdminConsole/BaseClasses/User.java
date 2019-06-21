package com.company.AdminConsole.BaseClasses;

import com.company.AdminConsole.Commons.BCrypt;

public class User {

    private int id;
    private String userName;
    private String email;
    private String password;
    private int user_group_id;

    public User(String userName, String email, String password, int user_group_id) {
        this.userName = userName;
        this.email = email;
        this.user_group_id = user_group_id;
        this.password = hashPassword(password);
    }

    public User() {
    }

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String plainTextPassword) {
        this.password = BCrypt.hashpw(plainTextPassword,BCrypt.gensalt());
    }

    public int getUser_group_id() {
        return user_group_id;
    }

    public void setUser_group_id(int user_group_id) {
        this.user_group_id = user_group_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

