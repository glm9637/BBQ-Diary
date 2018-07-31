package com.example.glm9637.myapplication;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.glm9637.myapplication.adapter.CategoryFragmentPagerAdapter;
import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.CategoryEntry;
import com.example.glm9637.myapplication.database.entry.CutEntry;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.CategoryViewModel;

public class CategoryActivity extends AppCompatActivity {

	private TextView toolbarText;
	private ImageView toolbarIcon;

	private CategoryViewModel viewModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		
		final Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		toolbarText = findViewById(R.id.category_title);
		toolbarIcon = findViewById(R.id.toolbar_icon);
		long cutId = getIntent().getLongExtra(Constants.Arguments.CATEGORY_ID,0);
		viewModel = new CategoryViewModel(RecipeDatabase.getInstance(this),cutId);
		viewModel.getCategory().observe(this, new Observer<CategoryEntry>() {
			@Override
			public void onChanged(@Nullable CategoryEntry categoryEntry) {
				viewModel.getCategory().removeObserver(this);
				toolbarText.setText(categoryEntry.getName());
				toolbarIcon.setImageResource(categoryEntry.getIconId());
			}
		});

		TabLayout tabLayout = findViewById(R.id.tab_layout);
		ViewPager pager = findViewById(R.id.view_pager);
		CategoryFragmentPagerAdapter adapter = new CategoryFragmentPagerAdapter(getSupportFragmentManager(),cutId);
		pager.setAdapter(adapter);
		tabLayout.setupWithViewPager(pager);



	}
}
