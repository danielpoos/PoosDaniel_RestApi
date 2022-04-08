package com.example.poosdaniel_restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button listaz, uj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaz = findViewById(R.id.listazas);
        uj = findViewById(R.id.uj_felvetele);
        listaz.setOnClickListener(v-> startActivity(new Intent(this, ListResultActivity.class)));
        uj.setOnClickListener(v-> startActivity(new Intent(this, InsertActivity.class)));
    }
}