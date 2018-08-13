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

public class RecipeStepActivity extends AppCompatActivity implements StepListAdapter.ListEntryClickedListener {

	long recipeId;
	private long categoryId;
	private long cutId;
	String firebaseRef;
	private RecipeStepsFragmentAdapter adapter;
	private ViewPager pager;
	private TabLayout tabLayout;
	private FirebaseDatabase firebaseDatabase;
	private DatabaseReference recipeDatabaseReference;
	private ValueEventListener valueEventListener;
	private int fragmentPosition;
	private long stepID;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_step);

		recipeId = getIntent().getLongExtra(Constants.Arguments.RECIPE_ID, 0);
		categoryId = getIntent().getLongExtra(Constants.Arguments.CATEGORY_ID, 0);
		cutId = getIntent().getLongExtra(Constants.Arguments.CUT_ID, 0);
		firebaseRef = getIntent().getStringExtra(Constants.Arguments.FIREBASE_REFERENCE);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		stepID = getIntent().getLongExtra(Constants.Arguments.STEP_ID, 0);

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
				if (pager.getCurrentItem() != fragmentPosition) {
					pager.setCurrentItem(fragmentPosition, false);
				}
				Log.w("StepId",stepID+"");
				if(stepID>0){
					for(int i = 0;i<idList.size();i++){
						if(stepID == idList.get(i)){
							pager.setCurrentItem(i+2);
							stepID = 0;
							return;
						}
					}
				}
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
				if (pager.getCurrentItem() != fragmentPosition) {
					pager.setCurrentItem(fragmentPosition, false);
				}
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
		outState.putInt("fragment position", pager.getCurrentItem());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		fragmentPosition = savedInstanceState.getInt("fragment position");
		pager.setCurrentItem(fragmentPosition, false);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.w("RecipeStepActivity", "onResume");
		if (firebaseRef == null) {
			adapter = new RecipeStepsFragmentAdapter(getSupportFragmentManager(), recipeId, cutId, categoryId);
			initDataRoom();
		} else {
			adapter = new RecipeStepsFragmentAdapter(getSupportFragmentManager(), firebaseRef, cutId, categoryId);
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
		getMenuInflater().inflate(R.menu.menu_activity_step, menu);
		return true;
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

	@Override
	public void onListEntryClicked(int position) {
		pager.setCurrentItem(position);
	}
}
