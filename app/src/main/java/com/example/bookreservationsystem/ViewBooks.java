package com.example.bookreservationsystem;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewBooks extends AppCompatActivity {

//    private static final String TAG = "ViewBooks";
    DatabaseHelper myDb;
    ArrayList<String> theList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);
        myDb = new DatabaseHelper(this);

        listView = (ListView)findViewById(R.id.bookList);

        theList = new ArrayList<>();
        Cursor data = myDb.getList();

        if(data.getCount() == 0){
            Toast.makeText(ViewBooks.this, "The database is emptyt !", Toast.LENGTH_LONG).show();
        }else{
            while (data.moveToNext()){
                theList.add(data.getString(1));
            }
            ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, theList);
            listView.setAdapter(listAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                    String bookName = adapterView.getItemAtPosition(i).toString();
//                    Log.d(TAG, "onItemClick: You Clicked on " + bookName);

                    Cursor data = myDb.getBookDetails(bookName);

                    int bookID = -1;
                    String author = "";
                    String year = "";
                    while (data.moveToNext()){
                        bookID = data.getInt(0);
                        author = data.getString(1);
                        year = data.getString(2);
                    }

                    if(bookID > -1){
//                        Log.d(TAG, "onItemClick: The ID is " + bookID);
//                        Log.d(TAG, "onItemClick: The Author is " + author);
//                        Log.d(TAG, "onItemClick: The Year is " + year);
                        Intent editScreenIntent = new Intent(ViewBooks.this, UpdateBooks.class);
                        editScreenIntent.putExtra("id",bookID);
                        editScreenIntent.putExtra("bookName", bookName);
                        editScreenIntent.putExtra("author", author);
                        editScreenIntent.putExtra("year", year);
                        startActivity(editScreenIntent);
                    }else {
                        Toast.makeText(ViewBooks.this, "No ID associated with that name !", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.member_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> bookslist = new ArrayList<>();

                for (String book : theList){
                    if (book.toLowerCase().contains(newText.toLowerCase())){
                        bookslist.add(book);
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewBooks.this, android.R.layout.simple_list_item_1, bookslist);
                listView.setAdapter(adapter);

                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
