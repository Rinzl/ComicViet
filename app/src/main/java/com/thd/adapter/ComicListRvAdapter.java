package com.thd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thd.comicviet.R;
import com.thd.model.ComicBook;

import java.util.ArrayList;

/**
 * Created by Trần Hải Đăng on 2/12/2017.
 * Email: tranhaidang2320@gmail.com
 */

public class ComicListRvAdapter extends RecyclerView.Adapter<ComicListRvAdapter.ComicHolder> {

    private Context context;
    private ArrayList<ComicBook> cbList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ComicListRvAdapter (Context context, ArrayList<ComicBook> arr) {
        this.cbList = arr;
        this.context = context;
    }

    @Override
    public ComicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comic_item,null);
        return new ComicHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ComicHolder holder, int position) {
        ComicBook comicBook = cbList.get(position);
        holder.bookName.setText(comicBook.getBookName());
        Glide.with(context)
                .load(comicBook.getBookImageUrl())
                .thumbnail(0.2f)
                .fitCenter()
                .crossFade()
                .into(holder.bookCover);
    }

    @Override
    public int getItemCount() {
        return cbList.size();
    }

    public class ComicHolder extends RecyclerView.ViewHolder {
        TextView bookName;
        ImageView bookCover;
        public ComicHolder (final View view) {
            super(view);
            bookCover = (ImageView) view.findViewById(R.id.ivBookCover);
            bookName = (TextView) view.findViewById(R.id.tvDetailName);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null ) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(view,position);
                        }
                    }
                }
            });
        }
    }

    public ComicBook getComicBook (int position) {
        return this.cbList.get(position);
    }
}
