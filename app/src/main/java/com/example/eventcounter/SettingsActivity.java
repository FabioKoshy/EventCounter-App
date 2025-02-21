package com.example.eventcounter;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    private EditText editCounter1, editCounter2, editCounter3, editMaxCount;
    private Button btnSaveSettings;
    private SharedPreferences sharedPreferences;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Prevent duplicate "Settings"
        }

        btnSaveSettings = findViewById(R.id.btnSaveSettings);
        TextView btnMenu;
        btnMenu = findViewById(R.id.btnMenu);
        TextView btnBack = findViewById(R.id.btnBack);
        editCounter1 = findViewById(R.id.editCounter1);
        editCounter2 = findViewById(R.id.editCounter2);
        editCounter3 = findViewById(R.id.editCounter3);
        editMaxCount = findViewById(R.id.editMaxCount);

        sharedPreferences = getSharedPreferences("EventPrefs", MODE_PRIVATE);
        loadSettings();
        disableEditing();

        btnBack.setOnClickListener(v -> finish());

        btnSaveSettings.setOnClickListener(view -> {
            if (validateInputs()) {
                saveSettings();
                disableEditing();
            }
        });

        btnMenu.setOnClickListener(this::showPopupMenu);
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenu().add("Edit").setOnMenuItemClickListener(item -> {
            enableEditing();
            return true;
        });
        popupMenu.show();
    }

    private void enableEditing() {
        isEditMode = true;
        editCounter1.setEnabled(true);
        editCounter2.setEnabled(true);
        editCounter3.setEnabled(true);
        editMaxCount.setEnabled(true);
        btnSaveSettings.setVisibility(View.VISIBLE);
    }

    private void disableEditing() {
        isEditMode = false;
        editCounter1.setEnabled(false);
        editCounter2.setEnabled(false);
        editCounter3.setEnabled(false);
        editMaxCount.setEnabled(false);
        btnSaveSettings.setVisibility(View.GONE);
    }

    private void loadSettings() {
        editCounter1.setText(sharedPreferences.getString("event1", "Counter 1"));
        editCounter2.setText(sharedPreferences.getString("event2", "Counter 2"));
        editCounter3.setText(sharedPreferences.getString("event3", "Counter 3"));
        editMaxCount.setText(String.valueOf(sharedPreferences.getInt("maxCount", 10)));
    }


    private boolean validateInputs() {
        String name1 = editCounter1.getText().toString().trim();
        String name2 = editCounter2.getText().toString().trim();
        String name3 = editCounter3.getText().toString().trim();
        String maxCountStr = editMaxCount.getText().toString().trim();

        if (name1.isEmpty() || name2.isEmpty() || name3.isEmpty()) {
            showToast("Event names cannot be empty");
            return false;
        }

        if (!maxCountStr.matches("\\d+") || Integer.parseInt(maxCountStr) < 5 || Integer.parseInt(maxCountStr) > 200) {
            showToast("Max count must be a number between 5 and 200");
            return false;
        }
        return true;
    }

    private void saveSettings() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("event1", editCounter1.getText().toString().trim());
        editor.putString("event2", editCounter2.getText().toString().trim());
        editor.putString("event3", editCounter3.getText().toString().trim());
        editor.putInt("maxCount", Integer.parseInt(editMaxCount.getText().toString().trim()));
        editor.apply();

        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(this);
        sharedPreferenceHelper.setFirstLaunchFalse();

        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
