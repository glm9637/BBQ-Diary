package com.example.glm9637.myapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.glm9637.myapplication.adapter.CategoryFragmentPagerAdapter;

public class CategoryActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		TabLayout tabLayout = findViewById(R.id.tab_layout);
		ViewPager pager = findViewById(R.id.view_pager);
		CategoryFragmentPagerAdapter adapter = new CategoryFragmentPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);
		tabLayout.setupWithViewPager(pager);
	}
}
