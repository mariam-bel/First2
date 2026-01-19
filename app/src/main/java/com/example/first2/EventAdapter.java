package com.example.first2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/*
    EL ADAPTADOR NO ANIM, NO ESCALA, NO ELEVA NO HACE NADA DE ESO,
    SOLO SE ENCARGA DE 'GUARDAR' EL ESTADO DE LA TARJETA QUE SELECCIONAMOS
    Y AVISA AL RECYCLERVIEW.
    EL RECYCLERVIEW VUELVE A 'BINDEAR' EL VIEWHOLDER Y LLAMA A ITEMANIMATOR
*/
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyHolder> implements View.OnClickListener {

    Context context;
    static ArrayList<EventModel> events;

    private int tarjetaSeleccionada = RecyclerView.NO_POSITION;

    public static final String PAYLOAD_SELECTED = "PAYLOAD_SELECTED";


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
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        EventModel event = events.get(position);
        holder.card.setBackgroundColor(Color.WHITE);

        holder.tvTitle.setText(event.getEventName());
        holder.tvDate.setText(event.getEventDate());
        holder.tvLocation.setText(event.getEventLocation());

        boolean seleccionada = (tarjetaSeleccionada == position);
        holder.extraContent.setVisibility(seleccionada ? View.VISIBLE : View.GONE);

        if (seleccionada) {
            holder.extraContent.removeAllViews();

            TextView titulo = new TextView(context);
            titulo.setText("Fechas disponibles");
            titulo.setTextSize(16);
            holder.extraContent.addView(titulo);

            RadioGroup rg = new RadioGroup(context);

            for (String fecha : event.getFechasOpciones()) {
                RadioButton rb = new RadioButton(context);
                rb.setText(fecha);
                rg.addView(rb);
            }

            holder.extraContent.addView(rg);
        }

        holder.card.setOnClickListener(v -> {
            int anterior = tarjetaSeleccionada;
            tarjetaSeleccionada = seleccionada ? RecyclerView.NO_POSITION : position;

            if (anterior != RecyclerView.NO_POSITION) {
                notifyItemChanged(anterior);
            }
            notifyItemChanged(position);
        });
    }


    @Override
    public int getItemCount() {
        return events.size();
    }

    @Override
    public void onClick(View v) {

    }

    static class MyHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView tvTitle, tvDate, tvLocation;
        LinearLayout extraContent;

        MyHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardView);
            tvTitle = itemView.findViewById(R.id.titleEvent);
            tvDate = itemView.findViewById(R.id.dateEvent);
            tvLocation = itemView.findViewById(R.id.locationEvent);
            extraContent = itemView.findViewById(R.id.extraContent);
        }
    }



}
