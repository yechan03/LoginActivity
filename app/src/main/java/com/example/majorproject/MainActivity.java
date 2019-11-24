package com.example.majorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText ID,PW;
    Button Login,Join;
    String id,pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();

        ID = findViewById(R.id.ID);
        PW = findViewById(R.id.PW);
        Login = findViewById(R.id.Login);
        Join = findViewById(R.id.Join);


        Login.setOnClickListener(v -> {

                id = ID.getText().toString();
                pw = PW.getText().toString();

                mAuth.signInWithEmailAndPassword(id,pw).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful())
                    {
                        startActivity(new Intent(this,ResultActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "LoginFailed", Toast.LENGTH_SHORT).show();
                    }
                });

            });



        Join.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,signup.class);
            startActivity(intent);

        });


    }



}
