package com.example.majorproject;

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

public class MainActivity extends AppCompatActivity {

    EditText ID,PW;
    Button Login,Join;
    String id,pw;
    String check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ID = findViewById(R.id.ID);
        PW = findViewById(R.id.PW);
        Login = findViewById(R.id.Login);
        Join = findViewById(R.id.Join);

        final SharedPreferences pref = getSharedPreferences("PREFERENCE",0);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = ID.getText().toString();
                pw = PW.getText().toString();


                check = pref.getString(id,"");
                if (check.equals(pw)){
                    Toast.makeText(MainActivity.this, "LoginSuccess", Toast.LENGTH_LONG).show();
                }

                if(PW.getText().toString().length()==0) {
                    Toast.makeText(MainActivity.this, "PW칸을 작성해주세요", Toast.LENGTH_SHORT).show();
                }
                if (ID.getText().toString().length()==0){
                    Toast.makeText(MainActivity.this, "ID칸을 작성해주세요", Toast.LENGTH_SHORT).show();
                }

                if (!check.equals(pw)){
                    Toast.makeText(MainActivity.this, "입력하신 정보가 맞지않습니다", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,signup.class);
                startActivity(intent);
            }
        });


    }

}
