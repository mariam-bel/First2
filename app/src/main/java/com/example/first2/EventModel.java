package com.example.first2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EventModel {

    private String eventName;
    private String eventDate;
    private String eventLocation;

    private List<String> fechasOpciones;
    String respuestaUsuario = null;

    public EventModel(String eventName, String eventDate, String eventLocation, long seed) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        generarFechas(seed);
    }

    private void generarFechas(long seed) {
        fechasOpciones = new ArrayList<>();
        Random r = new Random(seed);

        fechasOpciones.add(eventDate);

        while (fechasOpciones.size() < 3) {
            int fecha = 1000 + r.nextInt(2024 - 1000);
            String f = String.valueOf(fecha);
            if (!fechasOpciones.contains(f)) {
                fechasOpciones.add(f);
            }
        }

        Collections.shuffle(fechasOpciones, r);
    }

    public void setRespuestaUsuario(String respuesta) {
        this.respuestaUsuario = respuesta;
    }

    public boolean esCorrecta() {
        return respuestaUsuario != null && respuestaUsuario.equals(eventDate);
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
