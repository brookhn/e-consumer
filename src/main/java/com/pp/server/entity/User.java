package com.pp.server.entity;


public class User {
    private String userName;
    private String address;
    private Integer age;

    public User()
    {

    }

    public User(String userName, String address, Integer age)
    {
        this.userName = userName;
        this.address = address;
        this.age = age;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {

        return address;
    }

    public Integer getAge() {
        return age;
    }

    public String getUserName() {

        return userName;
    }
    public  String toString()
    {
        return "User{" +
                "username='" + userName + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
