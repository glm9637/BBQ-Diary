package com.example.glm9637.myapplication.ui.fragment.step;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.utils.Constants;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class StepIngredientFragment extends Fragment{
	
	long recipeId;
	
	public static Fragment createFragment(long recipeId) {
		StepIngredientFragment fragment = new StepIngredientFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(Constants.Arguments.RECIPE_ID,recipeId);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		recipeId = getArguments().getLong(Constants.Arguments.RECIPE_ID);
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		
		return rootView;
	}
}
