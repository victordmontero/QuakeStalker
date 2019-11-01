package com.example.quakestalker.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    OnItemClickListener listener;

    public QuakeRecyclerViewAdapter() {
        quakeList = new ArrayList<>();
    }

    public QuakeRecyclerViewAdapter(List<Feature> features) {
        this(features, null);
    }

    public QuakeRecyclerViewAdapter(OnItemClickListener listener) {
        this(null, listener);
    }

    public QuakeRecyclerViewAdapter(List<Feature> features, OnItemClickListener listener) {
        quakeList = features;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_elem, parent, false);
        QuakeRecyclerViewAdapter.QuakeViewHolder viewHolder =
                new QuakeRecyclerViewAdapter.QuakeViewHolder(view, listener);
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

    public void setQuakeList(List<Feature> quakeList) {
        this.quakeList.addAll(quakeList);
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class QuakeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView magnitudeTxt;
        TextView placeTxt;
        TextView dateTxt;

        OnItemClickListener listener;

        public QuakeViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            magnitudeTxt = itemView.findViewById(R.id.magnitude);
            placeTxt = itemView.findViewById(R.id.place);
            dateTxt = itemView.findViewById(R.id.date);
            this.listener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            listener.onItemClick(pos, quakeList.get(pos));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, Feature feature);
    }

}
