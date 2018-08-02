package com.example.glm9637.myapplication.ui.adapter.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.glm9637.myapplication.ui.activity.CutActivity;
import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.model.CutEntryForList;
import com.example.glm9637.myapplication.utils.Constants;

import java.util.List;

public class CutAdapter extends RecyclerView.Adapter<CutAdapter.CutViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private List<CutEntryForList> data;

    public CutAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<CutEntryForList> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.item_cut,parent,false);

        return new CutViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull CutViewHolder holder, int position) {
	    CutEntryForList cut = data.get(position);
        holder.Name.setText(cut.getName());
        holder.Description.setText(cut.getDescription());
        holder.Origin.setText(cut.getOrigin());
        holder.RecipeCount.setText(String.valueOf(cut.getRecipeCount()));
    }

    @Override
    public int getItemCount() {
    	if(data==null){
    		return 0;
	    }
        return data.size();
    }

    class CutViewHolder extends RecyclerView.ViewHolder{

        TextView Name;
        TextView Description;
        TextView Origin;
        TextView RecipeCount;

        public CutViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.txt_name);
            Description = itemView.findViewById(R.id.txt_description);
            Origin = itemView.findViewById(R.id.txt_origin);
            RecipeCount = itemView.findViewById(R.id.txt_recipe_count);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
	                CutEntryForList item = data.get(getAdapterPosition());
                    Intent intent = new Intent(context, CutActivity.class);
                    intent.putExtra(Constants.Arguments.CUT_ID,item.getId()) ;
                    context.startActivity(intent);
                }
            });
        }
    }
}
