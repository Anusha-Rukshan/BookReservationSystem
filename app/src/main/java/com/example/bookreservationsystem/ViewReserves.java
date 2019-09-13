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
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewReserves extends AppCompatActivity {

    private static final String TAG = "ViewReserves";

    DatabaseHelper db;
    ArrayList<String> theList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reserves);

        db = new DatabaseHelper(this);

        listView = (ListView)findViewById(R.id.list);

        theList = new ArrayList<>();
        Cursor data = db.getReservedListContents();

        if(data.getCount() == 0){
            Toast.makeText(ViewReserves.this, "The database is emptyt !", Toast.LENGTH_LONG).show();
        }else{
            while (data.moveToNext()){
                theList.add(data.getString(2));
            }
            ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, theList);
            listView.setAdapter(listAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                    String bookName = adapterView.getItemAtPosition(i).toString();
                    Log.d(TAG, "onItemClick: You Clicked on " + bookName);

                    Cursor data = db.getReserved(bookName);

                    int reservedID = -1;
                    String memberId="";
                    String bookId = "";
                    String date = "";
                    while (data.moveToNext()){
                        reservedID = data.getInt(0);
                        memberId = data.getString(1);
                        bookId = data.getString(2);
                        date = data.getString(3);
                    }

                    if(reservedID > -1){
                        Log.d(TAG, "onItemClick: The Reserve ID is " + reservedID);
                        Log.d(TAG, "onItemClick: The Member Id is " + memberId);
                        Log.d(TAG, "onItemClick: The Date is " + date);
                        Log.d(TAG, "onItemClick: The Book ID is " + bookId);

                        Intent editScreenIntent = new Intent(ViewReserves.this, UpdateReserves.class);
                        editScreenIntent.putExtra("id",reservedID);
                        editScreenIntent.putExtra("memId", memberId);
                        editScreenIntent.putExtra("bookId", bookId);
                        editScreenIntent.putExtra("date", date);
                        startActivity(editScreenIntent);
                    }else {
                        Toast.makeText(ViewReserves.this, "No ID associated with that name !", Toast.LENGTH_LONG).show();
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
                ArrayList<String> reservelist = new ArrayList<>();

                for (String res : theList){
                    if (res.toLowerCase().contains(newText.toLowerCase())){
                        reservelist.add(res);
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewReserves.this, android.R.layout.simple_list_item_1, reservelist);
                listView.setAdapter(adapter);

                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
