package com.example.glm9637.myapplication.ui.adapter.recyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

	private final LayoutInflater inflater;
	private final Context context;
	private List<IngredientEntry> data;

	public IngredientAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@NonNull
	@Override
	public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View rootView = inflater.inflate(R.layout.item_ingredient, parent, false);

		return new IngredientViewHolder(rootView);
	}

	@Override
	public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
		IngredientEntry ingredient = data.get(position);
		holder.Ingredient.setText(context.getString(R.string.ingredient_template, String.valueOf(ingredient.getAmount()), ingredient.getUnit(), ingredient.getName()));
		if (position == getItemCount() - 1) {
			holder.Divider.setVisibility(View.INVISIBLE);
		} else {
			holder.Divider.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public int getItemCount() {
		if (data == null) {
			return 0;
		}
		return data.size();
	}

	public void addData(IngredientEntry ingredientEntry) {
		if (data == null) {
			data = new ArrayList<>();
		}
		data.add(ingredientEntry);
		notifyDataSetChanged();
	}

	public List<IngredientEntry> getData() {
		return data;
	}

	public void setData(List<IngredientEntry> data) {
		this.data = data;
		notifyDataSetChanged();
	}

	class IngredientViewHolder extends RecyclerView.ViewHolder {

		final TextView Ingredient;
		final View Divider;

		IngredientViewHolder(View itemView) {
			super(itemView);
			Ingredient = itemView.findViewById(R.id.txt_ingredient);
			Divider = itemView.findViewById(R.id.divider);
		}
	}
}
