package com.example.lutemonbattle.Lutemons;

import android.content.Context;
import android.util.Log;
import java.io.*;

public class StorageSerializer {
    private static final String STORAGE_FILE = "storage.ser";

    // Save the Storage singleton instance to file
    public static boolean saveStorage(Context context) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                context.openFileOutput(STORAGE_FILE, Context.MODE_PRIVATE))) {
            oos.writeObject(Storage.getInstance());
            return true;
        } catch (IOException e) {
            Log.e("Storage", "Fail to save " + e.getMessage());
            return false;
        }
    }

    // Load Storage singleton instance from file
    public static boolean loadStorage(Context context) {
        try (ObjectInputStream ois = new ObjectInputStream(
                context.openFileInput(STORAGE_FILE))) {
            // De-serialize the Storage singleton instance
            Storage loadedStorage = (Storage) ois.readObject();
            return true;
        } catch (FileNotFoundException e) {
            Log.w("Storage", "No file exist, creating new storage");
            Storage.getInstance();
            return false;
        } catch (IOException | ClassNotFoundException e) {
            Log.e("Storage", "Fail to load " + e.getMessage());
            return false;
        }
    }
}
