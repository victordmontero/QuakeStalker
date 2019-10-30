package com.example.quakestalker.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quakestalker.R;
import com.example.quakestalker.models.Feature;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuakeRecyclerViewAdapter extends RecyclerView.Adapter<QuakeRecyclerViewAdapter.QuakeViewHolder> {

    List<Feature> quakeList;

    public QuakeRecyclerViewAdapter() {
        quakeList = new ArrayList<>();
    }

    @NonNull
    @Override
    public QuakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_elem, parent, false);
        QuakeRecyclerViewAdapter.QuakeViewHolder viewHolder =
                new QuakeRecyclerViewAdapter.QuakeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuakeViewHolder holder, int position) {
        Feature quake = quakeList.get(position);
        holder.magnitudeTxt.setText(quake.getProperties().getMag().toString());
        holder.placeTxt.setText(quake.getProperties().getPlace());
        Date date = new Date(quake.getProperties().getTime());
        holder.dateTxt.setText(date.toString());
    }

    @Override
    public int getItemCount() {
        return quakeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setQuakeList(List<Feature> quakeList)
    {
        this.quakeList.addAll(quakeList);
        notifyDataSetChanged();
    }

    public class QuakeViewHolder extends RecyclerView.ViewHolder {
        TextView magnitudeTxt;
        TextView placeTxt;
        TextView dateTxt;

        public QuakeViewHolder(@NonNull View itemView) {
            super(itemView);

            magnitudeTxt = itemView.findViewById(R.id.magnitude);
            placeTxt = itemView.findViewById(R.id.place);
            dateTxt = itemView.findViewById(R.id.date);
        }
    }

}
