package com.example.meal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DailyItemAdapter extends RecyclerView.Adapter<DailyItemAdapter.MyViewHolder> {

    Context context;
    ArrayList<DailyItemModel> itemModelArrayList;
    public DailyItemAdapter(Context context, ArrayList<DailyItemModel> itemModelArrayList) {
        this.context =context;
        this.itemModelArrayList =itemModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_items,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DailyItemModel item = itemModelArrayList.get(position);
        holder.text.setText(item.text);
        holder.heading.setText(item.heading);
       // holder.imageView.setImageResource(item.img_url);
    }

    @Override
    public int getItemCount() {
        return itemModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView heading;
        TextView text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.imageView);
            heading =itemView.findViewById(R.id.headingTextView);
            text =itemView.findViewById(R.id.descriptionTextView);
        }
    }

}
