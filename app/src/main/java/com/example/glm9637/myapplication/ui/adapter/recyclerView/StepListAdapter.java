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

import java.util.List;

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepListViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private List<StepEntry> data;
    private ListEntryClickedListener onClickListener;
    
    
    public interface ListEntryClickedListener {
    	void onListEntryClicked(int position);
    }

    public StepListAdapter(Context context, ListEntryClickedListener onClickListener){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    public void setData(List<StepEntry> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StepListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.item_step_list,parent,false);

        return new StepListViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull StepListViewHolder holder, int position) {
	    StepEntry recipe = data.get(position);
        holder.Name.setText(recipe.getName());
        holder.Description.setText(recipe.getDescription());
        holder.Time.setText(String.valueOf(recipe.getDuration()));
        holder.Number.setText(String.valueOf(position)+".");
    }

    @Override
    public int getItemCount() {
    	if(data==null){
    		return 0;
	    }
        return data.size();
    }

    class StepListViewHolder extends RecyclerView.ViewHolder{

        TextView Name;
        TextView Description;
		TextView Time;
		TextView Number;
        
        public StepListViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.txt_name);
            Description = itemView.findViewById(R.id.txt_description);
            Time = itemView.findViewById(R.id.txt_duration);
            Number = itemView.findViewById(R.id.txt_step_number);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
	                onClickListener.onListEntryClicked(getAdapterPosition());
                 
                }
            });
        }
    }
}
