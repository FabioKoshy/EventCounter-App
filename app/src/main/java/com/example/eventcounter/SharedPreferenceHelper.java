package com.example.eventcounter;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;

public class SharedPreferenceHelper {
    private final SharedPreferences sharedPreferences;


    public SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("EventPrefs", Context.MODE_PRIVATE);
    }

    public boolean isFirstLaunch() {
        return sharedPreferences.getBoolean("firstLaunch", true);
    }

    public void setFirstLaunchFalse() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firstLaunch", false);
        editor.apply();
    }

    public void saveEventData(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getEventData(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public void saveTotalCount(int totalCount) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("totalCount", totalCount);
        editor.apply();
    }

    public int getTotalCount() {
        return sharedPreferences.getInt("totalCount", 0);
    }

    public void appendEventHistory(String eventName) {
        String existingHistory = sharedPreferences.getString("eventOrder", "");
        if (!existingHistory.isEmpty()) {
            existingHistory += "," + eventName;
        } else {
            existingHistory = eventName;
        }
        saveEventDataString("eventOrder", existingHistory);
    }

    public void saveEventDataString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getEventDataString(String key) {
        return sharedPreferences.getString(key, "");
    }


    public String getEventName(int eventNum) {
        return sharedPreferences.getString("event" + eventNum, "Event " + eventNum);
    }

    public Set<String> getPreviousEventNames(int eventNum) {
        return sharedPreferences.getStringSet("eventHistory" + eventNum, new HashSet<>());
    }
}
