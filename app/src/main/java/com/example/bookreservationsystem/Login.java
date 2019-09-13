package com.example.bookreservationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.PasswordAuthentication;

public class Login extends AppCompatActivity {

    private Button btnLogin;
    private EditText userName;
    private EditText password;
    private int counter = 5;
    private Button memArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        memArea = (Button)findViewById(R.id.memArea);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        memArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMemArea();
            }
        });
    }

    private void login() {
        String user = userName.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (user.equals("Admin") && pass.equals("password")){
            Toast.makeText(this,"Login successful",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }
        else{
            counter--;
            Toast.makeText(this,"Login Fail. "+counter+" tries left",Toast.LENGTH_LONG).show();


            if(counter == 0){
                btnLogin.setEnabled(false);
            }
        }
    }

    private void openMemArea() {
        Intent intent = new Intent(this, MemberArea.class);
        startActivity(intent);
    }

}
