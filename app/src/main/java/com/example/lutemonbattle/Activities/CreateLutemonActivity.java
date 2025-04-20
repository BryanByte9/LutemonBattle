package com.example.lutemonbattle.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lutemonbattle.Attribute.AttributeOption;
import com.example.lutemonbattle.Attribute.AttributeOptionsAdapter;
import com.example.lutemonbattle.Lutemons.Lutemon;
import com.example.lutemonbattle.Lutemons.darkLutemon;
import com.example.lutemonbattle.Lutemons.fireLutemon;
import com.example.lutemonbattle.Lutemons.lightLutemon;
import com.example.lutemonbattle.Lutemons.rockLutemon;
import com.example.lutemonbattle.Lutemons.waterLutemon;
import com.example.lutemonbattle.Lutemons.windLutemon;
import com.example.lutemonbattle.Lutemons.woodLutemon;
import com.example.lutemonbattle.R;
import com.example.lutemonbattle.Lutemons.Storage;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class CreateLutemonActivity extends AppCompatActivity {
    private TextInputEditText lutemonNameInput;
    private AttributeOption selectedAttribute; // Save the attribute user selected
    private List<AttributeOption> attributeOptions = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Bind the input field for Lutemon name
        lutemonNameInput = findViewById(R.id.lutemonNameInput);

        // Initialize the 7 attribute options with correct preset values
        initializeAttributes();

        // Initialize RecyclerView and Adapter for attribute selection
        setupRecyclerView();

        // Bind the Create button and set its click listener
        ImageButton btnCreate = findViewById(R.id.imageButton);
        btnCreate.setOnClickListener(v -> createLutemon());

        // Bind the Cancel button and set its click listener to return to MainActivity
        ImageButton btnCancel = findViewById(R.id.imageButton2);
        btnCancel.setOnClickListener(v -> {
            Intent intent = new Intent(CreateLutemonActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }

    private void initializeAttributes() {
        attributeOptions.add(new AttributeOption("Fire", 8, 1, 4, 17, R.drawable.ic_fire, 1));
        attributeOptions.add(new AttributeOption("Water", 5, 4, 2, 20, R.drawable.ic_water, 2));
        attributeOptions.add(new AttributeOption("Wood", 6, 3, 5, 19, R.drawable.ic_wood, 3));
        attributeOptions.add(new AttributeOption("Rock", 6, 4, 1, 20, R.drawable.ic_rock, 4));
        attributeOptions.add(new AttributeOption("Wind", 10, 1, 10, 12, R.drawable.ic_wind, 5));
        attributeOptions.add(new AttributeOption("Light", 7, 2, 6, 18, R.drawable.ic_light, 6));
        attributeOptions.add(new AttributeOption("Dark", 9, 0, 7, 16, R.drawable.ic_dark, 7));
    }

    private void setupRecyclerView() {
        RecyclerView rvAttributeOptions = findViewById(R.id.rvAttributeOptions);
        rvAttributeOptions.setLayoutManager(new LinearLayoutManager(this));

        AttributeOptionsAdapter adapter = new AttributeOptionsAdapter(attributeOptions);
        adapter.setOnAttributeSelectedListener(selectedOption -> {
            selectedAttribute = selectedOption; // Save the selected attribute
            Log.d("Attribute", "Selected attribute: " + selectedOption.getAttributeName());
        });
        rvAttributeOptions.setAdapter(adapter);
    }

    private void createLutemon() {
        String name = lutemonNameInput.getText().toString().trim();

        // Check that name is not empty
        if (name.isEmpty()) {
            Toast.makeText(this, "Enter lutemon name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedAttribute == null) {
            Toast.makeText(this, "Select attribute", Toast.LENGTH_SHORT).show();
            return;
        }

        Lutemon lutemon = createLutemonByAttribute(name, selectedAttribute);

        // Add the created Lutemon to Storage (database)
        Storage storage = Storage.getInstance();
        storage.addLutemon(lutemon);

        Toast.makeText(this, "Lutemon created", Toast.LENGTH_SHORT).show();
        finish(); // Return to previous activity after creation
    }
    private Lutemon createLutemonByAttribute(String name, AttributeOption attribute) {
        switch (attribute.getType()) {
            case 1:
                return new fireLutemon(
                        "Fire",
                        name,
                        attribute.getAtk(),
                        attribute.getDef(),
                        4,
                        attribute.getHp(),
                        R.drawable.ic_fire,
                        attribute.getHp(),
                        0
                );
            case 2:
                return new waterLutemon(
                        "Water",
                        name,
                        attribute.getAtk(),
                        attribute.getDef(),
                        2,
                        attribute.getHp(),
                        R.drawable.ic_water,
                        attribute.getHp(),
                        0
                );
            case 3:
                return new woodLutemon(
                        "Wood",
                        name,
                        attribute.getAtk(),
                        attribute.getDef(),
                        5,
                        attribute.getHp(),
                        R.drawable.ic_wood,
                        attribute.getHp(),
                        0
                );
            case 4:
                return new rockLutemon(
                        "Rock",
                        name,
                        attribute.getAtk(),
                        attribute.getDef(),
                        1,
                        attribute.getHp(),
                        R.drawable.ic_rock,
                        attribute.getHp(),
                        0
                );
            case 5:
                return new windLutemon(
                        "Wind",
                        name,
                        attribute.getAtk(),
                        attribute.getDef(),
                        10,
                        attribute.getHp(),
                        R.drawable.ic_wind,
                        attribute.getHp(),
                        0
                );
            case 6:
                return new lightLutemon(
                        "Light",
                        name,
                        attribute.getAtk(),
                        attribute.getDef(),
                        6,
                        attribute.getHp(),
                        R.drawable.ic_light,
                        attribute.getHp(),
                        0
                );
            case 7:
                return new darkLutemon(
                        "Dark",
                        name,
                        attribute.getAtk(),
                        attribute.getDef(),
                        7,
                        attribute.getHp(),
                        R.drawable.ic_dark,
                        attribute.getHp(),
                        0
                );
            default:
                throw new IllegalArgumentException("Unknown");
        }
    }
}
