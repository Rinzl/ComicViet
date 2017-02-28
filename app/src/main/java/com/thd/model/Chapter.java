package com.thd.model;

/**
 * Created by Trần Hải Đăng on 2/21/2017.
 * Email: tranhaidang2320@gmail.com
 */

public class Chapter {
    private String chapterName;
    private String chapterUrl;

    public Chapter(String chapterName, String chapterUrl) {
        this.chapterName = chapterName;
        this.chapterUrl = chapterUrl;
    }

    public String getChapterName() {

        return chapterName;
    }

    public String getChapterUrl() {
        return chapterUrl;
    }
}
