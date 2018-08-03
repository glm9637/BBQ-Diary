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

    public StepListAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<StepEntry> data){
        this.data = data;
        notifyDataSetChanged();
    }
	
	public void setOnClickListener(ListEntryClickedListener onClickListener) {
		this.onClickListener = onClickListener;
	}
	
	@NonNull
    @Override
    public StepListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.item_step_list,parent,false);

        return new StepListViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull StepListViewHolder holder, int position) {
    	if(position==0){
    	    holder.Name.setText("Ingredients");
    	    holder.Time.setText("");
    	    holder.Description.setText("All the needed Ingredients");
	    }else {
		    StepEntry recipe = data.get(position - 1);
		    holder.Name.setText(recipe.getName());
		    holder.Description.setText(recipe.getDescription());
		    holder.Time.setText(String.valueOf(recipe.getDuration()));
	    }
		if(holder.Time.getText().toString().isEmpty()){
    		holder.Time.setVisibility(View.INVISIBLE);
		}else {
    		holder.Time.setVisibility(View.VISIBLE);
		}
	    holder.Number.setText(String.valueOf(position + 1) + ".");
    }

    @Override
    public int getItemCount() {
    	if(data==null){
    		return 0;
	    }
        return data.size()+1;
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
	                onClickListener.onListEntryClicked(getAdapterPosition()+1);
                 
                }
            });
        }
    }
}
