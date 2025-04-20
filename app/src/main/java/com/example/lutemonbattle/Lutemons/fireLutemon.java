package com.example.lutemonbattle.Lutemons;

import com.example.lutemonbattle.R;

public class fireLutemon extends Lutemon {
    public fireLutemon(String attribute, String Name, int atk, int def, int speed, int currentHp, int iconResId, int maxHealth, int experience){
        super(attribute, Name, atk, def, speed, currentHp, iconResId, maxHealth, experience);
        this.attribute = "Fire";
        this.atk = 8;
        this.def = 1;
        this.speed = 4;
        this.maxHealth = 17;
        this.currentHp = this.maxHealth;
        this.iconResId = R.drawable.fire_dragon;
    }
}
