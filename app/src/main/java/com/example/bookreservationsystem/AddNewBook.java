package com.example.bookreservationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewBook extends AppCompatActivity {

    DatabaseHelper myDb;
    private Button add;
    private EditText name;
    private EditText author;
    private EditText year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);
        myDb = new DatabaseHelper(this);

        name = (EditText) findViewById(R.id.name);
        author = (EditText) findViewById(R.id.author);
        year = (EditText) findViewById(R.id.editText3);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       if (name.length() == 0) {
                                           Toast.makeText(AddNewBook.this, "Enter a Book Name !", Toast.LENGTH_LONG).show();
                                       } else if (author.length() == 0) {
                                           Toast.makeText(AddNewBook.this, "Enter a Author Name !", Toast.LENGTH_LONG).show();

                                       } else if (year.length() == 0) {
                                           Toast.makeText(AddNewBook.this, "Enter a Published Year !", Toast.LENGTH_LONG).show();
                                       } else {
                                           boolean isInserted = myDb.insertBook(name.getText().toString(), author.getText().toString(), year.getText().toString());
//                                           openViewBooks();
                                           //name.setText("");
                                           //author.setText("");
                                           // year.setText("");
                                           if (isInserted == true) {
                                               Toast.makeText(AddNewBook.this, "Data inserted successfully", Toast.LENGTH_LONG).show();
                                           } else {
                                               Toast.makeText(AddNewBook.this, "Data inserted failed", Toast.LENGTH_LONG).show();
                                           }
                                       }
                                   }
                               }
        );
    }

    private void openViewBooks () {
        Intent intent = new Intent(this, ViewBooks.class);
        startActivity(intent);

    }
}

