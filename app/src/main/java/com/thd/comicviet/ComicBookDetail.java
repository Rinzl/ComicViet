package com.thd.comicviet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thd.adapter.ChapterAdapter;
import com.thd.model.BookDetail;
import com.thd.model.Chapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class ComicBookDetail extends AppCompatActivity {
    TextView tvBookName, tvAuthor, tvND;
    ImageView ivBookDetailCover;
    ChapterAdapter chapterAdapter;
    ListView lvChapter;
    Intent intent;
    LoadDetail loadDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_book_detail);
        addView();

        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url","error");
        loadDetail = new LoadDetail(this);
        loadDetail.execute(url);
    }

    private void addView() {
        tvAuthor = (TextView) findViewById(R.id.tvDetailAuthor);
        tvBookName = (TextView) findViewById(R.id.tvDetailName);
        tvND = (TextView) findViewById(R.id.tvDetailND);
        ivBookDetailCover = (ImageView) findViewById(R.id.ivBookDetailCover);
        lvChapter = (ListView) findViewById(R.id.lvChapter);
    }
    // AsyncTask load detail comic page
    private class LoadDetail extends AsyncTask<String,Void,BookDetail> {
        ProgressDialog progressDialog;
        Context context;
        private LoadDetail (Context context) {
            this.context = context;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Loading");
            progressDialog.show();
        }

        @Override
        protected BookDetail doInBackground(String... strings) {
            String url = strings[0];
            ArrayList<Chapter> chapters = new ArrayList<>();
            BookDetail bookDetail = new BookDetail();
            try {
                Document doc = Jsoup.connect(url).get();
                Elements e = doc.getElementsByClass("manga-info-top");
                Elements e1 = doc.getElementsByClass("manga-info-content").select("p:has(span)");
                Elements e2 = doc.getElementsByClass("row").select("a[href]:not([rel])");
                String urls = e.select("img[src]:not(style)").toString();
                String author = e.select("li:has(a[title])").toString();
                String bookName = e.select("h1").toString();
                String nd = e1.toString();
                bookDetail.setNd(nd.substring(nd.lastIndexOf("span")+5,nd.lastIndexOf("<")));
                bookDetail.setAuthor(author.substring(author.indexOf(">",author.indexOf("href"))+1,author.length()-9));
                bookDetail.setBookImageUrl(urls.substring(urls.indexOf("src")+5,urls.indexOf("alt")-2));
                bookDetail.setBookName(bookName.substring(4,bookName.lastIndexOf("h1")-2));
                for(Element element : e2) {
                    String temp = element.toString();
                    // chapter name : temp.indexOf("_blank")+8,temp.length()-4)
                    // chapter url : temp.substring(9,temp.indexOf("title")-2)
                    chapters.add(new Chapter(temp.substring(temp.indexOf("_blank")+8,temp.length()-4),temp.substring(9,temp.indexOf("title")-2)));
                }
                bookDetail.setChapterList(chapters);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return bookDetail;
        }

        @Override
        protected void onPostExecute(final BookDetail bookDetail) {
            super.onPostExecute(bookDetail);
            tvBookName.setText(bookDetail.getBookName());
            tvAuthor.setText(bookDetail.getAuthor());
            tvND.setText(bookDetail.getNd());
            ivBookDetailCover.setBackgroundColor(Color.parseColor("#FFFFFF"));
            Glide.with(context)
                    .load(bookDetail.getBookImageUrl())
                    .thumbnail(0.3f)
                    .centerCrop()
                    .crossFade()
                    .placeholder(R.drawable.placeholder)
                    .into(ivBookDetailCover);
            progressDialog.dismiss();
            chapterAdapter = new ChapterAdapter(context,R.layout.chapter,bookDetail.getChapterList());
            lvChapter.setAdapter(chapterAdapter);
            lvChapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Chapter chapter = bookDetail.getChapterList().get(i);
                    Toast.makeText(context,bookDetail.getChapterList().get(i).getChapterName(),Toast.LENGTH_LONG).show();
                    intent = new Intent(ComicBookDetail.this,ReadingZone.class);
                    intent.putExtra("img",chapter.getChapterUrl());
                    startActivity(intent);
                }
            });
        }
    }
}
