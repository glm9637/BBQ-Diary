package com.example.glm9637.myapplication.ui.fragment.cut;

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
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.FirebaseRecipeAdapter;
import com.example.glm9637.myapplication.utils.Constants;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Erzeugt von M. Fengels am 31.07.2018.
 */
public class DiscoverFragment extends Fragment {
	private static Bundle instanceState;
	private AdView adView;
	private long categoryId;
	private long cutId;
	private String reference;
	// Firebase instance variables
	private FirebaseDatabase firebaseDatabase;
	private DatabaseReference recipeDatabaseReference;
	private ChildEventListener childEventListener;
	private RecyclerView recyclerView;
	private FirebaseRecipeAdapter adapter;


	public static DiscoverFragment createFragment(long cutId, long categoryId) {
		DiscoverFragment fragment = new DiscoverFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(Constants.Arguments.CUT_ID, cutId);
		bundle.putLong(Constants.Arguments.CATEGORY_ID, categoryId);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_list_ad, container, false);

		cutId = getArguments().getLong(Constants.Arguments.CUT_ID);
		categoryId = getArguments().getLong(Constants.Arguments.CATEGORY_ID);

		reference = String.format("/recipes/category-%d/cuts/cut-%d/recipes", categoryId, cutId);

		initializeAd(rootView);
		initializeRecyclerView(rootView);
		initializeFirebase();

		return rootView;

	}

	private void initializeRecyclerView(View rootView) {
		recyclerView = rootView.findViewById(R.id.recyclerview);
		adapter = new FirebaseRecipeAdapter(getActivity());
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
	}


	private void initializeAd(View rootView) {
		adView = rootView.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}

	private void initializeFirebase() {
		firebaseDatabase = FirebaseDatabase.getInstance();

		recipeDatabaseReference = firebaseDatabase.getReference().child(reference);
	}

	private void attachDatabaseReadListener() {
		if (childEventListener == null) {
			childEventListener = new ChildEventListener() {
				@Override
				public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
					RecipeEntry recipeEntry = dataSnapshot.getValue(RecipeEntry.class);
					recipeEntry.setDatabaseReference(String.format("%1s/%2s", reference, dataSnapshot.getKey()));
					recipeEntry.setCategoryId(categoryId);
					recipeEntry.setCutId(cutId);
					adapter.addData(recipeEntry);
					if (instanceState != null) {
						Parcelable listState = instanceState.getParcelable(Constants.Arguments.SAVE_INSTANCE_RECYCLERVIEW);
						recyclerView.getLayoutManager().onRestoreInstanceState(listState);
					}
				}

				public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
				}

				public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
				}

				public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
				}

				public void onCancelled(@NonNull DatabaseError databaseError) {
				}
			};
			recipeDatabaseReference.addChildEventListener(childEventListener);
		}
	}

	private void detachDatabaseReadListener() {
		if (childEventListener != null) {
			recipeDatabaseReference.removeEventListener(childEventListener);
			childEventListener = null;
			adapter.setData(null);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		attachDatabaseReadListener();
		instanceState = new Bundle();
		Parcelable mLayoutManagerState = recyclerView.getLayoutManager().onSaveInstanceState();
		instanceState.putParcelable(Constants.Arguments.SAVE_INSTANCE_RECYCLERVIEW, mLayoutManagerState);
	}

	@Override
	public void onPause() {
		super.onPause();
		detachDatabaseReadListener();
		if (instanceState != null) {
			Parcelable listState = instanceState.getParcelable(Constants.Arguments.SAVE_INSTANCE_RECYCLERVIEW);
			recyclerView.getLayoutManager().onRestoreInstanceState(listState);
		}
	}

	public static void reset() {
		instanceState = null;
	}
}
