package com.github.jcapitanmoreno.model.entity;

import java.util.Objects;

public class Admin {
    private String user;
    private String password;

    public Admin(){

    }
    public Admin(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(user, admin.user) && Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, password);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
