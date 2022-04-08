package com.example.poosdaniel_restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class ListResultActivity extends AppCompatActivity {
    private ListView listView;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_result);
        listView = findViewById(R.id.lista);
        back = findViewById(R.id.vissza);

        back.setOnClickListener(v-> startActivity(new Intent(this, MainActivity.class)));
    }
}