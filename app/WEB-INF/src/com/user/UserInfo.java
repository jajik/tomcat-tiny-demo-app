package com.user;

public class UserInfo {
    int _id;
    String _firstname;
    String _lastname;
    int _age;

    public UserInfo(int id, String firstname, String lastname, int age) {
        _id = id; _firstname = firstname; _lastname = lastname; _age = age; 
    }

    public int getId() {
        return _id;
    }

    public String getFirstname() {
        return _firstname;
    }

    public String getLastname() {
        return _lastname;
    }

    public int getAge() {
        return _age;
    }
}
