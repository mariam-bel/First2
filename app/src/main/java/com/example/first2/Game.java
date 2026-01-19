package com.example.first2;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.List;
import java.util.Random;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(30,30,30,30);

        setContentView(root);

        String name = getIntent().getStringExtra("eventName");
        String dateCorrecta = getIntent().getStringExtra("eventDate");
        String location = getIntent().getStringExtra("eventLocation");
        long seed = getIntent().getLongExtra("seed", 0);

        EventModel event = new EventModel(name, dateCorrecta, location, seed);

        CardView card = new CardView(this);
        card.setCardElevation(12);
        card.setRadius(20);
        card.setCardBackgroundColor(Color.WHITE);

        LinearLayout cardContent = new LinearLayout(this);
        cardContent.setOrientation(LinearLayout.VERTICAL);
        cardContent.setPadding(30,30,30,30);
        card.addView(cardContent);

        TextView tvTitle = new TextView(this);
        tvTitle.setText(name);
        tvTitle.setTextSize(20);
        tvTitle.setTextColor(Color.BLACK);
        cardContent.addView(tvTitle);

        RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(LinearLayout.VERTICAL);
        List<String> fechas = event.getFechasOpciones();

        for (String f : fechas) {
            RadioButton rb = new RadioButton(this);
            rb.setText(f);
            rb.setTextSize(16);
            rb.setTextColor(Color.BLACK);
            rg.addView(rb);
        }

        cardContent.addView(rg);
        root.addView(card);

        rg.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton seleccionada = group.findViewById(checkedId);
            String respuesta = seleccionada.getText().toString();
            event.setRespuestaUsuario(respuesta);

            boolean correcta = event.esCorrecta();

            if (correcta) {
                card.setCardBackgroundColor(Color.parseColor("#C8E6C9"));
            } else {
                card.setCardBackgroundColor(Color.parseColor("#FFCDD2"));
            }

            Toast.makeText(this, correcta ? "Correcto" : "Incorrecto", Toast.LENGTH_SHORT).show();

            for (int i=0; i<rg.getChildCount(); i++){
                rg.getChildAt(i).setEnabled(false);
            }
        });
    }
}
