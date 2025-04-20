package com.example.lutemonbattle.Attribute;

public class AttributeOption {
    private String attributeName;
    private int atk;
    private int def;
    private int api;
    private int hp;
    private int iconResId;
    private boolean isSelected;
    private int type;

    public AttributeOption(String attributeName, int atk, int def,int api, int hp, int iconResId, int type) {
        this.attributeName = attributeName;
        this.atk = atk;
        this.def = def;
        this.api = api;
        this.hp = hp;
        this.iconResId = iconResId;
        this.isSelected = false;
        this.type = type;
    }

    public String getAttributeName() { return attributeName; }
    public int getAtk() { return atk; }
    public int getDef() { return def; }
    public int getApi() { return api; }
    public int getHp() { return hp; }
    public int getIconResId() { return iconResId; }
    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
    public int getType() {return type;}
}