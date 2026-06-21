package com.tetrium.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import com.tetrium.config.TetriumConfig;

@Environment(EnvType.CLIENT)
@Mixin(AbstractInventoryScreen.class)
public class MixinAbstractInventoryScreen {
    // Inventory optimization
}
