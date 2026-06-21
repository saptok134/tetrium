package com.tetrium.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.Camera;
import com.tetrium.config.TetriumConfig;

@Environment(EnvType.CLIENT)
@Mixin(Camera.class)
public class MixinCamera {
    // Camera optimization
}
