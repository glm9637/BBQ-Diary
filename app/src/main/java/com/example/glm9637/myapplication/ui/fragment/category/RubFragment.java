package com.example.glm9637.myapplication.ui.fragment.category;

import android.arch.lifecycle.Observer;
import android.content.Intent;
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
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.ui.activity.EditRecipeActivity;
import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.RecipeAdapter;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.RubFragmentViewModel;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 23.07.2018.
 */
public class RubFragment extends Fragment {

	private long categoryId;

    private RubFragmentViewModel viewModel;
	private RecipeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        categoryId = getArguments().getLong(Constants.Arguments.CATEGORY_ID);
        View rootView = inflater.inflate(R.layout.fragment_list_addable, container, false);
		RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);
        adapter = new RecipeAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new RubFragmentViewModel(RecipeDatabase.getInstance(getContext()), categoryId);
        viewModel.getCutList().observe(this, new Observer<List<RecipeEntry>>() {
            @Override
            public void onChanged(@Nullable List<RecipeEntry> cutEntries) {
                viewModel.getCutList().removeObserver(this);
                adapter.setData(cutEntries);

            }
        });
        
        rootView.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
				Intent intent = new Intent(getContext(), EditRecipeActivity.class);
				intent.putExtra(Constants.Arguments.CATEGORY_ID,categoryId);
				intent.putExtra(Constants.Arguments.IS_RUB,true);
				startActivity(intent);
	        }
        });
        
        return rootView;
    }

    public static RubFragment createFragment(long categoryId) {
        RubFragment fragment = new RubFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.Arguments.CATEGORY_ID, categoryId);
        fragment.setArguments(bundle);
        return fragment;
    }

}
