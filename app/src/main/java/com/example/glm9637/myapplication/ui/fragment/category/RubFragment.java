package com.example.glm9637.myapplication.ui.fragment.category;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.RecipeAdapter;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.RubFragmentViewModel;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 23.07.2018.
 */
public class RubFragment extends Fragment {

	private long categoryId;
	private static Bundle instanceState;
	private RubFragmentViewModel viewModel;
	private RecipeAdapter adapter;
	private RecyclerView recyclerView;
	private Observer<List<RecipeEntry>> observer;

	public static RubFragment createFragment(long categoryId) {
		RubFragment fragment = new RubFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(Constants.Arguments.CATEGORY_ID, categoryId);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		categoryId = getArguments().getLong(Constants.Arguments.CATEGORY_ID);
		View rootView = inflater.inflate(R.layout.fragment_list_addable, container, false);
		recyclerView = rootView.findViewById(R.id.recyclerview);
		adapter = new RecipeAdapter(getActivity());
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		viewModel = new RubFragmentViewModel(RecipeDatabase.getInstance(getContext()), categoryId);
		observer = new Observer<List<RecipeEntry>>() {
			@Override
			public void onChanged(@Nullable List<RecipeEntry> recipeEntries) {
				viewModel.getRubList().removeObserver(this);
				adapter.setData(recipeEntries);
			}
		};

		return rootView;
	}

	public static void reset() {
		instanceState = null;
	}

	@Override
	public void onPause() {
		super.onPause();
		instanceState = new Bundle();
		Parcelable mLayoutManagerState = recyclerView.getLayoutManager().onSaveInstanceState();
		instanceState.putParcelable(Constants.Arguments.SAVE_INSTANCE_RECYCLERVIEW, mLayoutManagerState);
		viewModel.getRubList().removeObserver(observer);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (instanceState != null) {
			Parcelable listState = instanceState.getParcelable(Constants.Arguments.SAVE_INSTANCE_RECYCLERVIEW);
			recyclerView.getLayoutManager().onRestoreInstanceState(listState);
		}
		viewModel.getRubList().observe(this, observer);
	}
}
