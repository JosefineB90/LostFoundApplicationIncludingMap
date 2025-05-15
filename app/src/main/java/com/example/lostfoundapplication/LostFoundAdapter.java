package com.example.lostfoundapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LostFoundAdapter extends RecyclerView.Adapter<LostFoundAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(LostFoundItem item);
    }

    private List<LostFoundItem> items;
    private OnItemClickListener listener;

    public LostFoundAdapter(List<LostFoundItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textType, textDescription, textDate, textLocation;

        public ViewHolder(View view) {
            super(view);
            textType = view.findViewById(R.id.textType);
            textDescription = view.findViewById(R.id.textDescription);
            textDate = view.findViewById(R.id.textDate);
            textLocation = view.findViewById(R.id.textLocation);
        }

        public void bind(LostFoundItem item, OnItemClickListener listener) {
            textType.setText("Type: " + item.getType());
            textDescription.setText("Description: " + item.getDescription());
            textDate.setText("Date: " + item.getDate());
            textLocation.setText("Location: " + item.getLocation());

            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }

    @NonNull
    @Override
    public LostFoundAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lost_found, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LostFoundAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
