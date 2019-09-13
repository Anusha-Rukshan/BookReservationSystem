package com.example.bookreservationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {



    DatabaseHelper myDb;
    private Button start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        start = (Button)findViewById(R.id.start);

//
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("message");
//
//                myRef.setValue("Hello, World!");
//                Toast.makeText(MainActivity.this, "Successfully inserted !", Toast.LENGTH_LONG).show();
                openHomePage();
            }


        });
//

//
    }
        private void openHomePage() {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }



}
