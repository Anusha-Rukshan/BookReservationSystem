package com.example.bookreservationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateBooks extends AppCompatActivity {

    private EditText bookName;
    private EditText author;
    private EditText year;
    private Button update;
    private Button del;

    DatabaseHelper myDb;

    private String selectedBookName;
    private int selectedID;
    private String selectedAuthor;
    private String selectedYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_books);
        myDb = new DatabaseHelper(this);

        bookName = (EditText)findViewById(R.id.editText5);
        author = (EditText)findViewById(R.id.editText6);
        year = (EditText)findViewById(R.id.editText7);
        update = (Button)findViewById(R.id.updateBook);
        del = (Button)findViewById(R.id.deleteBook);


        Intent recievedIntent = getIntent();

        selectedID = recievedIntent.getIntExtra("id", -1);
        selectedBookName = recievedIntent.getStringExtra("bookName");
        selectedAuthor = recievedIntent.getStringExtra("author");
        selectedYear = recievedIntent.getStringExtra("year");

        bookName.setText(selectedBookName);
        author.setText(selectedAuthor);
        year.setText(selectedYear);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String BookName = bookName.getText().toString();
                String Author = author.getText().toString();
                String Year = year.getText().toString();

                if(BookName.equals("")){
                    Toast.makeText(UpdateBooks.this, "You must enter a Name !", Toast.LENGTH_LONG).show();
                }else if (Author.equals("")){
                    Toast.makeText(UpdateBooks.this, "You must enter the Author !", Toast.LENGTH_LONG).show();
                }else if (Year.equals("")){
                    Toast.makeText(UpdateBooks.this, "You must enter a Year !", Toast.LENGTH_LONG).show();
                }else{
                    myDb.updateBookDetails(BookName, Author, Year, selectedID);
                    Toast.makeText(UpdateBooks.this, "Successfully Updated !", Toast.LENGTH_LONG).show();
                    openViewBooks();
                }

            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.deleteBook(selectedID);
                Toast.makeText(UpdateBooks.this, "Successfully deleted !", Toast.LENGTH_LONG).show();
                openViewBooks();
            }
        });



    }

    private void openViewBooks () {
        Intent intent = new Intent(this, ViewBooks.class);
        startActivity(intent);

    }

}
