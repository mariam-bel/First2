package com.example.first2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventModel {
    public String eventName;
    public String eventDate;
    public String eventLocation;

    private List<String> fechasOpciones;

    public EventModel(String eventName, String eventDate, String eventLocation, long seed) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;

        generarFechas(seed);
    }

    private void generarFechas(long seed) {
        fechasOpciones = new ArrayList<>();

        Random r = new Random(seed);
        for (int i = 0; i < 3; i++) {
            int fechas = r.nextInt(2050);
            fechasOpciones.add(""+fechas);
        }
    }

    public List<String> getFechasOpciones() {
        return fechasOpciones;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }
}
