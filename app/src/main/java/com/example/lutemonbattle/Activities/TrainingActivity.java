package com.example.lutemonbattle.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lutemonbattle.Lutemons.Lutemon;
import com.example.lutemonbattle.Lutemons.Storage;
import com.example.lutemonbattle.R;

public class TrainingActivity extends AppCompatActivity {
    private Lutemon selectedLutemon;
    private TextView tvName, tvAttribute, tvStats;
    private ImageView ivIcon, ivIcon2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        initViews();
        loadSelectedLutemon();
        setupTrainingButton();
        setupReturnButtons();
    }

    private void initViews() {
        tvName = findViewById(R.id.tv_lutemon_name);
        tvAttribute = findViewById(R.id.tv_lutemon_attribute);
        tvStats = findViewById(R.id.tv_lutemon_stats);
        ivIcon = findViewById(R.id.lutemon_icon);
        ivIcon2 = findViewById(R.id.imageView12);
    }

    private void loadSelectedLutemon() {
        // Get the Lutemon ID from the intent
        int lutemonId = getIntent().getIntExtra("LUTEMON_ID", -1);

        // Get lutemon from storage
        Storage storage = Storage.getInstance();
        selectedLutemon = storage.getLutemonById(lutemonId);

        updateUI();

    }

    private void updateUI() {
        if (selectedLutemon != null) {
            tvName.setText(selectedLutemon.getName());
            tvAttribute.setText(selectedLutemon.getAttribute());
            tvStats.setText(String.format(
                    "ATK: %d DEF: %d HP: %d/%d EXP: %d",
                    selectedLutemon.getAtk(),
                    selectedLutemon.getDef(),
                    selectedLutemon.getCurrentHp(),
                    selectedLutemon.getMaxHealth(),
                    selectedLutemon.getExperience()
            ));
            setLutemonIcon();
        }else{
            tvName.setText("");
            tvAttribute.setText("No Lutemon Selected");
            tvStats.setText("ATK: 0 DEF: 0 HP: 0/0 EXP: 0");
            ivIcon.setImageResource(R.drawable.ic_default);
            ivIcon2.setImageResource(R.drawable.ic_default);
        }

    }

    private void setLutemonIcon() {
        String attr = selectedLutemon.getAttribute().toLowerCase();
        switch (attr) {
            case "fire":
                ivIcon.setImageResource(R.drawable.fire_dragon);
                ivIcon2.setImageResource(R.drawable.fire_dragon);
                break;
            case "water":
                ivIcon.setImageResource(R.drawable.water_dragon);
                ivIcon2.setImageResource(R.drawable.water_dragon);
                break;
            case "dark":
                ivIcon.setImageResource(R.drawable.dark_dragon);
                ivIcon2.setImageResource(R.drawable.dark_dragon);
                break;
            case "wind":
                ivIcon.setImageResource(R.drawable.wind_dragon);
                ivIcon2.setImageResource(R.drawable.wind_dragon);
                break;
            case "light":
                ivIcon.setImageResource(R.drawable.light_dragon);
                ivIcon2.setImageResource(R.drawable.light_dragon);
                break;
            case "rock":
                ivIcon.setImageResource(R.drawable.rock_dragon);
                ivIcon2.setImageResource(R.drawable.rock_dragon);
                break;
            case "wood":
                ivIcon.setImageResource(R.drawable.wood_dragon);
                ivIcon2.setImageResource(R.drawable.wood_dragon);
                break;
            default:
                ivIcon.setImageResource(R.drawable.ic_default);
                ivIcon2.setImageResource(R.drawable.ic_default);
        }
    }

    private void setupTrainingButton() {
        Button btnTrain = findViewById(R.id.btnTraining);
        btnTrain.setOnClickListener(v -> {
            if (selectedLutemon == null) {
                Toast.makeText(this, "Please select a Lutemon first", Toast.LENGTH_SHORT).show();
                return;
            }
            selectedLutemon.addTraining();
            selectedLutemon.addExperience();
            updateTrainingResult();
        });
    }

    private void updateTrainingResult() {
        tvStats.setText(String.format("ATK: %d DEF: %d HP: %d/%d\nExperience: %d",
                selectedLutemon.getAtk(),
                selectedLutemon.getDef(),
                selectedLutemon.getCurrentHp(),
                selectedLutemon.getMaxHealth(),
                selectedLutemon.getExperience()
        ));
        Toast.makeText(this, selectedLutemon.getName() + " Experience+1", Toast.LENGTH_SHORT).show();
    }

    private void setupReturnButtons() {
        Button btnHome = findViewById(R.id.button5);
        btnHome.setOnClickListener(v -> finish());
    }

    private void showToastAndFinish(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }
}
