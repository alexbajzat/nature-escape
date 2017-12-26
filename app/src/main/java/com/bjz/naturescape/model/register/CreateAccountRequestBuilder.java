package com.bjz.naturescape.model.register;

import com.bjz.naturescape.utils.Objects;

import java.util.Date;

/**
 * Created by bjz on 11/6/2017.
 */

public class CreateAccountRequestBuilder {
    private String email;
    private String password;
    private String name;
    private Date birthday;
    private String accountType;
    private String gender;

    public CreateAccountRequest build() {
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        Objects.requireNonNull(name);
        Objects.requireNonNull(birthday);
        Objects.requireNonNull(accountType);
        Objects.requireNonNull(gender);

        return new CreateAccountRequest(email, password, name, birthday, accountType, gender);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
