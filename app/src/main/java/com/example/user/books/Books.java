package com.example.user.books;

/**
 * Created by USER on 11/8/2017.
 */

public class Books {
    private String mTitle;
    private String mAuthor;
    private String mDescription;
    private String mUrl;

    public Books(String title, String author, String description, String url){
        mTitle=title;
        mAuthor=author;
        mDescription=description;
        mUrl=url;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }
}
