package com.example.androidtaskv2.Data;

import com.example.androidtaskv2.Model.RetroPhoto;
import com.example.androidtaskv2.Model.RetroPosts;
import com.example.androidtaskv2.Model.RetroUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetData {

//Specify the request type and pass the relative URL

    @GET("/users")

//Wrap the response in a Call object with the type of the expected result

    Call<List<RetroUser>> getAllUsers();

    @GET("/users/{userId}/")
    Call<RetroUser> getUserById(@Path("userId") int userId);

    @GET("/photos")
    Call<List<RetroPhoto>> getPhotos();


    @GET("/posts")

    Call<List<RetroPosts>> getAllPosts();

}
