package com.tetrium;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tetrium implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("tetrium");
    public static final String MOD_ID = "tetrium";

    @Override
    public void onInitialize() {
        LOGGER.info("Tetrium optimization mod loaded successfully!");
    }
}
