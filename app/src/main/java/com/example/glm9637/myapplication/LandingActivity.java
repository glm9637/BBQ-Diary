package com.example.glm9637.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        TextView txt_beef = findViewById(R.id.txt_beef);
        Typeface tf = Typeface.createFromAsset(getAssets(),"font/copaseti.ttf");
        txt_beef.setTypeface(tf);
	
	    Toolbar toolbar = findViewById(R.id.toolbar);
	    setSupportActionBar(toolbar);
	    
	    findViewById(R.id.cv_beef).setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    Intent intent = new Intent(LandingActivity.this, CategoryActivity.class);
			    startActivity(intent);
		    }
	    });
    }
}
