package com.example.glm9637.myapplication.ui.activity;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.database.entry.StepEntry;
import com.example.glm9637.myapplication.ui.adapter.fragment.EditRecipeFragmentAdapter;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeFinishFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeIngredientFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeNameFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeStepsFragment;
import com.example.glm9637.myapplication.ui.view.EditRecipeViewPager;
import com.example.glm9637.myapplication.utils.AppExecutors;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.RecipeActivityViewModel;

import java.util.List;

public class EditRecipeActivity extends AppCompatActivity implements EditRecipeFinishFragment.RecipeFinishedListener, EditRecipeNameFragment.EnableSwipeListener {

	private long recipeId;
	private long categoryId;
	private long cutId;
	private boolean isRub;
	private EditRecipeFragmentAdapter adapter;
	private EditRecipeViewPager pager;
	private boolean swipeEnabled = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

		setContentView(R.layout.activity_edit_recipe);
		recipeId = getIntent().getLongExtra(Constants.Arguments.RECIPE_ID, 0);
		cutId = getIntent().getLongExtra(Constants.Arguments.CUT_ID, 0);
		categoryId = getIntent().getLongExtra(Constants.Arguments.CATEGORY_ID, 0);
		isRub = getIntent().getBooleanExtra(Constants.Arguments.IS_RUB, false);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		TabLayout tabLayout = findViewById(R.id.tab_layout);
		pager = findViewById(R.id.view_pager);
		if (recipeId != 0) {
			RecipeActivityViewModel viewModel = new RecipeActivityViewModel(this, recipeId);
			viewModel.getRecipe().observe(this, new Observer<RecipeEntry>() {
				@Override
				public void onChanged(@Nullable RecipeEntry recipeEntry) {
					adapter = new EditRecipeFragmentAdapter(getSupportFragmentManager(), recipeEntry);
					pager.setAdapter(adapter);
				}
			});
		} else {
			adapter = new EditRecipeFragmentAdapter(getSupportFragmentManager(), isRub);
			pager.setAdapter(adapter);
		}
		tabLayout.setupWithViewPager(pager);

		LinearLayout tabStrip = ((LinearLayout) tabLayout.getChildAt(0));
		for (int i = 0; i < tabStrip.getChildCount(); i++) {
			tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View view, MotionEvent motionEvent) {
					return !swipeEnabled;
				}
			});
		}

	}

	@Override
	public void onRecipeFinished() {
		if (recipeId == 0) {
			saveRecipe();
		} else {
			updateRecipe();
		}
		finish();
	}

	@Override
	public void onEnableSwipe() {
		pager.setPagingEnabled(true);
		swipeEnabled = true;
	}

	@Override
	public void onDisableSwipe() {
		pager.setPagingEnabled(false);
		swipeEnabled = false;
	}

	@Override
	public void onSwipeToNextPage() {
		pager.setCurrentItem(1, true);
	}

	private void saveRecipe() {
		AppExecutors.getInstance().diskIO().execute(new Runnable() {
			@Override
			public void run() {
				RecipeDatabase database = RecipeDatabase.getInstance(EditRecipeActivity.this);

				RecipeEntry recipeEntry;
				isRub = adapter.getIsRub();
				if (isRub) {
					categoryId = Constants.Ids.getRubCutId(categoryId);
				}
				recipeEntry = new RecipeEntry(categoryId, cutId, adapter.getName(), adapter.getShortDescription(), adapter.getDescription(), adapter.getCookingStyle(), adapter.getDuration(), isRub, false);

				long recipeId = database.getRecipeDao().insertRecipe(recipeEntry);

				List<IngredientEntry> ingredients = adapter.getIngredients();
				for (IngredientEntry ingredient : ingredients) {
					ingredient.setRecipeId(recipeId);
				}
				database.getIngredientDao().insertAll(ingredients.toArray(new IngredientEntry[ingredients.size()]));

				List<StepEntry> steps = adapter.getSteps();
				for (StepEntry step : steps) {
					step.setRecipeId(recipeId);
				}
				database.getStepDao().insertAll(steps.toArray(new StepEntry[steps.size()]));


			}

		});
	}

	private void updateRecipe() {
		AppExecutors.getInstance().diskIO().execute(new Runnable() {
			@Override
			public void run() {
				RecipeDatabase database = RecipeDatabase.getInstance(EditRecipeActivity.this);

				RecipeEntry recipeEntry;
				recipeEntry = database.getRecipeDao().loadRecipe_sync(recipeId);
				recipeEntry.setName(adapter.getName());
				recipeEntry.setShortDescription(adapter.getShortDescription());
				recipeEntry.setCookingStyle(adapter.getCookingStyle());
				recipeEntry.setDuration(adapter.getDuration());
				recipeEntry.setDescription(adapter.getDescription());
				database.getRecipeDao().updateRecipe(recipeEntry);

				List<IngredientEntry> ingredients = adapter.getIngredients();
				for (IngredientEntry ingredient : ingredients) {
					ingredient.setRecipeId(recipeId);
					if (ingredient.getId() == 0) {
						database.getIngredientDao().insertIngredient(ingredient);
					} else {
						if (ingredient.isDeleted()) {
							database.getIngredientDao().deleteIngredient(ingredient);
						} else {
							database.getIngredientDao().updateIngredient(ingredient);
						}
					}
				}
				List<StepEntry> steps = adapter.getSteps();
				for (StepEntry step : steps) {
					step.setRecipeId(recipeId);
					if (step.getId() == 0) {
						database.getStepDao().insertStep(step);
					} else {
						if (step.isDeleted()) {
							database.getStepDao().deleteStep(step);
						} else {
							database.getStepDao().updateStep(step);
						}
					}

				}

			}

		});
	}

	public static void reset(){
		EditRecipeIngredientFragment.resetData();
		EditRecipeStepsFragment.resetData();
	}

}
