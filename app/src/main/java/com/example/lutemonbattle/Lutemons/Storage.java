package com.example.lutemonbattle.Lutemons;

import java.io.Serializable;
import java.util.HashMap;

public class Storage implements Serializable {
    private static final long serialVersionUID = 1L;

    protected static Storage instance; // Singleton instance

    private int nextLutemonId = 1; // Next ID for lutemon

    protected HashMap<Integer, Lutemon> lutemons;
    protected int totalLutemonCreated;
    protected int totalBattles;
    protected int totalTrainingSessions;

    public Storage() {
        lutemons = new HashMap<>();
        totalLutemonCreated = 0;
        totalBattles = 0;
        totalTrainingSessions = 0;
    }

    public static synchronized Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public void addLutemon(Lutemon lutemon) {
        lutemons.put(lutemon.getId(), lutemon);
        totalLutemonCreated++;
    }

    public Lutemon getLutemonById(int id) {
        return lutemons.get(id);
    }

    // Return the ID counter for next Lutemon
    public int getNextLutemonId() {
        return nextLutemonId++;
    }

    // Return all Lutemons in the storage
    public HashMap<Integer, Lutemon> getLutemons() {
        return lutemons;
    }

    // readResolve : after de-serialize, make it to be the storage singleton object
    private Object readResolve() {
        instance = this;
        return instance;
    }

}
