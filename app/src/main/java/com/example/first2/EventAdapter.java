package com.example.first2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyHolder> {

    Context context;
    ArrayList<EventModel> events;

    public EventAdapter(Context context, ArrayList<EventModel> events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public EventAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cv_row, parent,false);
        return new EventAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.MyHolder holder, int position) {
        holder.tvTitle.setText(events.get(position).getEventName());
        holder.tvDate.setText(events.get(position).getEventDate());
        holder.tvLocation.setText(events.get(position).getEventLocation());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvLocation;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.titleEvent);
            tvDate = itemView.findViewById(R.id.dateEvent);
            tvLocation = itemView.findViewById(R.id.locationEvent);

        }
    }
}
