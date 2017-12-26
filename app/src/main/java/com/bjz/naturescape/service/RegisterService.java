package com.bjz.naturescape.service;

import com.bjz.naturescape.model.register.CreateAccountRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by bjz on 11/6/2017.
 */

public interface RegisterService {
    @POST
    Call<Void> createAccount(@Body CreateAccountRequest createAccountRequest);
}
