package com.example.glm9637.myapplication.ui.adapter.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.database.entry.StepEntry;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeDescriptionFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeFinishFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeIngredientFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeNameFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeStepsFragment;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class EditRecipeFragmentAdapter extends FragmentPagerAdapter {

	private RecipeEntry recipe;
	private boolean isRub;

	private EditRecipeNameFragment nameFragment;
	private EditRecipeDescriptionFragment descriptionFragment;
	private EditRecipeIngredientFragment ingredientFragment;
	private EditRecipeStepsFragment stepsFragment;
	private EditRecipeFinishFragment finishFragment;

	private final EditRecipeNameFragment.EnableSwipeListener swipeListener;
	private final EditRecipeFinishFragment.RecipeFinishedListener finishedListener;

	public EditRecipeFragmentAdapter(FragmentManager fm, RecipeEntry recipe, EditRecipeNameFragment.EnableSwipeListener swipeListener, EditRecipeFinishFragment.RecipeFinishedListener finishedListener) {
		super(fm);
		this.swipeListener = swipeListener;
		this.recipe = recipe;
		this.finishedListener = finishedListener;
	}

	public EditRecipeFragmentAdapter(FragmentManager fm, boolean isRub, EditRecipeNameFragment.EnableSwipeListener swipeListener, EditRecipeFinishFragment.RecipeFinishedListener finishedListener) {
		super(fm);
		this.swipeListener = swipeListener;
		this.isRub = isRub;
		this.finishedListener = finishedListener;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				if (nameFragment == null) {
					if (recipe == null) {
						nameFragment = EditRecipeNameFragment.createFragment(isRub);
					} else {
						nameFragment = EditRecipeNameFragment.createFragment(recipe.getName(), recipe.getShortDescription(), recipe.isSeasoning(), recipe.getCookingStyle(), recipe.getDuration());
					}
					nameFragment.setEnableSwipeListener(swipeListener);
				}
				return nameFragment;
			case 1:
				if (descriptionFragment == null) {
					if (recipe == null) {
						descriptionFragment = EditRecipeDescriptionFragment.createFragment();
					} else {
						descriptionFragment = EditRecipeDescriptionFragment.createFragment(recipe.getDescription());
					}
				}
				return descriptionFragment;
			case 2:
				if (ingredientFragment == null) {
					if (recipe == null) {
						ingredientFragment = EditRecipeIngredientFragment.createFragment();
					} else {
						ingredientFragment = EditRecipeIngredientFragment.createFragment(recipe.getId());
					}
				}
				return ingredientFragment;
			case 3:
				if (stepsFragment == null) {
					if (recipe == null) {
						stepsFragment = EditRecipeStepsFragment.createFragment();
					} else {
						stepsFragment = EditRecipeStepsFragment.createFragment(recipe.getId());
					}
				}
				return stepsFragment;
			default:
				if (finishFragment == null) {
					finishFragment = EditRecipeFinishFragment.createFragment();
					finishFragment.setRecipeFinishedListener(finishedListener);
				}

				return finishFragment;
		}

	}

	@Override
	public int getCount() {
		return 5;
	}

	public String getName() {
		return nameFragment.getName();
	}

	public String getShortDescription() {
		return nameFragment.getShortDescription();
	}

	public String getCookingStyle() {
		return nameFragment.getCookingStyle();
	}

	public long getDuration() {
		return nameFragment.getDuration();
	}

	public String getDescription() {
		return descriptionFragment.getDescription();
	}

	public List<IngredientEntry> getIngredients() {
		return ingredientFragment.getIngredients();
	}

	public List<StepEntry> getSteps() {
		return stepsFragment.getSteps();
	}

	public boolean getIsRub() {
		return nameFragment.isRub();
	}
}
