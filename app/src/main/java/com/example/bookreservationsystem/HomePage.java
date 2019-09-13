package com.example.bookreservationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {


    private Button addBook;
    private Button viewBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        addBook = (Button)findViewById(R.id.addBook);
        viewBook = (Button) findViewById(R.id.viewBook);







        viewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewBooks();
            }


        });

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNewBook();
            }


        });

    }



    private void openAddNewBook() {
        Intent intent = new Intent(this, AddNewBook.class);
        startActivity(intent);
    }

    private void openViewBooks() {
        Intent intent = new Intent(this, ViewBooks.class);
        startActivity(intent);
    }



}
