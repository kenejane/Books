package com.example.user.books;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.Uri;
import android.widget.EditText;

import java.net.URI;
import java.util.List;



/**
 * Created by USER on 11/10/2017.
 */

public class BooksLoader extends AsyncTaskLoader<List<Books>> {
    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = BooksLoader.class.getName();
    EditText editText;

    /**
     * Query URL
     */
    private Uri mUrl;

    /**
     * Constructs a new {@link BooksLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public BooksLoader(Context context, Uri url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Books> loadInBackground() {
        if (mUrl == null) {
            return null;

        }

        // Perform the network request, parse the response, and extract a list of books.
        List<Books> books = QueryUtils.fetchBooksData(mUrl);
        return books;
    }
}

