package com.example.glm9637.myapplication.ui.fragment.step;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.IngredientAdapter;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.IngredientFragmentViewModel;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class StepIngredientFragment extends Fragment{

	private IngredientAdapter adapter;
	
	private IngredientFragmentViewModel viewModel;
	
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
		long recipeId = getArguments().getLong(Constants.Arguments.RECIPE_ID);
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		adapter = new IngredientAdapter(getContext());
		recyclerView.setAdapter(adapter);
		viewModel = new IngredientFragmentViewModel(getContext());
		viewModel.setRecipeId(recipeId);
		viewModel.getData().observe(getActivity(), new Observer<List<IngredientEntry>>() {
			@Override
			public void onChanged(@Nullable List<IngredientEntry> ingredientEntries) {
				viewModel.getData().removeObserver(this);
				adapter.setData(ingredientEntries);
			}
		});
		
		return rootView;
	}
}
