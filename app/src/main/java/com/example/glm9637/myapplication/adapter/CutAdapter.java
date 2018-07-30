package com.example.glm9637.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.CutEntry;
import com.example.glm9637.myapplication.utils.Constants;

import java.util.List;

public class CutAdapter extends RecyclerView.Adapter<CutAdapter.CutViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private List<CutEntry> data;

    public CutAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<CutEntry> data){
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
        CutEntry cut = data.get(position);
        holder.Name.setText(cut.getName());
        holder.Description.setText(cut.getDescription());
    }

    @Override
    public int getItemCount() {
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
                    CutEntry item = data.get(getAdapterPosition());
                    //ToDo: add class
                    Intent intent = new Intent(context,null);
                    intent.putExtra(Constants.Arguments.CUT_ID,item.getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
