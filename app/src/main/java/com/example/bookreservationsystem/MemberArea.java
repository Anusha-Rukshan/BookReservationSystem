package com.example.bookreservationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MemberArea extends AppCompatActivity {

    private Button regMem;
    private Button resBook;
    private Button memView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_area);

        regMem = (Button)findViewById(R.id.regMem);
        resBook = (Button)findViewById(R.id.resBook);
        memView = (Button)findViewById(R.id.memView);

        regMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNewMember();
            }
        });

        resBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewBooksForReserve();
            }
        });

        memView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewMembers();
            }


        });

    }

    private void openAddNewMember() {
        Intent intent = new Intent(this, AddNewMember.class);
        startActivity(intent);
    }

    private void openViewBooksForReserve() {
        Intent intent = new Intent(this, ViewBooksForReserve.class);
        startActivity(intent);
    }

    private void openViewMembers() {
        Intent intent = new Intent(this, ViewMembers.class);
        startActivity(intent);
    }

}
