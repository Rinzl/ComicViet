package com.thd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.thd.comicviet.R;

import java.util.ArrayList;

/**
 * Created by Trần Hải Đăng on 2/18/2017.
 * Email: tranhaidang2320@gmail.com
 */

public class ComicPageAdapter extends BaseAdapter {
    private ArrayList<String> imgList;
    private Context context;

    public ComicPageAdapter (Context context, ArrayList<String> list) {
        this.context = context;
        this.imgList = list;
    }

    private class ImageHolder {
        ImageView imageView;
    }
    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageHolder imageHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.comic_page,null);
            imageHolder = new ImageHolder();
            imageHolder.imageView = (ImageView) view.findViewById(R.id.ivComicPage);
            view.setTag(imageHolder);
        }
        else imageHolder = (ImageHolder) view.getTag();
        Glide.with(context)
                .load(imgList.get(i))
                .centerCrop()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageHolder.imageView);

        return view;
    }
}
