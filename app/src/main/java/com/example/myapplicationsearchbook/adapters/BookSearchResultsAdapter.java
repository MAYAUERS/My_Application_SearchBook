package com.example.myapplicationsearchbook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationsearchbook.R;
import com.example.myapplicationsearchbook.models.Volume;

import java.util.ArrayList;
import java.util.List;

public class BookSearchResultsAdapter extends RecyclerView.Adapter<BookSearchResultsAdapter.BookViewHolder> {

    Context context;
    List<Volume> result=new ArrayList<>();
    public BookSearchResultsAdapter(Context context, List<Volume> result) {
        this.context = context;
        this.result = result;
    }




    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        Volume volume=result.get(position);

        holder.titleTextView.setText(volume.getVolumeInfo().getTitle());
        holder.authorsTextView.setText(volume.getVolumeInfo().getSubtitle());
        holder.publishedDateTextView.setText(volume.getVolumeInfo().getPublishedDate());
       // holder.smallThumbnailImageView.setText(volume.getVolumeInfo().getTitle());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView authorsTextView;
        private TextView publishedDateTextView;
        private ImageView smallThumbnailImageView;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);


            titleTextView = itemView.findViewById(R.id.book_item_title);
            authorsTextView = itemView.findViewById(R.id.book_item_authors);
            publishedDateTextView = itemView.findViewById(R.id.book_item_publishedDate);
            // smallThumbnailImageView = itemView.findViewById(R.id.book_item_smallThumbnail)
        }
    }
}
