package com.tetrium.mixin;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.particle.Particle;
import com.tetrium.config.TetriumConfig;

@Mixin(Particle.class)
public class MixinParticle {
    // Particle culling optimization placeholder
}
