package com.example.glm9637.myapplication.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.ui.adapter.fragment.EditRecipeFragmentAdapter;
import com.example.glm9637.myapplication.ui.adapter.fragment.RecipeStepsFragmentAdapter;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.StepListAdapter;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeFinishFragment;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.RecipeActivityViewModel;

public class EditRecipeActivity extends AppCompatActivity implements EditRecipeFinishFragment.RecipeFinishedListener {
	
	private long recipeId;
	private long categoryId;
	private boolean isRub;
	private EditRecipeFragmentAdapter adapter;
	private ViewPager pager;
	private TabLayout tabLayout;
	private RecipeActivityViewModel viewModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_recipe);
		recipeId = getIntent().getLongExtra(Constants.Arguments.RECIPE_ID, 0);
		categoryId = getIntent().getLongExtra(Constants.Arguments.CATEGORY_ID,0);
		isRub = getIntent().getBooleanExtra(Constants.Arguments.IS_RUB,false);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		tabLayout = findViewById(R.id.tab_layout);
		pager = findViewById(R.id.view_pager);
		if(recipeId!=0) {
			viewModel = new RecipeActivityViewModel(this, recipeId);
			viewModel.getRecipe().observe(this, new Observer<RecipeEntry>() {
				@Override
				public void onChanged(@Nullable RecipeEntry recipeEntry) {
					adapter = new EditRecipeFragmentAdapter(getSupportFragmentManager(), recipeEntry);
					pager.setAdapter(adapter);
				}
			});
		}
		else {
			adapter= new EditRecipeFragmentAdapter(getSupportFragmentManager(),isRub);
			pager.setAdapter(adapter);
		}
		tabLayout.setupWithViewPager(pager);
	}
	
	@Override
	public void onRecipeFinished() {
	
	}
}
