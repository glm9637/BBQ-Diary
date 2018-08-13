package com.example.glm9637.myapplication.ui.adapter.recyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EditableIngredientAdapter extends RecyclerView.Adapter<EditableIngredientAdapter.IngredientViewHolder> {

	private final LayoutInflater inflater;
	private final Context context;
	private final List<IngredientEntry> deletedData;
	private List<IngredientEntry> data;

	public EditableIngredientAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.data = new ArrayList<>();
		this.deletedData = new ArrayList<>();
	}

	@NonNull
	@Override
	public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View rootView = inflater.inflate(R.layout.item_ingredient_clickable, parent, false);
		return new IngredientViewHolder(rootView);
	}

	@Override
	public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
		IngredientEntry ingredient = data.get(position);
		holder.Ingredient.setText(context.getString(R.string.ingredient_template, String.valueOf(ingredient.getAmount()), ingredient.getUnit(), ingredient.getName()));
	}

	@Override
	public int getItemCount() {
		if (data == null) {
			return 0;
		}
		return data.size();
	}

	public void addData(IngredientEntry entry) {
		data.add(entry);
		Collections.sort(data, new Comparator<IngredientEntry>() {
			@Override
			public int compare(IngredientEntry ingredientEntry, IngredientEntry t1) {
				return (ingredientEntry.getName().compareTo(t1.getName()));
			}
		});
	}

	public ArrayList<IngredientEntry> getData() {
		ArrayList<IngredientEntry> completeData = new ArrayList<>();
		completeData.addAll(data);
		completeData.addAll(deletedData);
		return completeData;
	}

	public void setData(List<IngredientEntry> data) {
		for (IngredientEntry ingredient : data) {
			if (ingredient.isDeleted()) {
				deletedData.add(ingredient);
			} else {
				this.data.add(ingredient);
			}
		}
		notifyDataSetChanged();
	}

	private void showEditIngredientDialog(final IngredientEntry ingredientEntry) {
		LayoutInflater factory = LayoutInflater.from(context);
		final View deleteDialogView = factory.inflate(R.layout.dialog_ingredient_edit, null);
		final AlertDialog deleteDialog = new AlertDialog.Builder(context).create();
		deleteDialog.setView(deleteDialogView);

		TextInputEditText name = deleteDialogView.findViewById(R.id.txt_name);
		TextInputEditText amount = deleteDialogView.findViewById(R.id.txt_amount);
		TextInputEditText unit = deleteDialogView.findViewById(R.id.txt_unit);

		name.setText(ingredientEntry.getName());
		amount.setText(String.valueOf(ingredientEntry.getAmount()));
		unit.setText(ingredientEntry.getUnit());

		deleteDialogView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				deleteDialog.dismiss();
			}
		});
		deleteDialogView.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextInputEditText name = deleteDialogView.findViewById(R.id.txt_name);
				TextInputEditText amount = deleteDialogView.findViewById(R.id.txt_amount);
				TextInputEditText unit = deleteDialogView.findViewById(R.id.txt_unit);

				if (name.getText().toString().isEmpty()) {
					Toast.makeText(context, "Please Provide a Name or cancel this dialog.", Toast.LENGTH_LONG).show();
					return;
				}

				ingredientEntry.setName(name.getText().toString());
				ingredientEntry.setAmount(Long.valueOf(amount.getText().toString()));
				ingredientEntry.setUnit(unit.getText().toString());
				notifyDataSetChanged();
				deleteDialog.dismiss();
			}
		});
		deleteDialogView.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				data.remove(ingredientEntry);
				notifyDataSetChanged();
				ingredientEntry.setDeleted(true);
				deletedData.add(ingredientEntry);
				deleteDialog.dismiss();
			}
		});

		deleteDialog.show();
	}

	class IngredientViewHolder extends RecyclerView.ViewHolder {

		final TextView Ingredient;

		IngredientViewHolder(View itemView) {
			super(itemView);
			Ingredient = itemView.findViewById(R.id.txt_ingredient);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					IngredientEntry ingredientEntry = data.get(getAdapterPosition());
					showEditIngredientDialog(ingredientEntry);
				}
			});
		}
	}
}
