package com.example.glm9637.myapplication.ui.fragment.step;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
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
import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.database.entry.StepEntry;
import com.example.glm9637.myapplication.ui.activity.RecipeActivity;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.StepListAdapter;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.RecipeActivityViewModel;
import com.example.glm9637.myapplication.view_model.RecipeStepsViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class StepListFragment extends Fragment {

	private StepListAdapter adapter;
	private RecyclerView recyclerView;

	private StepListAdapter.ListEntryClickedListener entryClickedListener;

	private FirebaseDatabase firebaseDatabase;
	private DatabaseReference recipeDatabaseReference;
	private ValueEventListener valueEventListener;

	private long recipeId;
	private String firebaseReference;
	
	public static StepListFragment createFragment(long recipeId) {
		StepListFragment fragment = new StepListFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(Constants.Arguments.RECIPE_ID, recipeId);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.w("StepListFragment","onCreateView");
		recipeId = getArguments().getLong(Constants.Arguments.RECIPE_ID,0);
		firebaseReference = getArguments().getString(Constants.Arguments.FIREBASE_REFERENCE);
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		recyclerView = rootView.findViewById(R.id.recyclerview);
		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.w("StepListFragment","onResume");
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		adapter = new StepListAdapter(getContext());
		if(entryClickedListener!=null){
			adapter.setOnClickListener(entryClickedListener);
		}
		recyclerView.setAdapter(adapter);
		if(firebaseReference==null){
			initDataFromRoom();
		}else {
			initDataFromFirebase();
		}
	}

	public void setOnStepClickListener(StepListAdapter.ListEntryClickedListener listener){
		entryClickedListener=listener;
		if(adapter!=null){
			adapter.setOnClickListener(entryClickedListener);
		}
	}


	private void initDataFromRoom(){
		RecipeStepsViewModel viewModel = new RecipeStepsViewModel(getContext(), recipeId);
		viewModel.getSteps().observe(this, new Observer<List<StepEntry>>() {
			@Override
			public void onChanged(@Nullable List<StepEntry> stepEntries) {
				adapter.setData(stepEntries);
			}
		});
	}

	private void initDataFromFirebase() {
		firebaseDatabase = FirebaseDatabase.getInstance();
		recipeDatabaseReference = firebaseDatabase.getReference().child(firebaseReference);

		if (valueEventListener == null) {
			valueEventListener = new ValueEventListener() {
				@Override
				public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
					for(DataSnapshot snapshot: dataSnapshot.child("/steps").getChildren()){
						StepEntry stepEntry = snapshot.getValue(StepEntry.class);
						adapter.addData(stepEntry);
					}
				}

				public void onCancelled(@NonNull DatabaseError databaseError) {
				}
			};
			recipeDatabaseReference.addValueEventListener(valueEventListener);

		}
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.w("StepListFragment","onPause");
		detachDatabaseReadListener();
	}

	private void detachDatabaseReadListener() {
		if (valueEventListener != null) {
			recipeDatabaseReference.removeEventListener(valueEventListener);
			valueEventListener = null;
		}
	}


	public static StepListFragment createFragment(String recipeRef) {
		StepListFragment fragment = new StepListFragment();
		Bundle bundle = new Bundle();
		bundle.putString(Constants.Arguments.FIREBASE_REFERENCE, recipeRef);
		fragment.setArguments(bundle);
		return fragment;

	}
}
