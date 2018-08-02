package com.example.glm9637.myapplication.ui.fragment.step;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.utils.Constants;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class StepDetailFragment  extends Fragment{
	
	private long stepId;
	
	public static Fragment createFragment(long stepId) {
		StepDetailFragment fragment = new StepDetailFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(Constants.Arguments.STEP_ID,stepId);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		stepId = getArguments().getLong(Constants.Arguments.STEP_ID);
		View rootView = inflater.inflate(R.layout.fragment_step, container, false);
		
		return rootView;
	}
}
