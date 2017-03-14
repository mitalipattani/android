package com.example.dataaseexample;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity  implements AdapterView.OnItemClickListener {
    List<Books> list;
    ArrayAdapter<String> myAdapter;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
               // CRUD OPERATIONS
        db.addBook(new Books("ANDROID APPLICATION DEVELOPMENT","abc"));
        db.addBook(new Books("IOS APPLICATION DEVELOPMENT", "xyz"));
        db.addBook(new Books("JAVA APPLICATION DEVELOPMENT", "PQR"));
        db.addBook(new Books("The waves", "Virginia Woolf"));
        db.addBook(new Books("Pride and Prejudice", "Jane Austen"));

        list = db.getAllBooks();
        List<String> listTitle = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getTitle());
        }

        myAdapter = new ArrayAdapter(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // start BookActivity with extras the book id
        Intent intent = new Intent(this, BooksActivity.class);
        intent.putExtra("book", list.get(arg2).getId());
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // get all books again, because something changed
        list = db.getAllBooks();

        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getTitle());
        }

        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
