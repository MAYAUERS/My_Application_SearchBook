package com.example.myapplicationsearchbook.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplicationsearchbook.api.BookSearchServices;
import com.example.myapplicationsearchbook.models.VolumeResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class bookRepository {
    private static final String BOOK_SEARCH_SERVICE_BASE_URL = "https://www.googleapis.com/";

    private BookSearchServices bookSearchServices;
    private MutableLiveData<VolumeResponse> volumeResponseMutableLiveData;


    public bookRepository(){

        volumeResponseMutableLiveData=new MutableLiveData<>();

        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        bookSearchServices=new retrofit2.Retrofit.Builder()
                .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookSearchServices.class);

    }

    public void searchBookData(String keyword,String author){
        bookSearchServices.searchBookData(keyword,author)
                .enqueue(new Callback<VolumeResponse>() {
                    @Override
                    public void onResponse(Call<VolumeResponse> call, Response<VolumeResponse> response) {
                        volumeResponseMutableLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<VolumeResponse> call, Throwable t) {
                        volumeResponseMutableLiveData.postValue(null);
                    }
                });
    }

    public LiveData<VolumeResponse> getBookSearchResponse(){
        return volumeResponseMutableLiveData;
    }
}
