package com.bjz.naturescape.service;

import com.bjz.naturescape.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bjz on 12/26/2017.
 */

public interface PostService {
    @GET("/posts")
    Call<List<Post>> getAllPosts(@Query("added_to_favourite") String faved, @Query("title_like") String title);

    @PUT("/posts/{id}")
    Call<Post> updatePost(@Path("id") int postID, @Body() Post post);
}
