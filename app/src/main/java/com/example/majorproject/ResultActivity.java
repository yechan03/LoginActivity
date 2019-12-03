package com.example.majorproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ResultActivity extends AppCompatActivity {


    private FirebaseFirestore db;
    private ArrayList<Item> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private SimpleAdapter simpleAdapter;
    private Button button;
    private EditText editText;
    Toolbar toolbar;


    private void getData(){
        items.clear();
        db.collection("chats").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                    Item item = documentSnapshot.toObject(Item.class);
                    items.add(item);
                }
                if (simpleAdapter != null){
                    simpleAdapter.notifyDataSetChanged();
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        db = FirebaseFirestore.getInstance();
        recyclerView =findViewById(R.id.recycler);
        toolbar = findViewById(R.id.Toolbar);


        getSupportActionBar().setTitle("Your Activity Title");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, new LinearLayoutManager(this).getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        simpleAdapter = new SimpleAdapter(items);
        recyclerView.setAdapter(simpleAdapter);

        simpleAdapter.setOnItemClickListener((v, pos) -> {
            db.collection("chats").document(items.get(pos).getId()).delete().addOnCompleteListener(task -> {

                if (task.isSuccessful()) {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

                }
            });
            getData();
        });

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        String pw = intent.getStringExtra("pw");

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

        button.setOnClickListener(v -> {

            Map<String, Object> item = new HashMap<>();
            item.put("id", Objects.requireNonNull(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail()));
            item.put("content", editText.getText().toString());
            item.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
            editText.setText("");

            db.collection("chats").add(item).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                    getData();
                    simpleAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            });
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }





}
