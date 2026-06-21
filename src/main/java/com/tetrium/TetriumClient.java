package com.tetrium;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import com.tetrium.config.TetriumConfig;

@Environment(EnvType.CLIENT)
public class TetriumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TetriumConfig.load();
        Tetrium.LOGGER.info("Tetrium client initialized");
    }
}
