package com.example.bookreservationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateMembers extends AppCompatActivity {

    private EditText name;
    private EditText nic;
    private EditText mobile;
    private Button update;
    private Button del;

    DatabaseHelper myDb;

    private String selectedName;
    private int selectedID;
    private String selectedNic;
    private String selectedMobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_members);
        myDb = new DatabaseHelper(this);

        name = (EditText)findViewById(R.id.editText3);
        nic = (EditText)findViewById(R.id.editText7);
        mobile = (EditText)findViewById(R.id.editText8);
        update = (Button)findViewById(R.id.btn0);
        del = (Button)findViewById(R.id.btn1);

        Intent recievedIntent = getIntent();

        selectedID = recievedIntent.getIntExtra("id", -1);
        selectedName = recievedIntent.getStringExtra("name");
        selectedNic = recievedIntent.getStringExtra("nic");
        selectedMobile = recievedIntent.getStringExtra("mobile");

        name.setText(selectedName);
        nic.setText(selectedNic);
        mobile.setText(selectedMobile);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Nic = nic.getText().toString();
                String Mobile = mobile.getText().toString();

                if(Name.equals("")){
                    Toast.makeText(UpdateMembers.this, "You must enter a Name !", Toast.LENGTH_LONG).show();
                }else if (Nic.equals("")){
                    Toast.makeText(UpdateMembers.this, "You must enter the NIC !", Toast.LENGTH_LONG).show();
                }else if (Mobile.equals("")){
                    Toast.makeText(UpdateMembers.this, "You must enter a Mobile !", Toast.LENGTH_LONG).show();
                }else{
                    myDb.updateDetails(Name, Nic, Mobile, selectedID);
                    Toast.makeText(UpdateMembers.this, "Successfully Updated !", Toast.LENGTH_LONG).show();
                    openViewMembers();
                }
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.deleteMember(selectedID);
                Toast.makeText(UpdateMembers.this, "Successfully deleted !", Toast.LENGTH_LONG).show();
                openViewMembers();
            }
        });

    }

    private void openViewMembers() {
        Intent intent = new Intent(this, ViewMembers.class);
        startActivity(intent);
    }
}
