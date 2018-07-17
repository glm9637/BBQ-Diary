package com.example.glm9637.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        TextView txt_beef = findViewById(R.id.txt_beef);
        Typeface tf = Typeface.createFromAsset(getAssets(),"font/copaseti.ttf");
        txt_beef.setTypeface(tf);
    }
}
