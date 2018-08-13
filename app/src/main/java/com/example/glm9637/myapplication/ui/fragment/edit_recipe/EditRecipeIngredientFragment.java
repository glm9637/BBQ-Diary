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

import java.util.ArrayList;
import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class EditRecipeIngredientFragment extends Fragment {


	private EditableIngredientAdapter adapter;
	private IngredientFragmentViewModel viewModel;

	private static Bundle saveInstance;

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
		} else {
			adapter.setData(new ArrayList<IngredientEntry>());
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
				//your business logic
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
					Toast.makeText(getContext(), "Please Provide a Name or cancel this dialog.", Toast.LENGTH_LONG).show();
					return;
				}

				String ingredientName = name.getText().toString();
				String ingredientAmount = amount.getText().toString();
				String ingredientUnit = unit.getText().toString();
				if (ingredientName.isEmpty()) {
					Toast.makeText(getContext(),"Please specify a name or cancel",Toast.LENGTH_LONG).show();;
				} else {
					if(ingredientAmount.isEmpty()){
						ingredientAmount = "0";
					}
					IngredientEntry entry = new IngredientEntry(ingredientName, Integer.parseInt(ingredientAmount), ingredientUnit);
					adapter.addData(entry);
					adapter.notifyDataSetChanged();
					deleteDialog.dismiss();
				}
			}
		});


		deleteDialog.show();
	}


	@Override
	public void onPause() {
		super.onPause();
		saveInstance = new Bundle();
		saveInstance.putParcelableArrayList(Constants.Arguments.INGREDIENT_DATA, adapter.getData());
	}

	@Override
	public void onResume() {
		super.onResume();
		if (saveInstance != null) {
			adapter.setData(saveInstance.<IngredientEntry>getParcelableArrayList(Constants.Arguments.INGREDIENT_DATA));
		}
	}

	public List<IngredientEntry> getIngredients() {
		return adapter.getData();
	}

	public static void resetData() {
		saveInstance = null;
	}
}
