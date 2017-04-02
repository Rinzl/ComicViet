package com.thd.comicviet;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thd.adapter.ReadingAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class ReadingZone extends AppCompatActivity {
    RecyclerView rvImagePage;
    ReadingAdapter readingAdapter;
    LoadComicPage loadComicPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_zone);
        rvImagePage = (RecyclerView) findViewById(R.id.rvImagePage);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvImagePage.setLayoutManager(layoutManager);
        rvImagePage.setHasFixedSize(true);
        rvImagePage.setItemAnimator(new DefaultItemAnimator());
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("img","error");
        loadComicPage = new LoadComicPage(this);
        loadComicPage.execute(url);

    }
    private class LoadComicPage extends AsyncTask<String,Void,ArrayList<String>> {
        private Context context;
        private ProgressDialog progressDialog;
        private LoadComicPage (Context context) {
            this.context = context;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Loading Comic...");
            progressDialog.setMessage("Please wait");
            progressDialog.show();
        }
        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            ArrayList<String> urls = new ArrayList<>();
            String url = strings[0];
            String imageUrl;
            try {
                Document doc = Jsoup.connect(url).get();
                Element e = doc.getElementsByTag("Script").get(11);
                String temp = e.toString();
                imageUrl = temp.substring(temp.indexOf("'")+1, temp.indexOf(";")-1);
                // each url in url dataset
                for(String a : imageUrl.replace("|","-").split("-")) {
                    urls.add(a);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return urls;
        }

        @Override
        protected void onPostExecute(ArrayList<String> list) {
            super.onPostExecute(list);
            readingAdapter = new ReadingAdapter(context,list);
            rvImagePage.setAdapter(readingAdapter);
            progressDialog.dismiss();
        }
    }
}
