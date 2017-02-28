package com.thd.comicviet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.thd.adapter.ComicListRvAdapter;
import com.thd.model.ComicBook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvComicList;
    ComicListRvAdapter adapter;
    LoadComic loadComic;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this,ComicBookDetail.class);
        addRecycleView();
        RecyclerView.LayoutManager layout = new GridLayoutManager(this,2);
        rvComicList.setLayoutManager(layout);
        rvComicList.setHasFixedSize(true);
        rvComicList.setItemAnimator(new DefaultItemAnimator());
        loadComic = new LoadComic(this);

        String url = "http://vietcomic.net/truyen-tranh-hay?type=truyenmoi&page=1";
        loadComic.execute(url);
    }

    private void addRecycleView() {
        rvComicList = (RecyclerView) findViewById(R.id.rvComicList);
    }

    private class LoadComic extends AsyncTask<String,Void,ArrayList<ComicBook>> {
        ProgressDialog dialog;
        Activity activity;
        private LoadComic (Activity activity) {
            this.activity = activity;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(activity);
            dialog.setMessage("Đang tải thông tin....");
            dialog.setTitle("Loading");
            dialog.show();
        }

        @Override
        protected ArrayList<ComicBook> doInBackground(String... strings) {
            ArrayList<ComicBook> cb = new ArrayList<>();
            String url = strings[0];
            try {
                Document document = Jsoup.connect(url).get();
                Elements elements = document.getElementsByClass("list-truyen-item-wrap").select("a[href]:has(img)");
                // BookName : test.substring(test.indexOf("alt=")+5,test.length()-7)
                // BookUrl : test.substring(test.indexOf("=")+2,test.indexOf(" ",5)-1)
                // BookImageUrl test.substring(test.indexOf("src=")+5,test.indexOf("alt")-2)))
                for (Element e : elements) {
                    String test = e.toString();
                    cb.add(new ComicBook(test.substring(test.indexOf("alt=")+5,test.length()-7),
                            test.substring(test.indexOf("=")+2,test.indexOf(" ",5)-1),
                            test.substring(test.indexOf("src=")+5,test.indexOf("alt")-2)));
                    Log.i("LOL",test.substring(test.indexOf("src=")+5,test.indexOf("alt")-2));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return cb;
        }

        @Override
        protected void onPostExecute(final ArrayList<ComicBook> comicBookArrayList) {
            super.onPostExecute(comicBookArrayList);
            adapter = new ComicListRvAdapter(activity,comicBookArrayList);
            rvComicList.setAdapter(adapter);
            adapter.setOnItemClickListener(new ComicListRvAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(activity,adapter.getComicBook(position).getBookName(),Toast.LENGTH_SHORT).show();
                    intent.putExtra("url",comicBookArrayList.get(position).getBookUrl());
                    startActivity(intent);
                }
            });
            dialog.dismiss();
        }
    }

}
