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
import com.example.glm9637.myapplication.database.entry.StepEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EditableStepAdapter extends RecyclerView.Adapter<EditableStepAdapter.StepListViewHolder> {

	private final LayoutInflater inflater;
	private final Context context;
	private List<StepEntry> data;
	private List<StepEntry> deletedData;

	public EditableStepAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		deletedData = new ArrayList<>();
		data = new ArrayList<>();
	}

	@NonNull
	@Override
	public StepListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View rootView = inflater.inflate(R.layout.item_step_list, parent, false);
		return new StepListViewHolder(rootView);
	}

	@Override
	public void onBindViewHolder(@NonNull StepListViewHolder holder, int position) {
		StepEntry step = data.get(position);
		holder.Name.setText(step.getName());
		holder.Description.setText(step.getDescription());
		holder.Time.setText(String.valueOf(step.getDuration()));
		holder.Number.setText(context.getString(R.string.order, step.getOrder()));
	}

	@Override
	public int getItemCount() {
		if (data == null) {
			return 0;
		}
		return data.size();
	}

	public void addData(StepEntry entry) {
		data.add(entry);
		Collections.sort(data, new Comparator<StepEntry>() {
			@Override
			public int compare(StepEntry obj1, StepEntry obj2) {
				return obj1.getOrder() - obj2.getOrder();
			}
		});
	}

	public ArrayList<StepEntry> getData() {
		ArrayList<StepEntry> completeData = new ArrayList<>();
		if (data != null) {
			completeData.addAll(data);
		}
		if (deletedData != null) {
			completeData.addAll(deletedData);
		}
		return completeData;
	}

	public void setData(List<StepEntry> data) {
		this.deletedData.clear();
		this.data.clear();
		for(StepEntry step:data){
			if(step.isDeleted()){
				deletedData.add(step);
			}else {
				this.data.add(step);
			}
		}
		notifyDataSetChanged();
	}

	private void showEditStepDialog(final StepEntry stepEntry) {
		LayoutInflater factory = LayoutInflater.from(context);
		final View editDialogView = factory.inflate(R.layout.dialog_step_edit, null);
		final AlertDialog editDialog = new AlertDialog.Builder(context).create();
		editDialog.setView(editDialogView);

		TextInputEditText name = editDialogView.findViewById(R.id.txt_name);
		TextInputEditText duration = editDialogView.findViewById(R.id.txt_duration);
		TextInputEditText description = editDialogView.findViewById(R.id.txt_description);
		TextInputEditText order = editDialogView.findViewById(R.id.txt_order);

		name.setText(stepEntry.getName());
		duration.setText(String.valueOf(stepEntry.getDuration()));
		order.setText(String.valueOf(stepEntry.getOrder()));
		description.setText(stepEntry.getDescription());

		editDialogView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//your business logic
				editDialog.dismiss();
			}
		});
		editDialogView.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextInputEditText name = editDialogView.findViewById(R.id.txt_name);
				TextInputEditText duration = editDialogView.findViewById(R.id.txt_duration);
				TextInputEditText description = editDialogView.findViewById(R.id.txt_unit);
				TextInputEditText order = editDialogView.findViewById(R.id.txt_order);

				if (name.getText().toString().isEmpty() || order.getText().toString().isEmpty()) {
					Toast.makeText(context, "Please Provide a Name and Order, or cancel this dialog.", Toast.LENGTH_LONG).show();
					return;
				}

				stepEntry.setName(name.getText().toString());
				stepEntry.setDuration(Long.valueOf(duration.getText().toString()));
				stepEntry.setDescription(description.getText().toString());
				stepEntry.setOrder(Integer.parseInt(order.getText().toString()));
				notifyDataSetChanged();
				editDialog.dismiss();
			}
		});
		editDialogView.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				data.remove(stepEntry);
				stepEntry.setDeleted(true);
				deletedData.add(stepEntry);
				notifyDataSetChanged();
				editDialog.dismiss();
			}
		});

		editDialog.show();
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
					StepEntry entry = data.get(getAdapterPosition());
					showEditStepDialog(entry);

				}
			});
		}
	}
}
