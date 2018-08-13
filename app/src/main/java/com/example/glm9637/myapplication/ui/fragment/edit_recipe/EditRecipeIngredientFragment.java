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
import android.widget.Toast;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.EditableIngredientAdapter;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.IngredientFragmentViewModel;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class EditRecipeIngredientFragment extends Fragment {


	private static Bundle instanceState;
	private EditableIngredientAdapter adapter;
	private IngredientFragmentViewModel viewModel;

	public static EditRecipeIngredientFragment createFragment() {

		return new EditRecipeIngredientFragment();
	}

	public static EditRecipeIngredientFragment createFragment(long recipeId) {
		EditRecipeIngredientFragment fragment = new EditRecipeIngredientFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(Constants.Arguments.RECIPE_ID, recipeId);
		fragment.setArguments(bundle);
		return fragment;
	}

	public static void reset() {
		instanceState = null;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_edit_list, container, false);
		RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		adapter = new EditableIngredientAdapter(getContext());
		recyclerView.setAdapter(adapter);
		if (getArguments() != null) {
			long recipeId = getArguments().getLong(Constants.Arguments.RECIPE_ID);
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

		rootView.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				showAddIngredientDialog();
			}
		});

		return rootView;
	}

	private void showAddIngredientDialog() {
		LayoutInflater factory = LayoutInflater.from(getContext());
		View deleteDialogView = factory.inflate(R.layout.dialog_ingredient_edit, null);
		final AlertDialog deleteDialog = new AlertDialog.Builder(getContext()).create();
		deleteDialog.setView(deleteDialogView);
		deleteDialogView.findViewById(R.id.btn_delete).setVisibility(View.GONE);
		deleteDialogView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				deleteDialog.dismiss();
			}
		});
		deleteDialogView.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextInputEditText name = deleteDialog.findViewById(R.id.txt_name);
				TextInputEditText amount = deleteDialog.findViewById(R.id.txt_amount);
				TextInputEditText unit = deleteDialog.findViewById(R.id.txt_unit);

				if (name.getText().toString().isEmpty()) {
					Toast.makeText(getContext(), R.string.ingredient_dialog_error, Toast.LENGTH_LONG).show();
					return;
				}

				IngredientEntry entry = new IngredientEntry(name.getText().toString(), Integer.parseInt(amount.getText().toString()), unit.getText().toString());
				adapter.addData(entry);
				adapter.notifyDataSetChanged();
				deleteDialog.dismiss();
			}
		});


		deleteDialog.show();
	}

	public List<IngredientEntry> getIngredients() {
		if (adapter == null) {
			return instanceState.getParcelableArrayList(Constants.Arguments.EDIT_INGREDIENTS_DATA);
		}
		return adapter.getData();
	}

	@Override
	public void onPause() {
		super.onPause();
		instanceState = new Bundle();
		instanceState.putParcelableArrayList(Constants.Arguments.EDIT_INGREDIENTS_DATA, adapter.getData());
	}

	@Override
	public void onResume() {
		super.onResume();
		if (instanceState != null) {
			adapter.setData(instanceState.<IngredientEntry>getParcelableArrayList(Constants.Arguments.EDIT_INGREDIENTS_DATA));
		}
	}

}
