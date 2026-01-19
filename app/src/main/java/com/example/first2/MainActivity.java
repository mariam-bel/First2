package com.example.first2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<EventModel> historicEvents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.historicEventRecycler);

        fillModels();

        EventAdapter adapter = new EventAdapter(this, historicEvents);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fillModels() {
        String[] eventNames = getResources().getStringArray(R.array.historic_event_names);
        String[] eventDates = getResources().getStringArray(R.array.historic_event_dates);
        String[] eventLocations = getResources().getStringArray(R.array.historic_event_locations);

        for (int i = 0; i < eventNames.length; i++) {
            historicEvents.add(new EventModel(eventNames[i], eventDates[i], eventLocations[i], i));
        }
    }
}
