package com.example.user.books;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Books>> {
    /**
         * URL for earthquake data from the USGS dataset
         */
        private static final String GOOGLEBOOKS_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=novels%20by%20african%20authors";


        private static final String LOG_TAG = MainActivity.class.getName();

        /**
         * Constant value for the earthquake loader ID. We can choose any integer.
         * This really only comes into play if you're using multiple loaders.
         */
        private static final int BOOKS_LOADER_ID = 1;

        /**
         * Adapter for the list of Books
         */
        private BooksAdapter mAdapter;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Find a reference to the {@link ListView} in the layout
            LinearLayout booksListView = (LinearLayout) findViewById(R.id.list_view);

            // Create a new adapter that takes the list of books as input
            mAdapter = new BooksAdapter(this, new ArrayList<Books>());

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            booksListView.setAdapter(mAdapter);

            // Set an item click listener on the ListView, which sends an intent to a web browser
            // to open a website with more information about the selected earthquake.
            ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    // Find the current earthquake that was clicked on
                    Earthquake currentEarthquake = mAdapter.getItem(position);

                    // Convert the String URL into a URI object (to pass into the Intent constructor)
                    Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                    // Create a new intent to view the earthquake URI
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                    // Send the intent to launch a new activity
                    startActivity(websiteIntent);
                }
            });

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(BOOKS_LOADER_ID, null, this);
        }

        @Override
        public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
            // Create a new loader for the given URL
            return new EarthquakeLoader(this, GOOGLEBOOKS_REQUEST_URL);
        }

        @Override
        public void onLoadFinished(Loader<List<Books>> loader, List<Books> books) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();
            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (books != null && !books.isEmpty()) {
                mAdapter.addAll(books);
            }
        }

        @Override
        public void onLoaderReset(Loader<Books> loader) {
            // Loader reset, so we can clear out our existing data.
            mAdapter.clear();
        }
    }





