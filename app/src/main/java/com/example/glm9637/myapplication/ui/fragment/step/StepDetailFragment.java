package com.example.glm9637.myapplication.ui.fragment.step;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.StepEntry;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.utils.StepUtils;
import com.example.glm9637.myapplication.view_model.StepFragmentViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class StepDetailFragment extends Fragment implements View.OnClickListener {

	private TextView title;
	private TextView description;
	private TextView duration;
	private StepEntry step;
	private long stepId;
	private String firebaseRef;

	private FirebaseDatabase firebaseDatabase;
	private DatabaseReference recipeDatabaseReference;
	private ValueEventListener valueEventListener;

	public static Fragment createFragment(long stepId) {
		StepDetailFragment fragment = new StepDetailFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(Constants.Arguments.STEP_ID, stepId);
		fragment.setArguments(bundle);
		return fragment;
	}

	public static StepDetailFragment createFragment(String firebasePath) {
		StepDetailFragment fragment = new StepDetailFragment();
		Bundle bundle = new Bundle();
		bundle.putString(Constants.Arguments.FIREBASE_REFERENCE, firebasePath);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		stepId = getArguments().getLong(Constants.Arguments.STEP_ID);
		firebaseRef = getArguments().getString(Constants.Arguments.FIREBASE_REFERENCE);
		View rootView = inflater.inflate(R.layout.fragment_step, container, false);
		title = rootView.findViewById(R.id.txt_title);
		description = rootView.findViewById(R.id.txt_description);
		duration = rootView.findViewById(R.id.txt_duration);
		Button timer = rootView.findViewById(R.id.btn_timer);
		timer.setOnClickListener(this);
		if (firebaseRef == null) {
			loadDataRoom();
		} else {
			loadDataFirebase();
		}
		return rootView;
	}

	private void loadDataRoom() {
		StepFragmentViewModel viewModel = new StepFragmentViewModel(getContext());
		viewModel.setStepId(stepId);
		viewModel.getStep().observe(getActivity(), new Observer<StepEntry>() {
			@Override
			public void onChanged(@Nullable StepEntry stepEntry) {
				displayData(stepEntry);
			}
		});
	}

	private void loadDataFirebase() {
		firebaseDatabase = FirebaseDatabase.getInstance();
		recipeDatabaseReference = firebaseDatabase.getReference(firebaseRef);
		valueEventListener = new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				StepEntry stepEntry = dataSnapshot.getValue(StepEntry.class);
				displayData(stepEntry);

			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		};
		recipeDatabaseReference.addValueEventListener(valueEventListener);
	}

	@Override
	public void onPause() {
		super.onPause();
		detachDatabaseReadListener();
	}

	@Override
	public void onClick(View v) {
		StepUtils.setStepTimer(getContext(), step);
	}

	private void displayData(StepEntry stepEntry) {
		title.setText(stepEntry.getName());
		description.setText(stepEntry.getDescription());
		duration.setText(getContext().getString(R.string.time_min, stepEntry.getDuration()));
		step = stepEntry;
	}

	private void detachDatabaseReadListener() {
		if (valueEventListener != null) {
			recipeDatabaseReference.removeEventListener(valueEventListener);
			valueEventListener = null;
		}
	}
}
