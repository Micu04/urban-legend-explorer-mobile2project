package com.example.urbanlegendexplorer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.urbanlegendexplorer.R;
import com.example.urbanlegendexplorer.models.Legend;

import java.util.List;

public class LegendAdapter extends RecyclerView.Adapter<LegendAdapter.LegendViewHolder> {

    public interface OnLegendClickListener {
        void onLegendClick(Legend legend);
    }

    private List<Legend> legendList;
    private final OnLegendClickListener listener;

    public LegendAdapter(List<Legend> legendList, OnLegendClickListener listener) {
        this.legendList = legendList;
        this.listener = listener;
    }

    public void updateList(List<Legend> newList) {
        this.legendList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LegendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_legend, parent, false);
        return new LegendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LegendViewHolder holder, int position) {
        Legend legend = legendList.get(position);

        holder.textLegendTitle.setText(legend.getTitle());
        holder.textCategory.setText(legend.getCategory());
        holder.textLocation.setText(legend.getLocation());

        Glide.with(holder.itemView.getContext())
                .load(legend.getImageUrl())
                .placeholder(android.R.color.darker_gray)
                .into(holder.imageLegend);

        holder.itemView.setOnClickListener(v -> listener.onLegendClick(legend));
    }

    @Override
    public int getItemCount() {
        return legendList == null ? 0 : legendList.size();
    }

    static class LegendViewHolder extends RecyclerView.ViewHolder {
        ImageView imageLegend;
        TextView textCategory, textLegendTitle, textLocation;

        public LegendViewHolder(@NonNull View itemView) {
            super(itemView);
            imageLegend = itemView.findViewById(R.id.imageLegend);
            textCategory = itemView.findViewById(R.id.textCategory);
            textLegendTitle = itemView.findViewById(R.id.textLegendTitle);
            textLocation = itemView.findViewById(R.id.textLocation);
        }
    }
}