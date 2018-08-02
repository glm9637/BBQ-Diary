package com.example.glm9637.myapplication.ui.adapter.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.glm9637.myapplication.ui.fragment.cut.RecipeFragment;
import com.example.glm9637.myapplication.ui.fragment.step.StepIngredientFragment;
import com.example.glm9637.myapplication.ui.fragment.step.StepListFragment;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class RecipeStepsFragmentAdapter extends FragmentPagerAdapter {
	
	private long recipeId;
	
	private List<Integer> stepList;
	
	public RecipeStepsFragmentAdapter(FragmentManager fm, long recipeId) {
		super(fm);
		this.recipeId = recipeId;
	}
	
	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return StepListFragment.createFragment(recipeId);
			case 1:
				return StepIngredientFragment.createFragment(recipeId);
			default:
				return RecipeFragment.createFragment(recipeId);
		}
		
	}
	
	@Override
	public int getCount() {
		if(stepList==null){
			return 2;
		}
		return stepList.size()+2;
	}
	
	
	public void setStepList(List<Integer> stepList) {
		this.stepList = stepList;
		notifyDataSetChanged();
	}
}
