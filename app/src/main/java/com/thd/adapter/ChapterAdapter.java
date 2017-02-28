package com.thd.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thd.comicviet.R;
import com.thd.model.Chapter;

import java.util.List;

/**
 * Created by Trần Hải Đăng on 2/21/2017.
 * Email: tranhaidang2320@gmail.com
 */

public class ChapterAdapter extends ArrayAdapter<Chapter> {
    private Context context;
    private int resource;
    private List<Chapter> objects;

    public ChapterAdapter(Context context, int resource, List<Chapter> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChapterHolder chapterHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(this.resource,null);
            chapterHolder = new ChapterHolder();
            chapterHolder.textView = (TextView) convertView.findViewById(R.id.tvChapter);
            convertView.setTag(chapterHolder);
        }
        else chapterHolder = (ChapterHolder) convertView.getTag();
        chapterHolder.textView.setText(objects.get(position).getChapterName());
        return convertView;
    }

    private class ChapterHolder {
        TextView textView;
    }
}
