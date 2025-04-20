package com.example.lutemonbattle.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lutemonbattle.Lutemons.Lutemon;
import com.example.lutemonbattle.Lutemons.Storage;
import com.example.lutemonbattle.Lutemons.StorageSerializer;
import com.example.lutemonbattle.R;
import com.example.lutemonbattle.adapters.LutemonAdapter;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LutemonAdapter adapter;
    private Button btnTraining, btnBattle, btnSave, btnLoad;
    private Storage storage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Get storage instance
        storage = Storage.getInstance();

        // Initialize
        initViews();

        // Set listeners for buttons
        setupButtonListeners();

        // Initialize RecyclerView and Adapters
        setupRecyclerView();

        // Return button
        ImageView ivBack = findViewById(R.id.imageView4);
        ivBack.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }

    private void initViews() {
        btnTraining = findViewById(R.id.button);            // Move to Training area
        btnBattle = findViewById(R.id.button6);             // Move to Battle Arena
        btnSave = findViewById(R.id.buttonSave);            // Save to File
        btnLoad = findViewById(R.id.buttonLoad);            // Load from File
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Listeners for activities
    private void setupButtonListeners() {
        // Training when selected 1 lutemon
        btnTraining.setOnClickListener(v -> {
            List<Lutemon> selected = adapter.getSelectedLutemons();
            if (selected.size() != 1) {
                Toast.makeText(this, "Select 1 Lutemon for training", Toast.LENGTH_SHORT).show();
            } else {
                moveToTraining(selected.get(0));
            }
        });

        // Battle when selected 2 lutemons
        btnBattle.setOnClickListener(v -> {
            List<Lutemon> selected = adapter.getSelectedLutemons();
            if (selected.size() != 2) {
                Toast.makeText(this, "Select 2 Lutemons for battle", Toast.LENGTH_SHORT).show();
            } else {
                moveToBattle(selected.get(0), selected.get(1));
            }
        });

        // Store lutemon file
        btnSave.setOnClickListener(v -> {
            if (StorageSerializer.saveStorage(HomeActivity.this)) {
                Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Save failed", Toast.LENGTH_SHORT).show();
            }
        });

        // Load lutemon file
        btnLoad.setOnClickListener(v -> {
            if (StorageSerializer.loadStorage(HomeActivity.this)) {
                Toast.makeText(this, "Data loaded", Toast.LENGTH_SHORT).show();
                updateUI();
            } else {
                Toast.makeText(this, "Load failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView() {
        List<Lutemon> lutemons = new ArrayList<>(storage.getLutemons().values());
        adapter = new LutemonAdapter(lutemons);
        recyclerView.setAdapter(adapter);
    }

    // Update RecyclerView
    private void updateUI() {
        List<Lutemon> lutemons = new ArrayList<>(Storage.getInstance().getLutemons().values());
        if (adapter != null) {
            adapter.updateData(lutemons);
        }
    }

    // Jump to training page
    private void moveToTraining(Lutemon lutemon) {
        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra("LUTEMON_ID", lutemon.getId());
        startActivity(intent);
    }

    // Jump to battle page
    private void moveToBattle(Lutemon lutemon1, Lutemon lutemon2) {
        Intent intent = new Intent(HomeActivity.this, BattleActivity.class);
        intent.putExtra("LUTEMON_A_ID", lutemon1.getId());
        intent.putExtra("LUTEMON_B_ID", lutemon2.getId());
        startActivity(intent);
    }

    protected void onResume() {
        super.onResume();
        updateUI();
        healAllLutemons();  // Cover hp to full
        refreshLutemonList();

    }

    private void healAllLutemons() {
        Storage storage = Storage.getInstance();
        for (Lutemon lutemon : storage.getLutemons().values()) {
            lutemon.restoreHp();
        }
    }

    private void refreshLutemonList() {
        Storage storage = Storage.getInstance();
        List<Lutemon> lutemonList = new ArrayList<>(storage.getLutemons().values());

    }

    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}
