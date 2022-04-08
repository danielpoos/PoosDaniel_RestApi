package com.example.poosdaniel_restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {
    private EditText nev, orszag, lakossag;
    private Button felvetel, vissza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        nev = findViewById(R.id.nevMezo);
        orszag = findViewById(R.id.orszagMezo);
        lakossag = findViewById(R.id.lakossagMezo);
        felvetel = findViewById(R.id.felvetelGomb);
        vissza = findViewById(R.id.visszaGomb);
        nev.setOnFocusChangeListener((nev, fordFocus)->{
            if (!fordFocus) focusFun();
            else focusBack();
        });
        vissza.setOnClickListener(v->startActivity(new Intent(this, MainActivity.class)));
    }
    private void focusFun(){
        
    }
    private void focusBack(){

    }
}