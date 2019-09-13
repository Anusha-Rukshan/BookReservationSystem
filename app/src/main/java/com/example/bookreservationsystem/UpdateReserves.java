package com.example.bookreservationsystem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class UpdateReserves extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener {

    DatabaseHelper db;
    private ImageButton cal_imageButton;
    private TextView date;
    private Button up;
    private Button del;
    private Button cancel;

    private int selectedID;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_reserves);
        db = new DatabaseHelper(this);

        date = (TextView)findViewById(R.id.textView10);
        up = (Button)findViewById(R.id.button5);
        del = (Button)findViewById(R.id.button6);
        cancel = (Button)findViewById(R.id.button4);

        Intent recievedIntent = getIntent();

        selectedID = recievedIntent.getIntExtra("id", -1);
        selectedDate = recievedIntent.getStringExtra("date");

        date.setText(selectedDate);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newDate = date.getText().toString();

                if (date.equals("")){
                    Toast.makeText(UpdateReserves.this, "You must enter a Date !", Toast.LENGTH_LONG).show();
                }else{
                    db.updateReserveDetails(newDate, selectedID);
                    Toast.makeText(UpdateReserves.this, "Successfully Updated !", Toast.LENGTH_LONG).show();
                    openViewReserves();
                }

            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteReservation(selectedID);
                Toast.makeText(UpdateReserves.this, "Successfully deleted !", Toast.LENGTH_LONG).show();
                openViewReserves();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewReserves();
            }
        });

        cal_imageButton=(ImageButton)findViewById(R.id.imageButton2);
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

        TextView textView = (TextView) findViewById(R.id.textView10);
        textView.setText(currentDateString);

    }

    private void openViewReserves() {
        Intent intent = new Intent(this, ViewReserves.class);
        startActivity(intent);
    }

}
