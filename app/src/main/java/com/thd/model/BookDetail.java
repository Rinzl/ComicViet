package com.thd.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Trần Hải Đăng on 2/20/2017.
 * Email: tranhaidang2320@gmail.com
 */

public class BookDetail extends ComicBook implements Serializable {
    private String nd;
    private String author;
    private ArrayList<Chapter> chapterList;


    public BookDetail() {
    }

    public BookDetail(String bookName, String bookUrl, String bookImageUrl, String nd, String author, ArrayList<Chapter> chapterList) {
        super(bookName, bookUrl, bookImageUrl);
        this.nd = nd;
        this.author = author;
        this.chapterList = chapterList;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(ArrayList<Chapter> chapterList) {
        this.chapterList = chapterList;
    }
}
