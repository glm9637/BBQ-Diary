package com.example.glm9637.myapplication.ui.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.ui.adapter.fragment.RecipeStepsFragmentAdapter;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.StepListAdapter;
import com.example.glm9637.myapplication.utils.Constants;

import java.util.List;

public class RecipeStepActivity extends AppCompatActivity {
	
	private long recipeId;
	private RecipeStepsFragmentAdapter adapter;
	private ViewPager pager;
	private TabLayout tabLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_step);
		
		recipeId = getIntent().getLongExtra(Constants.Arguments.RECIPE_ID, 0);
		
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		tabLayout = findViewById(R.id.tab_layout);
		pager = findViewById(R.id.view_pager);
		
		adapter = new RecipeStepsFragmentAdapter(getSupportFragmentManager(), recipeId, new StepListAdapter.ListEntryClickedListener() {
			@Override
			public void onListEntryClicked(int position) {
				pager.setCurrentItem(position, true);
			}
		});
		RecipeDatabase database = RecipeDatabase.getInstance(RecipeStepActivity.this);
		database.getStepDao().loadStepIdList(recipeId).observe(this, new Observer<List<Integer>>() {
			@Override
			public void onChanged(@Nullable List<Integer> idList) {
				adapter.setStepList(idList);
				pager.setAdapter(adapter);
				tabLayout.setupWithViewPager(pager);
			}
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_activity_recipe, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.mnu_step:
				pager.setCurrentItem(0,true);
			default:
				return super.onOptionsItemSelected(item);
		}
		
	}
}
