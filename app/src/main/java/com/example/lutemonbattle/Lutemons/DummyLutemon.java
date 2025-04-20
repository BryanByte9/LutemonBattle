package com.example.lutemonbattle.Lutemons;

import com.example.lutemonbattle.R;

public class DummyLutemon extends Lutemon {
    public DummyLutemon() {
        // The default Lutemon when no Lutemon is selected
        super(
                "Empty Slot",
                "No Lutemon",
                0,
                0,
                0,
                0,
                R.drawable.empty_slot,
                0,
                0
        );
        // Use id = -1 to indicate an empty slot
        this.id = -1;
    }

    public String toString() {
        return "Empty Slot";
    }
}