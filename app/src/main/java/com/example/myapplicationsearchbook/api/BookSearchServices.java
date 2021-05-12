package com.example.myapplicationsearchbook.api;

import com.example.myapplicationsearchbook.models.VolumeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookSearchServices {

    @GET("/books/v1/volumes")
    Call<VolumeResponse> searchBookData(@Query("q") String query,@Query("inauthor") String author);

}
