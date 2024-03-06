package com.workout.model;

public class User {
    private String username;
    private int id;
    private String email;

    
    public User(int id,String username,String email) {
        this.username = username;
        this.id = id;
        this.email = email;
    }

    
    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    } 

}
