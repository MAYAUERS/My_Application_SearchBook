package com.example.myapplicationsearchbook.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationsearchbook.R;
import com.example.myapplicationsearchbook.adapters.BookSearchResultsAdapter;
import com.example.myapplicationsearchbook.models.Volume;
import com.example.myapplicationsearchbook.models.VolumeResponse;
import com.example.myapplicationsearchbook.viewmodels.BookSearchViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class BookSearchFragment extends Fragment {
    private TextInputEditText keywordEditText, authorEditText;
    RecyclerView recyclerView;
    Button searchbtn;

    private BookSearchResultsAdapter adapter;
    private BookSearchViewModel viewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        viewModel= ViewModelProviders.of(this).get(BookSearchViewModel.class);
        viewModel.init();
        viewModel.getVolumeResponseLiveData().observe(this,new Observer<VolumeResponse>() {
            @Override
            public void onChanged(VolumeResponse volumeResponse) {
                if (volumeResponse!=null){
                    displaydata(volumeResponse);
                }
            }
        });


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_booksearch,container,false);

        recyclerView =view.findViewById(R.id.fragment_booksearch_searchResultsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        keywordEditText=view.findViewById(R.id.fragment_booksearch_keyword);
        authorEditText=view.findViewById(R.id.fragment_booksearch_author);
        searchbtn=view.findViewById(R.id.fragment_booksearch_search);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seachBook();
            }
        });

        return view;
    }

    private void seachBook() {
        String key=keywordEditText.getEditableText().toString();
        String author=authorEditText.getEditableText().toString();

        viewModel.searchBookData(key,author);
    }

    private void displaydata(VolumeResponse volumeResponse) {
        List<Volume> list=volumeResponse.getItems();
        adapter=new BookSearchResultsAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
    }
}
