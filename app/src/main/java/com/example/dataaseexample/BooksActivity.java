package com.example.dataaseexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by naimesh on 3/13/2017.
 */
public class BooksActivity extends Activity {
    TextView bookTitle;
    TextView authorName;
    Books selectedBook;
    DatabaseHandler db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        bookTitle = (TextView) findViewById(R.id.title);
        authorName = (TextView) findViewById(R.id.author);
        // get the intent that we have passed
        Intent intent = getIntent();
        int id = intent.getIntExtra("book", -1);

        // open the database of the application context
        db = new DatabaseHandler(getApplicationContext());

        // read the book with "id" from the database
        selectedBook = db.readBook(id);
        initializeViews();
    }

    public void initializeViews() {
        bookTitle.setText(selectedBook.getTitle());
        authorName.setText(selectedBook.getAuthor());
    }
    public void update(View v) {
        Toast.makeText(getApplicationContext(), "This book is updated.", Toast.LENGTH_SHORT).show();
        selectedBook.setTitle(((EditText) findViewById(R.id.titleEdit)).getText().toString());
        selectedBook.setAuthor(((EditText) findViewById(R.id.authorEdit)).getText().toString());
        // update book with changes
        db.updateBook(selectedBook);
        finish();
    }
    public void delete(View v) {
        Toast.makeText(getApplicationContext(), "This book is deleted.", Toast.LENGTH_SHORT).show();

        // delete selected book
        db.deleteBook(selectedBook);
        finish();
    }

    }
