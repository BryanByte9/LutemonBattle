package com.example.lutemonbattle.Activities;

import android.content.Intent;
import android.media.Image;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lutemonbattle.Lutemons.DummyLutemon;
import com.example.lutemonbattle.Lutemons.Lutemon;
import com.example.lutemonbattle.Lutemons.Storage;
import com.example.lutemonbattle.R;

public class BattleActivity extends AppCompatActivity {
    private Lutemon lutemonA;
    private Lutemon lutemonB;
    private String description;
    private TextView textView;
    private Button attackButton;
    private Button stopButton;
    private boolean isBattleActive = true;
    private boolean isAttackNext = false;
    private int round = 1; // count which round it is
    private Storage storage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        // Initialize storage
        storage = Storage.getInstance();

        // Initialize views
        textView = findViewById(R.id.tv_battle_log4);
        attackButton = findViewById(R.id.btnBattle);
        stopButton = findViewById(R.id.button14);

        // Get lutemon id from intent
        int lutemonAId = getIntent().getIntExtra("LUTEMON_A_ID", -1);
        int lutemonBId = getIntent().getIntExtra("LUTEMON_B_ID", -1);

        // Use id to get lutemon from storage
        lutemonA = (lutemonAId != -1) ? storage.getLutemonById(lutemonAId) : new DummyLutemon();
        lutemonB = (lutemonBId != -1) ? storage.getLutemonById(lutemonBId) : new DummyLutemon();

        updateEmptyState();

        // Set icon for battle
        ImageView attackerImage1 = findViewById(R.id.attackerP1);
        ImageView attackerImage2 = findViewById(R.id.attackerP2);
        if (lutemonA != null && !(lutemonA instanceof DummyLutemon)) {
            attackerImage1.setImageResource(lutemonA.getIconResId());
        }
        if (lutemonB != null && !(lutemonB instanceof DummyLutemon)) {
            attackerImage2.setImageResource(lutemonB.getIconResId());
        }

        //Set button for attack
        attackButton.setOnClickListener(view -> {
            if (isBattleActive && !isAttackNext) {
                isAttackNext = true; // To mark this round as attack
            }
        });

        // Set button for stop attack
        stopButton.setOnClickListener(view -> {
            isBattleActive = false;
            description = "Battle has been stopped.";
            textView.setText(description);
        });

        // Linear iteration
        new Thread(() -> {
            while (isBattleActive) {
                if (isAttackNext) {
                    isAttackNext = false; // Reset mark of attack
                    runOnUiThread(() -> battleRound());
                }
            }
        }).start();

        // Return button back to MainActivity
        ImageView backImage = findViewById(R.id.imageView14);
        backImage.setOnClickListener(v -> {
            Intent intent = new Intent(BattleActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }

    // Decide who attack based on round
    private void battleRound() {
        if (lutemonA instanceof DummyLutemon || lutemonB instanceof DummyLutemon) {
            endBattle();
            return;
        }

        if (lutemonA.getCurrentHp() <= 0 || lutemonB.getCurrentHp() <= 0) {
            endBattle();
            return;
        }

        Lutemon attacker, defender;
        if (round % 2 == 1) {
            attacker = (lutemonA.getSpeed() >= lutemonB.getSpeed()) ? lutemonA : lutemonB;
        } else {
            attacker = (lutemonA.getSpeed() < lutemonB.getSpeed()) ? lutemonA : lutemonB;
        }
        defender = (attacker == lutemonA) ? lutemonB : lutemonA;

        performAttack(attacker, defender);
        round++;
    }

    // For each attack
    public void performAttack(Lutemon attacker, Lutemon defender) {
        TextView hp1 = findViewById(R.id.textViewhp1);
        TextView hp2 = findViewById(R.id.textViewhp2);
        hp1.setText(lutemonA.getCurrentHp()+"/"+lutemonA.getMaxHealth());
        hp2.setText(lutemonB.getCurrentHp()+"/"+lutemonB.getMaxHealth()); 
        ProgressBar hpBar1 = findViewById(R.id.health1);
        ProgressBar hpBar2 = findViewById(R.id.health2);
        //Display hp
        hpBar1.setMax(lutemonA.getMaxHealth());
        hpBar2.setMax(lutemonB.getMaxHealth());
        if (attacker==lutemonA){
            ImageView sword = findViewById(R.id.imageView11);
            sword.setImageResource(R.drawable.ic_leftsword);
        }
        else if (attacker==lutemonB){
            ImageView sword = findViewById(R.id.imageView11);
            sword.setImageResource(R.drawable.ic_rightsword);
        }

        if (attacker instanceof DummyLutemon || defender instanceof DummyLutemon) {
            return;
        }
        // Make sure attacker and defender are real lutemon object
        Lutemon realAttacker = storage.getLutemonById(attacker.getId());
        Lutemon realDefender = storage.getLutemonById(defender.getId());
        int damage = Math.max(realAttacker.getAtk() - realDefender.getDef() + realAttacker.getExperience(), 0)+(int)(Math.random()*4);
        //Randomness in attack
        int newHp = realDefender.getCurrentHp() - damage;
        realDefender.setCurrentHp(newHp);
        if (realDefender.getCurrentHp() <= 0) {
            realDefender.setCurrentHp(0);
            realDefender.resetExperience(0);
            realAttacker.addExperience();
            description = realAttacker.getAttribute() + "(" + realAttacker.getName() + ") attacks " +
                    realDefender.getAttribute() + "(" + realDefender.getName() + ")\n" +
                    realDefender.getAttribute() + "(" + realDefender.getName() + ") gets killed\n" +
                    realAttacker.getAttribute() + "(" + realAttacker.getName() + ") gains 1 experience!\n" +
                    "Battle is over";
            textView.setText(description);
            hpBar1.setProgress(lutemonA.getCurrentHp());
            hpBar2.setProgress(lutemonB.getCurrentHp());
            hp1.setText(lutemonA.getCurrentHp()+"/"+lutemonA.getMaxHealth());
            hp2.setText(lutemonB.getCurrentHp()+"/"+lutemonB.getMaxHealth());
            endBattle();
        } else {
            description = realAttacker.getAttribute() + "(" + realAttacker.getName() + ") attacks " +
                    realDefender.getAttribute() + "(" + realDefender.getName() + ")\n" +realDefender.getAttribute() +
                    "(" + realDefender.getName() + ")"+" manages to escape death.\n"+ realDefender.getAttribute() +
                    "(" + realDefender.getName() + ") health: " + realDefender.getCurrentHp() +
                    "/" + realDefender.getMaxHealth()+"\n"+realAttacker.getAttribute() +
                    "(" + realAttacker.getName() + ") health: " + realAttacker.getCurrentHp() +
                    "/" + realAttacker.getMaxHealth();
            textView.setText(description);
            hpBar1.setProgress(realAttacker.getCurrentHp());
            hpBar2.setProgress(realDefender.getCurrentHp());
            hp1.setText(lutemonA.getCurrentHp()+"/"+lutemonA.getMaxHealth()); 
        }   hp2.setText(lutemonB.getCurrentHp()+"/"+lutemonB.getMaxHealth()); 
    }

    private void endBattle() {

        if (!(lutemonA instanceof DummyLutemon)) {
            Lutemon realA = storage.getLutemonById(lutemonA.getId());
            if (realA != null) {
                realA.addBattles();
                if (lutemonB.getCurrentHp() <= 0) realA.addWins();
            }
        }

        if (!(lutemonB instanceof DummyLutemon)) {
            Lutemon realB = storage.getLutemonById(lutemonB.getId());
            if (realB != null) {
                realB.addBattles();
                if (lutemonA.getCurrentHp() <= 0) realB.addWins();
            }
        }

        isBattleActive = false;

    }

    //If user enter without Lutemon selection
    private void updateEmptyState() {
        if (lutemonA instanceof DummyLutemon || lutemonB instanceof DummyLutemon) {
            textView.setText("No Lutemons selected!\nGo to Home to choose fighters.");
            attackButton.setEnabled(false);
            stopButton.setEnabled(false);
            ImageView attackerImage1 = findViewById(R.id.attackerP1);
            ImageView attackerImage2 = findViewById(R.id.attackerP2);
            attackerImage1.setImageResource(R.drawable.ic_default);
            attackerImage2.setImageResource(R.drawable.ic_default);
        }
    }
}
