package com.example.glm9637.myapplication.ui.fragment.cut;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.RecipeAdapter;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.RecipeFragmentViewModel;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 31.07.2018.
 */
public class RecipeFragment extends Fragment {
	
	
	private static Bundle mBundleRecyclerViewState;
	private RecipeFragmentViewModel viewModel;
	private RecyclerView recyclerView;
	private RecipeAdapter adapter;


	public static RecipeFragment createFragment(long cutId) {
		RecipeFragment fragment = new RecipeFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(Constants.Arguments.CUT_ID, cutId);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		long cutId = getArguments().getLong(Constants.Arguments.CUT_ID);
		View rootView = inflater.inflate(R.layout.fragment_list_addable, container, false);
		recyclerView = rootView.findViewById(R.id.recyclerview);
		adapter = new RecipeAdapter(getActivity());
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		
		viewModel = new RecipeFragmentViewModel(getContext(), cutId);
		viewModel.getRecipeList().observe(this, new Observer<List<RecipeEntry>>() {
			@Override
			public void onChanged(@Nullable List<RecipeEntry> cutEntries) {
				viewModel.getRecipeList().removeObserver(this);
				adapter.setData(cutEntries);
				
			}
		});

		return rootView;
	}
	
	@Override
	public void onPause() {
		super.onPause();
		super.onPause();
		mBundleRecyclerViewState = new Bundle();
		Parcelable mLayoutManagerState = recyclerView.getLayoutManager().onSaveInstanceState();
		mBundleRecyclerViewState.putParcelable(Constants.Arguments.SAVE_INSTANCE_RECYCLERVIEW, mLayoutManagerState);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (mBundleRecyclerViewState != null) {
			Parcelable listState = mBundleRecyclerViewState.getParcelable(Constants.Arguments.SAVE_INSTANCE_RECYCLERVIEW);
			recyclerView.getLayoutManager().onRestoreInstanceState(listState);
		}
	}
	
	public static void reset() {
		mBundleRecyclerViewState = null;
	}
}
