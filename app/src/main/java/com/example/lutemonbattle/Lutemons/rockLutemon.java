package com.example.lutemonbattle.Lutemons;

import com.example.lutemonbattle.R;

public class rockLutemon extends Lutemon {
    public rockLutemon(String attribute, String Name, int atk, int def, int speed, int currentHp, int iconResId, int maxHealth, int experience){
        super(attribute, Name, atk, def, speed, currentHp, iconResId, maxHealth, experience);
        this.attribute = "Rock";
        this.atk = 6;
        this.def = 4;
        this.speed = 1;
        this.maxHealth = 20;
        this.currentHp = this.maxHealth;
        this.iconResId = R.drawable.rock_dragon;
    }
}
