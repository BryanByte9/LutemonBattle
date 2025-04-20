package com.example.lutemonbattle.Lutemons;

import com.example.lutemonbattle.R;

public class windLutemon extends Lutemon {
    public windLutemon(String attribute, String Name, int atk, int def, int speed, int currentHp, int iconResId, int maxHealth, int experience){
        super(attribute, Name, atk, def, speed, currentHp, iconResId, maxHealth, experience);
        this.attribute = "Wind";
        this.atk = 10;
        this.def = 1;
        this.speed = 10;
        this.maxHealth = 12;
        this.currentHp = this.maxHealth;
        this.iconResId = R.drawable.wind_dragon;
    }
}
