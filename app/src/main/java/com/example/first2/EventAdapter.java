package com.example.first2;

import static android.view.View.TEXT_ALIGNMENT_CENTER;
import static android.view.View.TEXT_ALIGNMENT_TEXT_END;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
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
    public void onBindViewHolder(@NonNull EventAdapter.MyHolder holder, int posicion) {
        holder.tvTitle.setText(events.get(posicion).getEventName());
        holder.tvDate.setText(events.get(posicion).getEventDate());
        holder.tvLocation.setText(events.get(posicion).getEventLocation());


        ViewGroup.LayoutParams params = holder.card.getLayoutParams();
        boolean esSeleccionada = (tarjetaSeleccionada == posicion);
        params.height = (esSeleccionada ? 800 : 200);
        holder.card.setLayoutParams(params);

        if (esSeleccionada) {
            holder.contenidoAdd.removeAllViews();
            TextView textView = new TextView(holder.itemView.getContext());
            textView.setText(events.get(posicion).getEventName());
            textView.setTextSize(20);
            textView.setTextColor(Color.BLACK);
            textView.setPadding(100,100,16,16);

            holder.contenidoAdd.addView(textView);
        }

        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int ultima = tarjetaSeleccionada;
                tarjetaSeleccionada = esSeleccionada ? -1 : posicion;

                notifyItemChanged(ultima);
                notifyItemChanged(posicion);

                //Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.agrandar);
                //v.startAnimation(animation);
                //ActivityOptions options  = ActivityOptions.makeSceneTransitionAnimation((Activity) context);



//                Intent intent = new Intent(v.getContext(), Game.class);
//                context.startActivity(intent, options.toBundle());

            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    @Override
    public void onClick(View v) {

    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        CardView card;

        LinearLayout contenidoAdd;

        TextView tvTitle, tvDate, tvLocation;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.titleEvent);
            tvDate = itemView.findViewById(R.id.dateEvent);
            tvLocation = itemView.findViewById(R.id.locationEvent);
            card = itemView.findViewById(R.id.cardView);
            contenidoAdd = itemView.findViewById(R.id.cardContent);

        }
    }

}
