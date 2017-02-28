package com.thd.comicviet;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.thd.adapter.ComicPageAdapter;

import java.util.ArrayList;

public class ReadingZone extends AppCompatActivity {
    ListView comicPage;
    ComicPageAdapter comicPageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_zone);
        comicPage = (ListView) findViewById(R.id.lvComicPage);
    }
    /*private class LoadComicPage extends AsyncTask<String,Void,ArrayList<String>> {
        private Context context;
        private LoadComicPage (Context context) {
            this.context = context;
        }
        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            String url = strings[0];
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> list) {
            super.onPostExecute(list);
            comicPageAdapter = new ComicPageAdapter(context,list);
            comicPage.setAdapter(comicPageAdapter);
        }
    }*/
}
