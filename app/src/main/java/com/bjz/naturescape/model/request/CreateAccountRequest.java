package com.bjz.naturescape.model.request;

import java.util.Date;

/**
 * Created by bjz on 11/6/2017.
 */

public class CreateAccountRequest {
    private final String email;
    private final String password;
    private final String name;
    private final Date birthday;
    private final String accountType;
    private final String gender;

    CreateAccountRequest(String email, String password, String name, Date birthday, String accountType, String gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.accountType = accountType;
        this.gender = gender;
    }

    public static CreateAccountRequestBuilder Builder() {
        return new CreateAccountRequestBuilder();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getGender() {
        return gender;
    }
}
