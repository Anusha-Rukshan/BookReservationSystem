package com.example.bookreservationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class AddNewMember extends AppCompatActivity {

    DatabaseHelper myDb;
    private Button reg;
    private Button view;
    private EditText name;
    private EditText nic;
    private EditText mobile;

//    DatabaseReference databaseMembers;
//    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_member);
        myDb = new DatabaseHelper(this);
//        member = new Member();
//        databaseMembers = FirebaseDatabase.getInstance().getReference();

        reg = (Button) findViewById(R.id.reg);
        view = (Button) findViewById(R.id.button1);
        name = (EditText) findViewById(R.id.name);
        nic = (EditText) findViewById(R.id.nic);
        mobile = (EditText) findViewById(R.id.mobile);
//        if (name != null && nic != null && mobile != null) {
//        reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                writeNewMember("001","Thamal", "971152265V", "0712963564");
//                member.setMemberName(name.getText().toString().trim());
//                member.setMemberNic(nic.getText().toString().trim());
//                member.setMemberMob(mobile.getText().toString().trim());
//                databaseMembers.push().setValue(member);
//                Toast.makeText(AddNewMember.this,"Data inserted successfully !", Toast.LENGTH_LONG).show();

//                addMember();
//
//            }
//        });
//        }
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(name.length() == 0){
                        Toast.makeText(AddNewMember.this, "Enter a Name !", Toast.LENGTH_LONG).show();
                    }else if (nic.length() == 0){
                        Toast.makeText(AddNewMember.this, "Enter the NIC !", Toast.LENGTH_LONG).show();
                    }else if (mobile.length() == 0){
                        Toast.makeText(AddNewMember.this, "Enter the Mobile !", Toast.LENGTH_LONG).show();
                    }else {
                        boolean isInserted = myDb.insertMember(name.getText().toString(), nic.getText().toString(), mobile.getText().toString());
                        name.setText("");
                        nic.setText("");
                        mobile.setText("");
                        if (isInserted == true) {
                            Toast.makeText(AddNewMember.this, "Data inserted successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AddNewMember.this, "Data insertion failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                }
            );

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                openViewMembers();
                }
            });

    }

//        public void addData(){
//        }


//    private void writeNewMember(String memberId, String name, String nic, String mobile){
//        Member member = new Member(name, nic, mobile);
//
//        databaseMembers.child("Members").child(memberId).setValue(member);
//    }

//    private void addMember(){
//        String Name = name.getText().toString().trim();
//        String Nic = nic.getText().toString().trim();
//        String Mob = mobile.getText().toString().trim();
//
//        if(TextUtils.isEmpty(Name)){
//            Toast.makeText(this, "You should enter a Name !", Toast.LENGTH_LONG).show();
//        } else if(TextUtils.isEmpty(Nic)){
//            Toast.makeText(this, "You should enter the NIC !", Toast.LENGTH_LONG).show();
//        } else if(TextUtils.isEmpty(Mob)){
//            Toast.makeText(this, "You should enter a Mobile number !", Toast.LENGTH_LONG).show();
//        } else{
//
//            String id = databaseMembers.push().getKey();
//        Member member = new Member(id, Name, Nic, Mob);
//            databaseMembers.child(id).setValue(member);
//            Toast.makeText(this,"Member added successfully !", Toast.LENGTH_LONG).show();
//
//        }
//
//    }

    private void openViewMembers() {
            Intent intent = new Intent(this, ViewMembers.class);
            startActivity(intent);
    }


}
