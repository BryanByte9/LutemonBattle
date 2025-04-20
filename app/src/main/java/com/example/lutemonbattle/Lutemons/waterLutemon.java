package com.example.lutemonbattle.Lutemons;

import com.example.lutemonbattle.R;

public class waterLutemon extends Lutemon {
    public waterLutemon(String attribute, String Name, int atk, int def, int speed, int currentHp, int iconResId, int maxHealth, int experience){
        super(attribute, Name, atk, def, speed, currentHp, iconResId, maxHealth, experience);
        this.attribute = "Water";
        this.atk = 5;
        this.def = 4;
        this.speed = 2;
        this.maxHealth = 20;
        this.currentHp = this.maxHealth;
        this.iconResId = R.drawable.water_dragon;
    }
}
