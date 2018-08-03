package com.example.glm9637.myapplication.ui.fragment.category;

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
import com.example.glm9637.myapplication.ui.adapter.recyclerView.CutAdapter;
import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.model.CutEntryForList;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.CutFragmentViewModel;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 23.07.2018.
 */
public class CutsFragment extends Fragment {


    private CutFragmentViewModel viewModel;
    private RecyclerView recyclerView;
    private CutAdapter adapter;

	private static Bundle mBundleRecyclerViewState;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Long categoryId = getArguments().getLong(Constants.Arguments.CATEGORY_ID);
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerview);
        adapter = new CutAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new CutFragmentViewModel(RecipeDatabase.getInstance(getContext()), categoryId);
        viewModel.getCutList().observe(this, new Observer<List<CutEntryForList>>() {
            @Override
            public void onChanged(@Nullable List<CutEntryForList> cutEntries) {
                viewModel.getCutList().removeObserver(this);
                adapter.setData(cutEntries);
				if(mBundleRecyclerViewState!=null){
					Parcelable listState = mBundleRecyclerViewState.getParcelable(Constants.Arguments.SAVE_INSTANCE_RECYCLERVIEW);
					recyclerView.getLayoutManager().onRestoreInstanceState(listState);
				}
            }
        });
        return rootView;
    }

    public static CutsFragment createFragment(long categoryId) {
        CutsFragment fragment = new CutsFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.Arguments.CATEGORY_ID, categoryId);
        fragment.setArguments(bundle);
        return fragment;
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
