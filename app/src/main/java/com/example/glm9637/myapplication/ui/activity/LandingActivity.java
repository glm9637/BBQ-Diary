package com.example.glm9637.myapplication.ui.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.CategoryEntry;
import com.example.glm9637.myapplication.utils.Constants;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        TextView txt_beef = findViewById(R.id.txt_beef);
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/copaseti.ttf");
        txt_beef.setTypeface(tf);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.cv_beef).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategoryActivity(Constants.Ids.CATEGORY_BEEF);
            }
        });
        findViewById(R.id.cv_pork).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategoryActivity(Constants.Ids.CATEGORY_PORK);
            }
        });
        findViewById(R.id.cv_poultry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategoryActivity(Constants.Ids.CATEGORY_POULTY);
            }
        });
        findViewById(R.id.cv_fish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategoryActivity(Constants.Ids.CATEGORY_FISH);
            }
        });
        findViewById(R.id.cv_vegetable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategoryActivity(Constants.Ids.CATEGORY_VEGETABLE);
            }
        });
        findViewById(R.id.cv_other).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategoryActivity(Constants.Ids.CATEGORY_OTHER);
            }
        });
	
        //Prefill the Database ToDo: seperate this into a first time Onscreener?
	    final RecipeDatabase database = RecipeDatabase.getInstance(this);
	    final LiveData<CategoryEntry> data= database.getCategoryDao().loadCategory(Constants.Ids.CATEGORY_BEEF);
	    data.observe(this, new Observer<CategoryEntry>() {
		    @Override
		    public void onChanged(@Nullable CategoryEntry categoryEntry) {
				data.removeObserver(this);
				Log.d("Test","test");
		    }
	    });
	    
    }

    private void startCategoryActivity(long id){
        Intent intent = new Intent(LandingActivity.this, CategoryActivity.class);
        intent.putExtra(Constants.Arguments.CATEGORY_ID, id);
        startActivity(intent);
    }
}
