package com.bjz.naturescape.service;

import com.bjz.naturescape.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by bjz on 12/26/2017.
 */

public interface PostService {
    @GET("/posts")
    Call<List<Post>> getAllPosts();
}
