package com.example.bookreservationsystem;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
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

public class ViewMembers extends AppCompatActivity {

    private static final String TAG = "ViewMembers";

//    private Button temp;
    DatabaseHelper myDb;
    ArrayList<String> theList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_members);
        myDb = new DatabaseHelper(this);

        listView = (ListView)findViewById(R.id.memberList);

        theList = new ArrayList<>();
        Cursor data = myDb.getListContents();

        if(data.getCount() == 0){
            Toast.makeText(ViewMembers.this, "The database is emptyt !", Toast.LENGTH_LONG).show();
        }else{
            while (data.moveToNext()){
                theList.add(data.getString(1));
            }
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                        String name = adapterView.getItemAtPosition(i).toString();
                        Log.d(TAG, "onItemClick: You Clicked on " + name);

                        Cursor data = myDb.getMemberDetails(name);

                        int memberID = -1;
                        String nic = "";
                        String mob = "";
                        while (data.moveToNext()){
                            memberID = data.getInt(0);
                            nic = data.getString(1);
                            mob = data.getString(2);
                        }

                        if(memberID > -1){
                        Log.d(TAG, "onItemClick: The ID is " + memberID);
                        Log.d(TAG, "onItemClick: The Nic is " + nic);
                        Log.d(TAG, "onItemClick: The Mobile is " + mob);
                            Intent editScreenIntent = new Intent(ViewMembers.this, UpdateMembers.class);
                            editScreenIntent.putExtra("id",memberID);
                            editScreenIntent.putExtra("name", name);
                            editScreenIntent.putExtra("nic", nic);
                            editScreenIntent.putExtra("mobile", mob);
                            startActivity(editScreenIntent);
                        }else {
                            Toast.makeText(ViewMembers.this, "No ID associated with that name !", Toast.LENGTH_LONG).show();
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
                ArrayList<String> memberslist = new ArrayList<>();

                for (String member : theList){
                    if (member.toLowerCase().contains(newText.toLowerCase())){
                        memberslist.add(member);
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewMembers.this, android.R.layout.simple_list_item_1, memberslist);
                listView.setAdapter(adapter);

                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
