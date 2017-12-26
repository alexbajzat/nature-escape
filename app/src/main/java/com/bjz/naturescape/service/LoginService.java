package com.bjz.naturescape.service;

import com.bjz.naturescape.model.login.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by bjz on 10/23/2017.
 */

public interface LoginService {
    @POST(value = "/login")
    Call<Void> loginAccount(@Body LoginRequest loginRequest);
}
