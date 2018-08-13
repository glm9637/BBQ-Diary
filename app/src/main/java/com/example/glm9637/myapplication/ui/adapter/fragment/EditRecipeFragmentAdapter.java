package com.example.glm9637.myapplication.ui.adapter.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.database.entry.StepEntry;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeDescriptionFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeFinishFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeIngredientFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeNameFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeStepsFragment;
import com.example.glm9637.myapplication.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class EditRecipeFragmentAdapter extends FragmentPagerAdapter {

	private final FragmentManager fragmentManager;
	private RecipeEntry recipe;
	private boolean isRub;
	private EditRecipeNameFragment nameFragment;
	private EditRecipeDescriptionFragment descriptionFragment;
	private EditRecipeIngredientFragment ingredientFragment;
	private EditRecipeStepsFragment stepsFragment;
	private EditRecipeFinishFragment finishFragment;

	public EditRecipeFragmentAdapter(FragmentManager fm, RecipeEntry recipe) {
		super(fm);
		this.recipe = recipe;
		fragmentManager = fm;
	}

	public EditRecipeFragmentAdapter(FragmentManager fm, boolean isRub) {
		super(fm);
		this.isRub = isRub;
		this.fragmentManager = fm;
	}

	private static String makeFragmentName(long id) {
		return "android:switcher:" + R.id.view_pager + ":" + id;
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
				}
				return finishFragment;
		}

	}

	@Override
	public int getCount() {
		return 5;
	}

	public String getName() {
		return ((EditRecipeNameFragment) getRegisteredFragment(0)).getName();
	}

	public String getShortDescription() {
		return ((EditRecipeNameFragment) getRegisteredFragment(0)).getShortDescription();
	}

	public String getCookingStyle() {
		return ((EditRecipeNameFragment) getRegisteredFragment(0)).getCookingStyle();
	}

	public long getDuration() {
		return ((EditRecipeNameFragment) getRegisteredFragment(0)).getDuration();
	}

	public String getDescription() {
		return ((EditRecipeDescriptionFragment) getRegisteredFragment(1)).getDescription();
	}

	public List<IngredientEntry> getIngredients() {
		return ((EditRecipeIngredientFragment) getRegisteredFragment(2)).getIngredients();
	}

	public List<StepEntry> getSteps() {
		return ((EditRecipeStepsFragment) getRegisteredFragment(3)).getSteps();
	}

	public boolean getIsRub() {

		return ((EditRecipeNameFragment) getRegisteredFragment(0)).isRub();
	}

	private Fragment getRegisteredFragment(int position) {
		return fragmentManager.findFragmentByTag(makeFragmentName(position));
	}

}
