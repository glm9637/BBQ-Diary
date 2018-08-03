package com.example.glm9637.myapplication.ui.fragment.edit_recipe;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.StepEntry;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.EditableStepAdapter;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.RecipeStepsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class EditRecipeStepsFragment extends Fragment {

	private EditableStepAdapter adapter;

	private RecipeStepsViewModel viewModel;

	public static EditRecipeStepsFragment createFragment() {
		return new EditRecipeStepsFragment();
	}

	public static EditRecipeStepsFragment createFragment(long recipeId) {
		EditRecipeStepsFragment fragment = new EditRecipeStepsFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(Constants.Arguments.RECIPE_ID, recipeId);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_edit_list, container, false);
		TextView label = rootView.findViewById(R.id.txt_label);
		label.setText(R.string.title_step_edit);
		RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		adapter = new EditableStepAdapter(getContext());
		recyclerView.setAdapter(adapter);
		if (getArguments() != null) {
			long recipeId = getArguments().getLong(Constants.Arguments.RECIPE_ID);
			viewModel = new RecipeStepsViewModel(getContext(), recipeId);
			viewModel.getSteps().observe(getActivity(), new Observer<List<StepEntry>>() {
				@Override
				public void onChanged(@Nullable List<StepEntry> stepEntries) {
					viewModel.getSteps().removeObserver(this);
					adapter.setData(stepEntries);
				}
			});
		}else {
			adapter.setData(new ArrayList<StepEntry>());
		}
		rootView.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				showAddSteps();
			}
		});
		return rootView;
	}

	private void showAddSteps() {
		LayoutInflater factory = LayoutInflater.from(getContext());
		final View addDialogView = factory.inflate(R.layout.dialog_step_edit, null);
		final AlertDialog addDialog = new AlertDialog.Builder(getContext()).create();
		addDialog.setView(addDialogView);
		addDialogView.findViewById(R.id.btn_delete).setVisibility(View.GONE);


		addDialogView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//your business logic
				addDialog.dismiss();
			}
		});
		addDialogView.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextInputEditText name = addDialogView.findViewById(R.id.txt_name);
				TextInputEditText duration = addDialogView.findViewById(R.id.txt_duration);
				TextInputEditText description = addDialogView.findViewById(R.id.txt_description);
				TextInputEditText order = addDialogView.findViewById(R.id.txt_order);

				if (name.getText().toString().isEmpty() || order.getText().toString().isEmpty()) {
					Toast.makeText(getContext(), "Please Provide a Name and Order, or cancel this dialog.", Toast.LENGTH_LONG).show();
					return;
				}

				StepEntry stepEntry = new StepEntry(Integer.parseInt(order.getText().toString()), name.getText().toString(), description.getText().toString(), Long.parseLong(duration.getText().toString()));
				adapter.addData(stepEntry);
				adapter.notifyDataSetChanged();
				addDialog.dismiss();
			}
		});

		addDialog.show();
	}

	public List<StepEntry> getSteps() {
		return adapter.getData();
	}
}
