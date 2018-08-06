package com.example.glm9637.myapplication.ui.adapter.recyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.StepEntry;

import java.util.ArrayList;
import java.util.List;

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepListViewHolder> {

	private final LayoutInflater inflater;
	private final Context context;
	private List<StepEntry> data;
	private ListEntryClickedListener onClickListener;

	public StepListAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public void addData(StepEntry data) {
		if (this.data == null) {
			this.data = new ArrayList<>();
		}
		this.data.add(data);
		notifyDataSetChanged();
	}

	public void setData(List<StepEntry> data) {
		this.data = data;
		notifyDataSetChanged();
	}

	public void setOnClickListener(ListEntryClickedListener onClickListener) {
		this.onClickListener = onClickListener;
	}

	@NonNull
	@Override
	public StepListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View rootView = inflater.inflate(R.layout.item_step_list, parent, false);

		return new StepListViewHolder(rootView);
	}

	@Override
	public void onBindViewHolder(@NonNull StepListViewHolder holder, int position) {
		if (position == 0) {
			holder.Name.setText(R.string.ingredients_title);
			holder.Time.setText("");
			holder.Description.setText(R.string.ingredients_description);
		} else {
			StepEntry recipe = data.get(position - 1);
			holder.Name.setText(recipe.getName());
			holder.Description.setText(recipe.getDescription());
			holder.Time.setText(context.getString(R.string.time_min, recipe.getDuration()));
		}
		if (holder.Time.getText().toString().isEmpty()) {
			holder.Time.setVisibility(View.INVISIBLE);
		} else {
			holder.Time.setVisibility(View.VISIBLE);
		}
		holder.Number.setText(context.getString(R.string.order, position+1));
	}

	@Override
	public int getItemCount() {
		if (data == null) {
			return 0;
		}
		return data.size() + 1;
	}

	public interface ListEntryClickedListener {
		void onListEntryClicked(int position);
	}

	class StepListViewHolder extends RecyclerView.ViewHolder {

		final TextView Name;
		final TextView Description;
		final TextView Time;
		final TextView Number;

		StepListViewHolder(View itemView) {
			super(itemView);
			Name = itemView.findViewById(R.id.txt_name);
			Description = itemView.findViewById(R.id.txt_description);
			Time = itemView.findViewById(R.id.txt_duration);
			Number = itemView.findViewById(R.id.txt_step_number);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onClickListener.onListEntryClicked(getAdapterPosition() + 1);

				}
			});
		}
	}
}
