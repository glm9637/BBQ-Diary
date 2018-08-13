package com.example.glm9637.myapplication.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.model.NoteEntryForList;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.NoteAdapter;
import com.example.glm9637.myapplication.ui.fragment.category.CutsFragment;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.NoteListViewModel;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {
	private static Bundle mState;
	private long recipeId;
	private NoteAdapter adapter;
	private NoteListViewModel viewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_notes);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		RecyclerView recyclerView = findViewById(R.id.recyclerview);
		adapter = new NoteAdapter(this);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recipeId = getIntent().getLongExtra(Constants.Arguments.RECIPE_ID, 0);


		findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				NoteActivity.reset();
				Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
				intent.putExtra(Constants.Arguments.RECIPE_ID, recipeId);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		mState = new Bundle();
		mState.putLong(Constants.Arguments.RECIPE_ID, recipeId);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mState != null && recipeId == 0) {
			recipeId = mState.getLong(Constants.Arguments.RECIPE_ID);
		} else {
			CutsFragment.reset();
		}

		viewModel = new NoteListViewModel(this, recipeId);
		viewModel.getNoteList().observe(this, new Observer<List<NoteEntryForList>>() {
			@Override
			public void onChanged(@Nullable List<NoteEntryForList> noteEntryForLists) {
				viewModel.getNoteList().removeObserver(this);
				adapter.setData(noteEntryForLists);
			}
		});


	}


}
