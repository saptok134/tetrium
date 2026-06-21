package com.tetrium.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.ParticleManager;
import com.tetrium.config.TetriumConfig;

@Environment(EnvType.CLIENT)
@Mixin(ParticleManager.class)
public class MixinParticleManager {
    // Particle manager optimization
}
