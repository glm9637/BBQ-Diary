package com.example.glm9637.myapplication.ui.adapter.recyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.model.NoteEntryForList;
import com.example.glm9637.myapplication.ui.activity.NoteActivity;
import com.example.glm9637.myapplication.utils.Constants;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

	private final LayoutInflater inflater;
	private final Activity context;
	private List<NoteEntryForList> data;
	private DateFormat dateFormat;
	private DateFormat timeFormat;

	public NoteAdapter(Activity context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		dateFormat = android.text.format.DateFormat.getDateFormat(context);
		timeFormat = android.text.format.DateFormat.getTimeFormat(context);
	}

	public void setData(List<NoteEntryForList> data) {
		this.data = data;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View rootView = inflater.inflate(R.layout.item_note, parent, false);

		return new NoteViewHolder(rootView);
	}

	@Override
	public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
		NoteEntryForList noteEntry = data.get(position);
		holder.Name.setText(noteEntry.getName());
		Date date = new Date(noteEntry.getDate());
		holder.Date.setText(context.getString(R.string.date_and_time, dateFormat.format(date), timeFormat.format(date)));
	}

	@Override
	public int getItemCount() {
		if (data == null) {
			return 0;
		}
		return data.size();
	}

	class NoteViewHolder extends RecyclerView.ViewHolder {

		final TextView Name;
		final TextView Date;

		NoteViewHolder(View itemView) {
			super(itemView);
			Name = itemView.findViewById(R.id.txt_name);
			Date = itemView.findViewById(R.id.txt_date);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					NoteActivity.reset();
					NoteEntryForList item = data.get(getAdapterPosition());
					Intent intent = new Intent(context, NoteActivity.class);
					intent.putExtra(Constants.Arguments.NOTE_ID, item.getId());
					intent.putExtra(Constants.Arguments.RECIPE_ID, item.getRecipeId());
					ActivityOptionsCompat options = ActivityOptionsCompat.
							makeSceneTransitionAnimation(context,Name,"note name");
					context.startActivity(intent,options.toBundle());
				}
			});
		}
	}
}
