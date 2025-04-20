package com.example.lutemonbattle.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.lutemonbattle.Lutemons.Lutemon;
import com.example.lutemonbattle.Lutemons.Storage;
import com.example.lutemonbattle.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView totallutemons;
    private static final Class<?>[] ACTIVITY_CLASSES = {
            HomeActivity.class,
            TrainingActivity.class,
            CreateLutemonActivity.class,
            StatisticsActivity.class
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        initUIComponents();
        updateLutemonCount();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }
    protected void onResume() {
        super.onResume();
        updateLutemonCount(); // Refresh the Lutemon count when the activity is resumed
    }

    // Extract the method to update the Lutemon count
    private void updateLutemonCount() {
        Map<Integer, Lutemon> lutemonMap = Storage.getInstance().getLutemons();
        List<Lutemon> finalList = new ArrayList<>(lutemonMap.values());
        if (totallutemons != null) {
            totallutemons.setText("You have " + finalList.size() + " Lutemons at home");
        }
    }
    // Initialize different UI components
    private void initUIComponents() {
        try {
            // Home and Training continue to use bindClick
            bindClick(R.id.imageView8, 0);    // HomeActivity
            bindClick(R.id.imageView9, 1);    // TrainingActivity

            View battleView = findViewById(R.id.imageView10);
            if(battleView != null){
                battleView.setOnClickListener(v -> safeStartActivity(BattleActivity.class));
            } else {
                logMissingView(R.id.imageView10);
            }

            bindClick(R.id.button17, 2);   // CreateLutemonActivity
            bindClick(R.id.button_sss, 3);   // StatisticsActivity

        } catch (Exception e) {
            showErrorToast("UI initialization failed");
        }
        final Map<Integer, Lutemon> lutemonMap = Storage.getInstance().getLutemons();
        final List<Lutemon> finalList = new ArrayList<>(lutemonMap.values());
        totallutemons = findViewById(R.id.textView2);
        totallutemons.setText("You have " + finalList.size() + " Lutemons at home");
    }


    // Use viewId to jump Activity
    private void bindClick(int viewId, int activityIndex) {
        View view = findViewById(viewId);
        if (view != null) {
            view.setOnClickListener(v -> safeStartActivity(ACTIVITY_CLASSES[activityIndex]));
        } else {
            logMissingView(viewId);
        }
    }

    // Safe start Activity
    private void safeStartActivity(Class<?> cls) {
        try {
            startActivity(new Intent(this, cls));
            overridePendingTransition(0, 0);
        } catch (Exception e) {
            showErrorToast("Functionality not available");
            e.printStackTrace();
        }
    }

    // Show wrong prompt
    private void showErrorToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void logMissingView(int viewId) {
        String viewName;
        try {
            viewName = getResources().getResourceEntryName(viewId);
        } catch (Exception e) {
            viewName = "Unknown";
        }
        System.err.println("Warning: Could not find view - " + viewName);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
