package com.example.quakestalker.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quakestalker.databinding.RecyclerviewElemBinding;
import com.example.quakestalker.models.Feature;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuakeRecyclerViewAdapter extends ListAdapter<Feature, QuakeRecyclerViewAdapter.QuakeViewHolder> {
    OnItemClickListener listener;

    public QuakeRecyclerViewAdapter() {
        super(new FeatureDiffCallback());
    }

    @NonNull
    @Override
    public QuakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return (QuakeViewHolder) from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull QuakeViewHolder holder, int position) {
        Feature quake = getItem(position);
        holder.bind(quake);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class QuakeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerviewElemBinding binding;

        OnItemClickListener listener;

        private QuakeViewHolder(@NonNull RecyclerviewElemBinding binding, OnItemClickListener listener) {
            super(binding.getRoot());

            this.binding = binding;
            this.listener = listener;

            if (listener != null)
                itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            listener.onItemClick(pos, getItem(pos));
        }

        public void bind(Feature quake) {
            Double mag = (double)Math.round(quake.getProperties().getMag() * 100d) / 100d;
            binding.magnitude.setText(mag.toString());
            binding.place.setText(quake.getProperties().getPlace());

            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a dd/MM/yy");
            binding.date.setText(dateFormat.format(new Date(quake.getProperties().getTime())));
            binding.executePendingBindings();
        }
    }

    public QuakeViewHolder from(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerviewElemBinding binding =
                RecyclerviewElemBinding.inflate(layoutInflater, parent, false);
        return new QuakeViewHolder(binding, listener);
    }

    public interface OnItemClickListener {
        void onItemClick(int position, Feature feature);
    }
}

class FeatureDiffCallback extends DiffUtil.ItemCallback<Feature> {
    FeatureDiffCallback() {

    }

    @Override
    public boolean areItemsTheSame(@NonNull Feature oldItem, @NonNull Feature newItem) {
        return oldItem.getID() == newItem.getID();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Feature oldItem, @NonNull Feature newItem) {
        return true;
    }
}
