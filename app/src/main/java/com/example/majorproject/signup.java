package com.example.majorproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {

    Button check;
    EditText ID,PW;
    private String pw;
    private String id;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("SIGN UP");
        actionBar.setDisplayHomeAsUpEnabled(true);

        check = findViewById(R.id.check);
        ID = findViewById(R.id.ID);
        PW = findViewById(R.id.PW);

        check.setOnClickListener(view -> {

            pw =PW.getText().toString();
            id = ID.getText().toString();

            mAuth.createUserWithEmailAndPassword(id,pw).addOnCompleteListener(this,task -> {
                if (task.isSuccessful()){
                    FirebaseUser user= mAuth.getCurrentUser();

                    finish();
                }
                else{
                    Toast.makeText(this, "RegisterFailed", Toast.LENGTH_SHORT).show();
                }
            });
        });




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
