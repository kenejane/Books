package com.example.user.books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by USER on 11/9/2017.
 */

public class BooksAdapter extends ArrayAdapter<Books>{

    public BooksAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Books book = getItem(position);

        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.books_list_view, parent, false);
        }

        TextView title = (TextView) view.findViewById(R.id.book_title);
        TextView author = (TextView) view.findViewById(R.id.book_authors);
        TextView description = (TextView) view.findViewById(R.id.book_description);
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        description.setText(book.getDescription());

        return view;
    }
}

