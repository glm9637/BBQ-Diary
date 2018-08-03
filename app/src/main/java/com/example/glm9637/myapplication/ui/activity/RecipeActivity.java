package com.example.glm9637.myapplication.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.IngredientAdapter;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.RecipeActivityViewModel;

import java.util.List;

public class RecipeActivity extends AppCompatActivity {

	private TextView descriptionText;
	private TextView durationText;
	private TextView cookingStyleText;
	private IngredientAdapter adapter;
	private RecipeEntry recipeEntry;
	
	private static Bundle mState;
	
	private long recipeId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe);
		Toolbar toolbar = findViewById(R.id.toolbar);
		descriptionText = findViewById(R.id.txt_description);
		durationText = findViewById(R.id.txt_duration);
		cookingStyleText = findViewById(R.id.txt_cooking_style);
		RecyclerView recyclerView = findViewById(R.id.recyclerview);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		recipeId = getIntent().getLongExtra(Constants.Arguments.RECIPE_ID, 0);
		if(savedInstanceState!=null && recipeId == 0) {
			recipeId=savedInstanceState.getLong(Constants.Arguments.CATEGORY_ID);
		}
		
		adapter = new IngredientAdapter(this);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
		
		
		FloatingActionButton btn = findViewById(R.id.btn_cook);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RecipeActivity.this, RecipeStepActivity.class);
				intent.putExtra(Constants.Arguments.RECIPE_ID, recipeId);
				startActivity(intent);
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
			case android.R.id.home:
				finishAfterTransition();
				return true;
			case R.id.mnu_notes:
				Intent intent = new Intent(this, NoteListActivity.class);
				intent.putExtra(Constants.Arguments.RECIPE_ID, recipeId);
				startActivity(intent);
				return true;
			case R.id.mnu_edit:
				intent = new Intent(this, EditRecipeActivity.class);
				intent.putExtra(Constants.Arguments.RECIPE_ID, recipeId);
				intent.putExtra(Constants.Arguments.CUT_ID,recipeEntry.getCutId());
				intent.putExtra(Constants.Arguments.CATEGORY_ID,recipeEntry.getCategoryId());
				startActivity(intent);
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	
	@Override
	protected void onPause() {
		super.onPause();
		mState = new Bundle();
		mState.putLong(Constants.Arguments.RECIPE_ID, recipeId);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(mState!=null && recipeId == 0){
			recipeId = mState.getLong(Constants.Arguments.RECIPE_ID);
		}else {
			mState = null;
		}

		RecipeActivityViewModel viewModel = new RecipeActivityViewModel(this, recipeId);
		
		viewModel.getRecipe().observe(this, new Observer<RecipeEntry>() {
			@Override
			public void onChanged(@Nullable RecipeEntry recipeEntry) {
				if (recipeEntry == null) {
					return;
				}
				RecipeActivity.this.recipeEntry = recipeEntry;
				getSupportActionBar().setTitle(recipeEntry.getName());
				getSupportActionBar().setSubtitle(recipeEntry.getShortDescription());
				descriptionText.setText(recipeEntry.getDescription());
				durationText.setText(String.valueOf(recipeEntry.getDuration()));
				cookingStyleText.setText(recipeEntry.getCookingStyle());
			}
		});
		viewModel.getIngredients().observe(this, new Observer<List<IngredientEntry>>() {
			@Override
			public void onChanged(@Nullable List<IngredientEntry> ingredientEntries) {
				adapter.setData(ingredientEntries);
			}
		});
		
	}
}
