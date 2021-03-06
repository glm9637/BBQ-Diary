package com.example.glm9637.myapplication.ui.adapter.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.glm9637.myapplication.ui.adapter.recyclerView.StepListAdapter;
import com.example.glm9637.myapplication.ui.fragment.step.StepDetailFragment;
import com.example.glm9637.myapplication.ui.fragment.step.StepIngredientFragment;
import com.example.glm9637.myapplication.ui.fragment.step.StepListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class RecipeStepsFragmentAdapter extends FragmentPagerAdapter {

	private long recipeId;
	private long categoryId;
	private long cutId;
	private String recipeRef;
	private List<Integer> stepList;
	private ArrayList<String> firebaseStepList;
	private StepListFragment stepListFragment;

	public RecipeStepsFragmentAdapter(FragmentManager fm, long recipeId, long categoryId, long cutId) {
		super(fm);
		this.recipeId = recipeId;
		this.categoryId = categoryId;
		this.cutId = cutId;
	}

	public RecipeStepsFragmentAdapter(FragmentManager fm, String recipeRef, long categoryId, long cutId) {
		super(fm);
		this.recipeRef = recipeRef;
		this.categoryId = categoryId;
		this.cutId = cutId;
	}

	@Override
	public Fragment getItem(int position) {
		Log.w("Step", "getItem: " + position);
		switch (position) {
			case 0:

				if (stepListFragment == null) {
					if (recipeRef == null) {
						stepListFragment = StepListFragment.createFragment(recipeId);
					} else {
						stepListFragment = StepListFragment.createFragment(recipeRef);
					}
				}
				Log.w("Step", "Recipe fragment returned");
				return stepListFragment;
			case 1:
				StepIngredientFragment stepIngredientFragment;
				if (recipeRef == null) {
					stepIngredientFragment = StepIngredientFragment.createFragment(recipeId);
				} else {
					stepIngredientFragment = StepIngredientFragment.createFragment(recipeRef);
				}
				return stepIngredientFragment;
			default:
				if (stepList == null) {
					return StepDetailFragment.createFragment(firebaseStepList.get(position - 2));
				} else {
					return StepDetailFragment.createFragment(stepList.get(position - 2), cutId, categoryId);
				}
		}

	}

	@Override
	public int getCount() {
		if (stepList == null) {
			if (firebaseStepList == null) {
				return 2;
			}
			return firebaseStepList.size() + 2;
		}
		return stepList.size() + 2;
	}


	public void setStepList(List<Integer> stepList) {
		this.stepList = stepList;
		notifyDataSetChanged();
	}

	public void setStepList(ArrayList<String> data) {
		this.stepList = null;
		this.firebaseStepList = data;
		notifyDataSetChanged();
	}
}
