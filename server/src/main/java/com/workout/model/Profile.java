package com.workout.model;

public class Profile {
    private int id;
    private String email;
    private int age;
    private String gender;
    private String date;
    private double weight;
    private double height;
    
    public Profile() {
    }
    
    public Profile(String email, int age, String gender, String date, double weight, double height) {
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.date = date;
        this.weight = weight;
        this.height = height;
    }
    

    public Profile(int id, String email, int age, String gender, String date, double weight, double height) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.date = date;
        this.weight = weight;
        this.height = height;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

   
}
