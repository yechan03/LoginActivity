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

public class signup extends AppCompatActivity {

    Button check;
    EditText ID,PW;
    private String pw;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("SIGN UP");
        actionBar.setDisplayHomeAsUpEnabled(true);

        check = findViewById(R.id.check);
        ID = findViewById(R.id.ID);
        PW = findViewById(R.id.PW);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("PREFERENCE",0);
                SharedPreferences.Editor editor = pref.edit();

                pw =PW.getText().toString();
                id = ID.getText().toString();


                if (id.length()==0){
                    Toast.makeText(signup.this, "ID칸을 작성해주세요", Toast.LENGTH_SHORT).show();
                }
                if (pw.length()==0){
                    Toast.makeText(signup.this, "PW칸을 작성해주세요", Toast.LENGTH_SHORT).show();
                }
                if (pw.equals(pref.getString(id,pw))&&id.length()!=0&&pw.length()!=0) {
                    editor.putString(ID.getText().toString(), PW.getText().toString());
                    editor.commit();
                    Intent intent = new Intent(signup.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(signup.this, "이미 있는 아이디 비밀번호 입니", Toast.LENGTH_SHORT).show();
                }
            }
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
