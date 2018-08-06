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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class StepIngredientFragment extends Fragment {

	long recipeId;
	String firebaseRef;
	private IngredientAdapter adapter;
	private IngredientFragmentViewModel viewModel;
	private FirebaseDatabase firebaseDatabase;
	private DatabaseReference recipeDatabaseReference;
	private ValueEventListener valueEventListener;

	public static StepIngredientFragment createFragment(long recipeId) {
		StepIngredientFragment fragment = new StepIngredientFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(Constants.Arguments.RECIPE_ID, recipeId);
		fragment.setArguments(bundle);
		return fragment;
	}

	public static StepIngredientFragment createFragment(String firebaseRef) {
		StepIngredientFragment fragment = new StepIngredientFragment();
		Bundle bundle = new Bundle();
		bundle.putString(Constants.Arguments.FIREBASE_REFERENCE, firebaseRef);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		recipeId = getArguments().getLong(Constants.Arguments.RECIPE_ID, 0);
		firebaseRef = getArguments().getString(Constants.Arguments.FIREBASE_REFERENCE);
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		adapter = new IngredientAdapter(getContext());
		if (firebaseRef == null) {
			initDataRoom();
		} else {
			initDataFromFirebase();
		}
		recyclerView.setAdapter(adapter);


		return rootView;
	}

	private void initDataFromFirebase() {
		firebaseDatabase = FirebaseDatabase.getInstance();
		recipeDatabaseReference = firebaseDatabase.getReference().child(firebaseRef);

		if (valueEventListener == null) {
			valueEventListener = new ValueEventListener() {
				@Override
				public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
					for (DataSnapshot snapshot : dataSnapshot.child("/ingredients").getChildren()) {
						IngredientEntry ingredientEntry = snapshot.getValue(IngredientEntry.class);
						adapter.addData(ingredientEntry);
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
		detachDatabaseReadListener();
	}

	private void detachDatabaseReadListener() {
		if (valueEventListener != null) {
			recipeDatabaseReference.removeEventListener(valueEventListener);
			valueEventListener = null;
		}
	}


	private void initDataRoom() {
		viewModel = new IngredientFragmentViewModel(getContext());
		viewModel.setRecipeId(recipeId);
		viewModel.getData().observe(getActivity(), new Observer<List<IngredientEntry>>() {
			@Override
			public void onChanged(@Nullable List<IngredientEntry> ingredientEntries) {
				viewModel.getData().removeObserver(this);
				adapter.setData(ingredientEntries);
			}
		});
	}
}
