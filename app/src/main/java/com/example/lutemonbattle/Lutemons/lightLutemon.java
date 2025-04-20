package com.example.lutemonbattle.Lutemons;

import com.example.lutemonbattle.R;

public class lightLutemon extends Lutemon {
    public lightLutemon(String attribute, String Name, int atk, int def, int speed, int currentHp, int iconResId, int maxHealth, int experience){
        super(attribute, Name, atk, def, speed, currentHp, iconResId, maxHealth, experience);
        this.attribute = "Light";
        this.atk = 7;
        this.def = 2;
        this.speed = 6;
        this.maxHealth = 18;
        this.currentHp = this.maxHealth;
        this.iconResId = R.drawable.light_dragon;
    }
}
