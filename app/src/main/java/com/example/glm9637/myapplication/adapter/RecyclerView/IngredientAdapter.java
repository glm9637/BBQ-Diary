package com.example.glm9637.myapplication.adapter.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private List<IngredientEntry> data;

    public IngredientAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<IngredientEntry> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.item_cut,parent,false);

        return new IngredientViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        IngredientEntry ingredient = data.get(position);
        holder.Name.setText(ingredient.getName());
        holder.Amount.setText(ingredient.getAmount()+"");
        holder.Measure.setText(ingredient.getMeasure()+"");
    }

    @Override
    public int getItemCount() {
    	if(data==null){
    		return 0;
	    }
        return data.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder{

        TextView Name;
        TextView Amount;
        TextView Measure;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.txt_name);
            Amount = itemView.findViewById(R.id.txt_description);
            Measure = itemView.findViewById(R.id.txt_origin);
        }
    }
}
