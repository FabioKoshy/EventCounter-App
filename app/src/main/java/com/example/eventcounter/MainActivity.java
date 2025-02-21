package com.example.eventcounter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Button btnEventA;
    private Button btnEventB;
    private Button btnEventC;
    private TextView txtTotalCount;
    private SharedPreferenceHelper sharedPreferenceHelper;
    private int totalCount = 0;
    private int maxCount = 10; // Default max count

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferenceHelper = new SharedPreferenceHelper(this);

        if (sharedPreferenceHelper.isFirstLaunch()) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Button btnSettings = findViewById(R.id.button);
        btnEventA = findViewById(R.id.button2);
        btnEventB = findViewById(R.id.button3);
        btnEventC = findViewById(R.id.button4);
        Button btnShowCount = findViewById(R.id.button5);
        txtTotalCount = findViewById(R.id.textView);

        updateUI();

        btnEventA.setOnClickListener(view -> incrementCount("eventA", 1));
        btnEventB.setOnClickListener(view -> incrementCount("eventB", 2));
        btnEventC.setOnClickListener(view -> incrementCount("eventC", 3));

        btnSettings.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        btnShowCount.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DataActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    @SuppressLint("SetTextI18n")
    private void updateUI() {
        maxCount = sharedPreferenceHelper.getEventData("maxCount");
        totalCount = sharedPreferenceHelper.getTotalCount();
        txtTotalCount.setText("Total Count: " + totalCount);

        btnEventA.setText(sharedPreferenceHelper.getEventName(1));
        btnEventB.setText(sharedPreferenceHelper.getEventName(2));
        btnEventC.setText(sharedPreferenceHelper.getEventName(3));
    }

    private void incrementCount(String eventKey, int eventId) {
        if (totalCount >= maxCount) {
            showToast();
            return;
        }

        totalCount++;
        sharedPreferenceHelper.saveTotalCount(totalCount);

        int eventCount = sharedPreferenceHelper.getEventData(eventKey) + 1;
        sharedPreferenceHelper.saveEventData(eventKey, eventCount);

        String eventName = sharedPreferenceHelper.getEventName(eventId);
        sharedPreferenceHelper.appendEventHistory(eventName);

        txtTotalCount.setText("Total Count: " + totalCount);
    }

    private void showToast() {
        Toast toast = Toast.makeText(this, "Maximum count reached.", Toast.LENGTH_SHORT);
        toast.setGravity(android.view.Gravity.TOP, 0, 200);
        toast.show();
    }
}
