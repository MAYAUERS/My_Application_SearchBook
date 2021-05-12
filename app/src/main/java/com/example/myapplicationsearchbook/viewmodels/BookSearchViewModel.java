package com.example.myapplicationsearchbook.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplicationsearchbook.models.VolumeResponse;
import com.example.myapplicationsearchbook.repositories.bookRepository;

public class BookSearchViewModel extends AndroidViewModel {

    private bookRepository bookRepository1;
    private LiveData<VolumeResponse> volumeResponseLiveData;


    public BookSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        bookRepository1=new bookRepository();
        volumeResponseLiveData=bookRepository1.getBookSearchResponse();
    }

    public void searchBookData(String keyword,String author){
        bookRepository1.searchBookData(keyword,author);
    }

    public LiveData<VolumeResponse>getVolumeResponseLiveData(){
        return volumeResponseLiveData;
    }

}
