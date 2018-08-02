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
import com.example.glm9637.myapplication.database.entry.StepEntry;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.StepListAdapter;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.RecipeStepsViewModel;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class StepListFragment extends Fragment {
	
	long recipeId;
	RecyclerView recyclerView;
	StepListAdapter adapter;
	
	RecipeStepsViewModel viewModel;
	
	public static Fragment createFragment(long recipeId) {
		StepListFragment fragment = new StepListFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(Constants.Arguments.RECIPE_ID, recipeId);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		recipeId = getArguments().getLong(Constants.Arguments.RECIPE_ID);
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		recyclerView = rootView.findViewById(R.id.recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		adapter = new StepListAdapter(getContext(), new StepListAdapter.ListEntryClickedListener() {
			@Override
			public void onListEntryClicked(int position) {
			
			}
		});
		recyclerView.setAdapter(adapter);
		viewModel = new RecipeStepsViewModel(getContext(), recipeId);
		viewModel.getSteps().observe(this, new Observer<List<StepEntry>>() {
			@Override
			public void onChanged(@Nullable List<StepEntry> stepEntries) {
				adapter.setData(stepEntries);
			}
		});
		return rootView;
	}
}
