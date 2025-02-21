package com.example.eventcounter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import java.util.Objects;
import java.util.Set;
import android.widget.Button;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    private TextView txtSummary;
    private ListView listViewEvents;
    private SharedPreferenceHelper sharedPreferenceHelper;
    private boolean showEventNames = true;
    private Button btnDone;
    private boolean isToggled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        txtSummary = findViewById(R.id.txtSummary);
        listViewEvents = findViewById(R.id.listViewEvents);
        TextView btnBack;
        btnBack = findViewById(R.id.btnBack);
        TextView btnMenu = findViewById(R.id.btnMenu);
        Button btnReset = findViewById(R.id.btnReset);
        btnDone = findViewById(R.id.btnDone);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        btnBack.setOnClickListener(v -> finish());

        btnMenu.setOnClickListener(this::showPopupMenu);

        sharedPreferenceHelper = new SharedPreferenceHelper(this);
        loadEventData();

        btnReset.setOnClickListener(v -> resetEventData());
        btnDone.setOnClickListener(v -> resetToggleView());
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenu().add("Toggle Event Names").setOnMenuItemClickListener(item -> {
            toggleEventNames();
            return true;
        });
        popupMenu.show();
    }

    private void toggleEventNames() {
        if (!isToggled) {
            showEventNames = false;
            btnDone.setVisibility(View.VISIBLE);
            isToggled = true;
        } else {
            showEventNames = true;
            btnDone.setVisibility(View.GONE);
            isToggled = false;
        }
        loadEventData();
    }

    @SuppressLint("SetTextI18n")
    private void loadEventData() {
        int totalEvents = sharedPreferenceHelper.getTotalCount();
        int countA = sharedPreferenceHelper.getEventData("eventA");
        int countB = sharedPreferenceHelper.getEventData("eventB");
        int countC = sharedPreferenceHelper.getEventData("eventC");

        String eventName1 = sharedPreferenceHelper.getEventName(1); // Updated name for Counter 1
        String eventName2 = sharedPreferenceHelper.getEventName(2); // Updated name for Counter 2
        String eventName3 = sharedPreferenceHelper.getEventName(3); // Updated name for Counter 3

        Set<String> previousNames1 = sharedPreferenceHelper.getPreviousEventNames(1);
        Set<String> previousNames2 = sharedPreferenceHelper.getPreviousEventNames(2);
        Set<String> previousNames3 = sharedPreferenceHelper.getPreviousEventNames(3);

        String labelA = showEventNames ? eventName1 : "Counter 1";
        String labelB = showEventNames ? eventName2 : "Counter 2";
        String labelC = showEventNames ? eventName3 : "Counter 3";

        txtSummary.setText(labelA + ": " + countA + "\n" + labelB + ": " + countB + "\n" + labelC + ": " + countC + "\n" + "Total Count: " + totalEvents);


        String history = sharedPreferenceHelper.getEventDataString("eventOrder");
        List<String> eventHistory = history.isEmpty() ? new ArrayList<>() : new ArrayList<>(Arrays.asList(history.split(",")));

        List<String> displayList = new ArrayList<>();
        for (String event : eventHistory) {
            if (showEventNames) {
                displayList.add(event);
            } else {
                if (event.equals(eventName1) || previousNames1.contains(event)) {
                    displayList.add("Counter 1");
                } else if (event.equals(eventName2) || previousNames2.contains(event)) {
                    displayList.add("Counter 2");
                } else if (event.equals(eventName3) || previousNames3.contains(event)) {
                    displayList.add("Counter 3");
                } else {
                    displayList.add("Unknown Counter");
                }
            }
        }

        listViewEvents.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList));
    }

    private void resetEventData() {
        sharedPreferenceHelper.saveTotalCount(0);
        sharedPreferenceHelper.saveEventData("eventA", 0);
        sharedPreferenceHelper.saveEventData("eventB", 0);
        sharedPreferenceHelper.saveEventData("eventC", 0);
        sharedPreferenceHelper.saveEventDataString("eventOrder", "");

        loadEventData();
    }

    private void resetToggleView() {
        showEventNames = true;
        isToggled = false;
        btnDone.setVisibility(View.GONE);
        loadEventData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadEventData();
    }
}
