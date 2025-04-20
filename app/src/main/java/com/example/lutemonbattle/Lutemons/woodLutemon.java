package com.example.lutemonbattle.Lutemons;

import com.example.lutemonbattle.R;

public class woodLutemon extends Lutemon {
    public woodLutemon(String attribute, String Name, int atk, int def, int speed, int currentHp, int iconResId, int maxHealth, int experience){
        super(attribute, Name, atk, def, speed, currentHp, iconResId, maxHealth, experience);
        this.attribute = "Wood";
        this.atk = 6;
        this.def = 3;
        this.speed = 5;
        this.maxHealth = 19;
        this.currentHp = this.maxHealth;
        this.iconResId = R.drawable.wood_dragon;
    }
}
