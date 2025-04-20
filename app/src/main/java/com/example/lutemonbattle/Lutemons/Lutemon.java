package com.example.lutemonbattle.Lutemons;

import java.io.Serializable;

public class Lutemon implements Serializable {
        private static final long serialVersionUID = 1L;
        protected String name, attribute;
        protected int atk, def, speed, maxHealth, experience, currentHp;
        protected int iconResId;
        protected int id;//The"ID" of Lutemon
        protected int battles, wins, training;//Keep track of info

        public Lutemon(String attribute, String name, int atk, int def,int speed, int currentHp, int iconResId, int maxHealth, int experience) {
            this.attribute = attribute;
            this.experience=experience;
            this.name = name;
            this.atk = atk;
            this.def = def;
            this.speed = speed;
            this.currentHp = currentHp;
            this.maxHealth = maxHealth;
            this.iconResId = iconResId;
            this.id = Storage.getInstance().getNextLutemonId();
            this.battles = 0;
            this.wins = 0;
            this.training = 0;

        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public int getSpeed() {
            return speed;
        }
        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public int getAtk() {return atk;}

        public int getDef() {
            return def;
        }
        public void setDef(int def) {
            this.def = def;
        }

        public String getAttribute() {
            return attribute;
        }

        public int getCurrentHp() {
            return currentHp;
        }
        public void setCurrentHp(int currentHp) {
            this.currentHp = Math.max(0, Math.min(currentHp, maxHealth));
        }


        public int getMaxHealth() { return maxHealth; }
        public void setMaxHealth(int maxHealth) {
            this.maxHealth = maxHealth;
            this.currentHp = Math.min(currentHp, maxHealth);
        }

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id; }


        public int getIconResId() {
            return iconResId; }

        public void setIconResId(int iconResId) {
            this.iconResId = iconResId; }


        public void restoreHp() {
            currentHp = maxHealth;
        }

        public void takeDamage(int damage) {
            setCurrentHp(currentHp - Math.max(damage - def, 0));
        }


        public void heal(int amount) {
            setCurrentHp(currentHp + amount);
        }


        public boolean isAlive() {
            return currentHp > 0;
        }


        public String toString() {
            return String.format("%s (%s) #%04d\nATK: %d  DEF: %d  HP: %d/%d",
                    name, id, atk, def, currentHp, maxHealth);
        }

        public int getExperience() {
            return experience;
        }


        //Getter & Adder of Wins, Battles & Training
        public int getWins(){
            return wins;
        }
        public int getBattles(){
            return battles;
        }
        public int getTraining(){
            return training;
        }

        public void addWins(){
            wins++;
        }
        public void addBattles(){
            battles++;
        }
        public void addTraining(){
            training++;

        }
        public void addExperience(){
            experience++;
        }

        public void resetExperience(int ex) {
            this.experience = ex ;
        }
    }
