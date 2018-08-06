package com.example.glm9637.myapplication.ui.adapter.recyclerView;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.ui.activity.RecipeActivity;
import com.example.glm9637.myapplication.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRecipeAdapter extends RecyclerView.Adapter<FirebaseRecipeAdapter.RecipeViewHolder> {

	private final LayoutInflater inflater;
	private final Activity context;
	private List<RecipeEntry> data;

	public FirebaseRecipeAdapter(Activity context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public void setData(List<RecipeEntry> data) {
		this.data = data;
		notifyDataSetChanged();
	}

	public void addData(RecipeEntry data){
		if(this.data==null){
			this.data = new ArrayList<>();
		}
		this.data.add(data);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View rootView = inflater.inflate(R.layout.item_recipe, parent, false);

		return new RecipeViewHolder(rootView);
	}

	@Override
	public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
		RecipeEntry recipe = data.get(position);
		holder.Name.setText(recipe.getName());
		holder.Description.setText(recipe.getShortDescription());
	}

	@Override
	public int getItemCount() {
		if (data == null) {
			return 0;
		}
		return data.size();
	}

	class RecipeViewHolder extends RecyclerView.ViewHolder {

		final TextView Name;
		final TextView Description;

		RecipeViewHolder(View itemView) {
			super(itemView);
			Name = itemView.findViewById(R.id.txt_name);
			Description = itemView.findViewById(R.id.txt_description);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					RecipeActivity.reset();
					RecipeEntry item = data.get(getAdapterPosition());
					Intent intent = new Intent(context, RecipeActivity.class);
					intent.putExtra(Constants.Arguments.RECIPE_ID, Long.valueOf(item.getId()));
					intent.putExtra(Constants.Arguments.CATEGORY_ID, Long.valueOf(item.getCategoryId()));
					intent.putExtra(Constants.Arguments.CUT_ID, Long.valueOf(item.getCutId()));
					intent.putExtra(Constants.Arguments.FIREBASE_REFERENCE,item.getDatabaseReference());
					ActivityOptionsCompat options = ActivityOptionsCompat.
							makeSceneTransitionAnimation(context, Name, "recipe name");
					context.startActivity(intent, options.toBundle());
				}
			});
		}
	}
}
