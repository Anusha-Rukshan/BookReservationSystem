package com.example.bookreservationsystem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class AddNewReservation extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener {

    DatabaseHelper db;
    private String selectedBookName;
    private String selectedBID;
    private int selectedID;

    private ImageButton cal_imageButton;
    private Button saveRes;
    private Button viewReserved;
    private EditText memberId;
    private EditText bookName;
    private EditText bookId;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_reservation);
        db = new DatabaseHelper(this);


        memberId =(EditText)findViewById(R.id.editText2);
        bookName=(EditText)findViewById(R.id.editText);
        bookId=(EditText)findViewById(R.id.editText3);
        date=(TextView)findViewById(R.id.textView15);

        saveRes = (Button)findViewById(R.id.saveRes);
        viewReserved = (Button)findViewById(R.id.btn0);

        Intent recievedIntent = getIntent();

        selectedID = recievedIntent.getIntExtra("id", -1);
        selectedBID = recievedIntent.getStringExtra("bId");
        selectedBookName = recievedIntent.getStringExtra("bookName");

        bookName.setText(selectedBookName);

        bookId.setText(selectedBID);

//        saveRes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openViewReserves();
//            }
//
//
//        });


        saveRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = db.insertReservation(memberId.getText().toString(), bookName.getText().toString(), bookId.getText().toString(),date.getText().toString());
                memberId.setText("");
                bookName.setText("");
                bookId.setText("");
                date.setText("");
                if (isInserted == true) {
                    Toast.makeText(AddNewReservation.this, "Data inserted successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AddNewReservation.this, "Data insertion failed", Toast.LENGTH_LONG).show();
                }
            }
            }
        );

        viewReserved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewReserves();
            }
        });


        cal_imageButton=(ImageButton)findViewById(R.id.imageButton);
        cal_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker=new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c =Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        TextView textView = (TextView) findViewById(R.id.textView15);
        textView.setText(currentDateString);

    }

    private void openViewReserves() {
        Intent intent = new Intent(this, ViewReserves.class);
        startActivity(intent);
    }

}
