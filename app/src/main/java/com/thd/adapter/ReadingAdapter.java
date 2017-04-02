package com.thd.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.thd.comicviet.R;

import java.util.ArrayList;

/**
 * Created by tranh on 4/1/2017.
 */

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.BookHolder> {
    Context context;
    ArrayList<String> urls;
    public ReadingAdapter (Context context, ArrayList<String> urls) {
        this.context = context;
        this. urls = urls;
    }
    public class BookHolder extends RecyclerView.ViewHolder {
        ImageView imagePage;
        public BookHolder(View itemView) {
            super(itemView);
            imagePage = (ImageView) itemView.findViewById(R.id.ivComicPage);
        }
    }
    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_page,null);
        return new BookHolder(view);
    }
    @Override
    public void onBindViewHolder(ReadingAdapter.BookHolder holder, int position) {
        String url = urls.get(position);
        Glide.with(context)
                .load(url)
                .thumbnail(0.2f)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.imagePage);
        Log.i("Image",url);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }
}
