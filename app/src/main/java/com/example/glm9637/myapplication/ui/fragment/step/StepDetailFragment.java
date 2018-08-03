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

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class StepDetailFragment extends Fragment implements View.OnClickListener {
	
	private long stepId;
	private StepFragmentViewModel viewModel;
	private TextView title;
	private TextView description;
	private TextView duration;
	private Button timer;
	private StepEntry step;
	
	public static Fragment createFragment(long stepId) {
		StepDetailFragment fragment = new StepDetailFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(Constants.Arguments.STEP_ID, stepId);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		stepId = getArguments().getLong(Constants.Arguments.STEP_ID);
		View rootView = inflater.inflate(R.layout.fragment_step, container, false);
		title = rootView.findViewById(R.id.txt_title);
		description = rootView.findViewById(R.id.txt_description);
		duration = rootView.findViewById(R.id.txt_duration);
		timer = rootView.findViewById(R.id.btn_timer);
		timer.setOnClickListener(this);
		viewModel = new StepFragmentViewModel(getContext());
		viewModel.setStepId(stepId);
		viewModel.getStep().observe(getActivity(), new Observer<StepEntry>() {
			@Override
			public void onChanged(@Nullable StepEntry stepEntry) {
				title.setText(stepEntry.getName());
				description.setText(stepEntry.getDescription());
				duration.setText(String.valueOf(stepEntry.getDuration()));
				step = stepEntry;
			}
		});
		return rootView;
	}
	
	
	@Override
	public void onClick(View v) {
		
		StepUtils.setStepTimer(getContext(), step);
		
	}
	
}
