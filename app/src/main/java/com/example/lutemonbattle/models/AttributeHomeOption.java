package com.example.lutemonbattle.models;

public class AttributeHomeOption {
    private String attributeName;
    private int atk;
    private int def;
    private int api;
    private int hp;
    private int iconResId;
    private boolean isSelected;

    public AttributeHomeOption(String attributeName, int atk, int def, int api, int hp, int iconResId) {
        this.attributeName = attributeName;
        this.atk = atk;
        this.def = def;
        this.api = api;
        this.hp = hp;
        this.iconResId = iconResId;
        this.isSelected = false; // Default as non-selected
    }

    // Getters and Setters
    public String getAttributeName() { return attributeName; }
    public void setAttributeName(String attributeName) { this.attributeName = attributeName; }

    public int getAtk() { return atk; }
    public void setAtk(int atk) { this.atk = atk; }

    public int getDef() { return def; }
    public void setDef(int def) { this.def = def; }

    public int getApi() { return api; }
    public void setApi(int api) { this.api = api; }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    public int getIconResId() { return iconResId; }
    public void setIconResId(int iconResId) { this.iconResId = iconResId; }

    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
}
