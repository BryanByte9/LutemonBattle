package com.example.lutemonbattle.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lutemonbattle.Lutemons.Lutemon;
import com.example.lutemonbattle.Lutemons.Storage;
import com.example.lutemonbattle.R;
import com.example.lutemonbattle.adapters.LutemonStatsAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StatisticsActivity extends AppCompatActivity {

    private TextView tvTotalLutemons, tvTotalBattles, tvTotalTrainings;
    private RecyclerView rvLutemonStats;
    private Storage storage;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        // Initialize layout
        initViews();

        // Load stored data
        storage = Storage.getInstance();

        // Set statistic data
        setupStatistics();

        // Set RecyclerView
        setupRecyclerView();

        // Return button
        setupBackButton();
    }

    private void initViews() {
        tvTotalLutemons = findViewById(R.id.tv_total_lutemons);
        tvTotalBattles = findViewById(R.id.tv_total_battles);
        tvTotalTrainings = findViewById(R.id.tv_total_trainings);
        rvLutemonStats = findViewById(R.id.rv_lutemon_stats);
    }

    private void setupStatistics() {
        final Map<Integer, Lutemon> lutemonMap = Storage.getInstance().getLutemons();
        final List<Lutemon> finalList = new ArrayList<>(lutemonMap.values());

        // Compute total battles and trainings
        final int[] totalBattles = {0};
        final int[] totalTrainings = {0};

        for (Lutemon lutemon : finalList) {
            totalBattles[0] += lutemon.getBattles();
            totalTrainings[0] += lutemon.getTraining();
        }

        // Update UI display
        runOnUiThread(() -> {
            tvTotalLutemons.setText("Total lutemons: " + finalList.size());
            tvTotalBattles.setText("Battles: " + totalBattles[0]/2);
            tvTotalTrainings.setText("Trainings: " + totalTrainings[0]);
        });   //Lambda
    }

    private void setupRecyclerView() {

        rvLutemonStats.setLayoutManager(new LinearLayoutManager(this));
        rvLutemonStats.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        List<Lutemon> lutemonList = new ArrayList<>(Storage.getInstance().getLutemons().values());
        Collections.sort(lutemonList, (l1, l2) ->
                Integer.compare(l2.getBattles(), l1.getBattles()));

        LutemonStatsAdapter adapter = new LutemonStatsAdapter(lutemonList);
        rvLutemonStats.setAdapter(adapter);
    }

    private void setupBackButton() {
        ImageView btnBack = findViewById(R.id.imageView15);
        btnBack.setOnClickListener(v -> finish());
    }
}

