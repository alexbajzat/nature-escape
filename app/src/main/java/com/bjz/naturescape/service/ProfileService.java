package com.bjz.naturescape.service;

import com.bjz.naturescape.model.Profile;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by bjz on 12/26/2017.
 */

public interface ProfileService {
    @GET("/profile")
    Call<Profile> getProfile();
}
