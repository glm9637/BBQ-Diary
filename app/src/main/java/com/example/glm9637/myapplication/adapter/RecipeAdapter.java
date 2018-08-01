package com.example.glm9637.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.glm9637.myapplication.CutActivity;
import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.CutEntryForList;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.utils.Constants;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.CutViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private List<RecipeEntry> data;

    public RecipeAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<RecipeEntry> data){
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
	    RecipeEntry recipe = data.get(position);
        holder.Name.setText(recipe.getName());
        holder.Description.setText(recipe.getDescription());
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

        public CutViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.txt_name);
            Description = itemView.findViewById(R.id.txt_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
	             /*   RecipeEntry item = data.get(getAdapterPosition());
                    Intent intent = new Intent(context, CutActivity.class);
                    intent.putExtra(Constants.Arguments.CUT_ID,item.getId());
                    context.startActivity(intent);*/
                }
            });
        }
    }
}
