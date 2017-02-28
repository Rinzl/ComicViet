package com.thd.model;

/**
 * Created by Trần Hải Đăng on 2/12/2017.
 * Email: tranhaidang2320@gmail.com
 */

public class ComicBook {
    private String bookName;
    private String bookUrl;
    private String bookImageUrl;

    public ComicBook() {
    }

    public ComicBook(String bookName, String bookUrl, String bookImageUrl) {
        this.bookImageUrl = bookImageUrl;
        this.bookName = bookName;
        this.bookUrl = bookUrl;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getBookImageUrl() {
        return bookImageUrl;
    }

    public void setBookImageUrl(String bookImageUrl) {
        this.bookImageUrl = bookImageUrl;
    }
}
