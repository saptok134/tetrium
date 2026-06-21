package com.tetrium.config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

public class TetriumConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("tetrium.json");
    private static TetriumConfig instance;

    // Performance Settings
    public boolean entityCulling = true;
    public boolean blockEntityCulling = true;
    public boolean particleCulling = true;
    public boolean smartAnimations = true;
    public boolean dynamicFPS = true;
    public boolean memoryOptimization = true;
    public boolean fasterChunkLoading = true;
    public boolean fasterChunkUnloading = true;
    public boolean inventoryFPSOptimization = true;
    public boolean recipeBookOptimization = true;
    public boolean tooltipCacheOptimization = true;

    // Multiplayer Settings
    public boolean manyPlayerOptimization = true;
    public int nameTagDistanceLimit = 64;
    public boolean entityTrackingOptimization = true;
    public boolean distantPlayerOptimization = true;

    // Render Settings
    public boolean fogEnabled = true;
    public boolean cloudsEnabled = true;
    public boolean skyEnabled = true;
    public boolean sunMoonEnabled = true;
    public boolean weatherEffectsEnabled = true;
    public float particleAmount = 1.0f;
    public int animationSpeed = 1;

    // Current Preset
    public String currentPreset = "Balanced";

    private TetriumConfig() {}

    public static synchronized TetriumConfig getInstance() {
        if (instance == null) {
            instance = new TetriumConfig();
        }
        return instance;
    }

    public static void load() {
        try {
            if (Files.exists(CONFIG_PATH)) {
                String json = Files.readString(CONFIG_PATH);
                TetriumConfig config = GSON.fromJson(json, TetriumConfig.class);
                instance = config;
            } else {
                instance = new TetriumConfig();
                save();
            }
        } catch (Exception e) {
            instance = new TetriumConfig();
        }
    }

    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            String json = GSON.toJson(instance);
            Files.writeString(CONFIG_PATH, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void applyPreset(String preset) {
        currentPreset = preset;
        switch (preset.toLowerCase()) {
            case "potato":
                entityCulling = true;
                blockEntityCulling = true;
                particleCulling = true;
                dynamicFPS = true;
                fogEnabled = false;
                cloudsEnabled = false;
                particleAmount = 0.25f;
                animationSpeed = 0;
                break;
            case "balanced":
                entityCulling = true;
                blockEntityCulling = true;
                particleCulling = true;
                dynamicFPS = true;
                fogEnabled = true;
                cloudsEnabled = true;
                particleAmount = 0.75f;
                animationSpeed = 1;
                break;
            case "high fps":
                entityCulling = true;
                blockEntityCulling = true;
                particleCulling = false;
                dynamicFPS = true;
                fogEnabled = true;
                cloudsEnabled = true;
                particleAmount = 0.5f;
                animationSpeed = 1;
                break;
            case "quality":
                entityCulling = true;
                blockEntityCulling = true;
                particleCulling = false;
                dynamicFPS = false;
                fogEnabled = true;
                cloudsEnabled = true;
                particleAmount = 1.0f;
                animationSpeed = 2;
                break;
        }
        save();
    }
}
