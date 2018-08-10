package com.example.glm9637.myapplication.ui.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.ui.adapter.fragment.RecipeStepsFragmentAdapter;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.StepListAdapter;
import com.example.glm9637.myapplication.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecipeStepActivity extends AppCompatActivity {

	long recipeId;
	String firebaseRef;
	private RecipeStepsFragmentAdapter adapter;
	private ViewPager pager;
	private TabLayout tabLayout;
	private FirebaseDatabase firebaseDatabase;
	private DatabaseReference recipeDatabaseReference;
	private ValueEventListener valueEventListener;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_step);

		recipeId = getIntent().getLongExtra(Constants.Arguments.RECIPE_ID, 0);
		firebaseRef = getIntent().getStringExtra(Constants.Arguments.FIREBASE_REFERENCE);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		tabLayout = findViewById(R.id.tab_layout);
		pager = findViewById(R.id.view_pager);
	}

	private void initDataRoom() {
		RecipeDatabase database = RecipeDatabase.getInstance(RecipeStepActivity.this);
		database.getStepDao().loadStepIdList(recipeId).observe(this, new Observer<List<Integer>>() {
			@Override
			public void onChanged(@Nullable List<Integer> idList) {
				adapter.setStepList(idList);
				pager.setAdapter(adapter);
				tabLayout.setupWithViewPager(pager);
				adapter.notifyDataSetChanged();
			}
		});
	}

	private void initDataFirebase() {
		firebaseDatabase = FirebaseDatabase.getInstance();
		recipeDatabaseReference = firebaseDatabase.getReference(firebaseRef);
		valueEventListener = new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				ArrayList<String> data = new ArrayList<>();
				for (DataSnapshot snapshot : dataSnapshot.child("/steps").getChildren()) {
					data.add(String.format("%1s/%2s/%3s", firebaseRef, "steps", snapshot.getKey()));
				}
				adapter.setStepList(data);
				pager.setAdapter(adapter);
				tabLayout.setupWithViewPager(pager);
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		};

		recipeDatabaseReference.addValueEventListener(valueEventListener);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("test", pager.getCurrentItem());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		int item = savedInstanceState.getInt("test");
		pager.setCurrentItem(item, false);
	}

	@Override
	protected void onResume() {
		super.onResume();
		StepListAdapter.ListEntryClickedListener listEntryClickedListener = new StepListAdapter.ListEntryClickedListener() {
			@Override
			public void onListEntryClicked(int position) {
				pager.setCurrentItem(position, true);
			}
		};
		Log.w("RecipeStepActivity", "onResume");
		if (firebaseRef == null) {
			if (adapter == null) {
				adapter = new RecipeStepsFragmentAdapter(getSupportFragmentManager(), recipeId, listEntryClickedListener);
			} else {
				Log.w("Step", "Adapter not null");
			}
			initDataRoom();
		} else {
			if (adapter == null) {
				adapter = new RecipeStepsFragmentAdapter(getSupportFragmentManager(), firebaseRef, listEntryClickedListener);
			} else {
				Log.w("Step", "Adapter not null");
			}
			initDataFirebase();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		detachDatabaseReadListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.mnu_step:
				pager.setCurrentItem(0, true);
			default:
				return super.onOptionsItemSelected(item);
		}

	}

	private void detachDatabaseReadListener() {
		if (valueEventListener != null) {
			recipeDatabaseReference.removeEventListener(valueEventListener);
			valueEventListener = null;
		}
	}
}
