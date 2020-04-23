package com.pc.msa.oauth2.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

//@Entity
public class AppUser {

   // @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@Column
    private String username;
    //@Column
    @JsonIgnore
    private String password;
    //@Column
    private String role;
    //@Column
    private long salary;
    //@Column
    private int age;

    public AppUser() {
    }

    public AppUser(long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
