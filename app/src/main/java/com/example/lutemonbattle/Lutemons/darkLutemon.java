package com.example.lutemonbattle.Lutemons;

import com.example.lutemonbattle.R;

public class darkLutemon extends Lutemon {

    public darkLutemon(String attribute, String Name, int atk, int def, int speed, int currentHp, int iconResId, int maxHealth, int experience){
        super(attribute, Name, atk, def, speed, currentHp, iconResId, maxHealth, experience);
        this.attribute = "Dark";
        this.atk = 9;
        this.def = 0;
        this.speed = 7;
        this.maxHealth = 16;
        this.currentHp = this.maxHealth;
        this.iconResId = R.drawable.dark_dragon;
    }
}
