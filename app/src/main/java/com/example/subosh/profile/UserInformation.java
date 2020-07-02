package com.example.subosh.profile;

public class UserInformation {
public String name;
public String phone;
public Long salary;


    public UserInformation(String name, String phone,Long salary) {
        this.name = name;
        this.phone = phone;
        this.salary= salary;
}

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Long getSalary() {
        return salary;
    }
}

