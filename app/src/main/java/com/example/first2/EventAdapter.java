package com.example.first2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyHolder> {

    private final Context context;
    private final ArrayList<EventModel> events;

    public EventAdapter(Context context, ArrayList<EventModel> events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cv_row, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        EventModel event = events.get(position);

        holder.tvTitle.setText(event.getEventName());
        holder.tvDate.setText(event.getEventDate());
        holder.tvLocation.setText(event.getEventLocation());

        holder.card.setOnClickListener(v -> {
            Intent intent = new Intent(context, Game.class);
            intent.putExtra("eventName", event.getEventName());
            intent.putExtra("eventDate", event.getEventDate());
            intent.putExtra("eventLocation", event.getEventLocation());
            intent.putExtra("seed", position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void eliminarTarjeta(int position) {
        if (position!=RecyclerView.NO_POSITION){
            events.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,events.size());
        }
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        CardView card;
        androidx.appcompat.widget.AppCompatTextView tvTitle, tvDate, tvLocation;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardView);
            tvTitle = itemView.findViewById(R.id.titleEvent);
            tvDate = itemView.findViewById(R.id.dateEvent);
            tvLocation = itemView.findViewById(R.id.locationEvent);
        }
    }
}
