package com.example.glm9637.myapplication;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.glm9637.myapplication.adapter.Fragments.CategoryFragmentPagerAdapter;
import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.CategoryEntry;
import com.example.glm9637.myapplication.fragments.category.CutsFragment;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.CategoryViewModel;

public class CategoryActivity extends AppCompatActivity {

	private TextView toolbarText;
	private ImageView toolbarIcon;

	private CategoryViewModel viewModel;
	private static Bundle mState;
	private long categoryId;
	private CategoryFragmentPagerAdapter adapter;
	private ViewPager pager;
	private TabLayout tabLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		toolbarText = findViewById(R.id.category_title);
		toolbarIcon = findViewById(R.id.toolbar_icon);
		categoryId = getIntent().getLongExtra(Constants.Arguments.CATEGORY_ID,0);
		if(savedInstanceState!=null && categoryId == 0) {
			categoryId=savedInstanceState.getLong(Constants.Arguments.CATEGORY_ID);
		}

		tabLayout = findViewById(R.id.tab_layout);
		pager = findViewById(R.id.view_pager);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mState = new Bundle();
		mState.putLong(Constants.Arguments.CATEGORY_ID, categoryId);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(mState!=null && categoryId == 0){
			categoryId = mState.getLong(Constants.Arguments.CATEGORY_ID);
		}else {
			CutsFragment.reset();
		}
		
		viewModel = new CategoryViewModel(RecipeDatabase.getInstance(this),categoryId);
		viewModel.getCategory().observe(this, new Observer<CategoryEntry>() {
			@Override
			public void onChanged(@Nullable CategoryEntry categoryEntry) {
				viewModel.getCategory().removeObserver(this);
				Log.w("Tag",categoryId+"");
				if(categoryEntry==null){
					return;
				}
				toolbarText.setText(categoryEntry.getName());
				toolbarIcon.setImageResource(categoryEntry.getIconId());
			}
		});
		
		adapter = new CategoryFragmentPagerAdapter(getSupportFragmentManager(),categoryId);
		pager.setAdapter(adapter);
		tabLayout.setupWithViewPager(pager);
		
	}
	
	
}
