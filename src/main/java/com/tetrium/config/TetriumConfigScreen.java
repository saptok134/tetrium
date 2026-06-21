package com.tetrium.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class TetriumConfigScreen extends Screen {
    private final Screen parent;
    private final TetriumConfig config = TetriumConfig.getInstance();

    public TetriumConfigScreen(Screen parent) {
        super(Text.literal("Tetrium Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        this.clearChildren();

        int centerX = this.width / 2;
        int startY = 20;
        int buttonWidth = 150;
        int buttonHeight = 20;
        int spacing = 25;

        // Performance Settings
        addToggleButton(centerX, startY + spacing * 2, buttonWidth, buttonHeight, "Entity Culling",
            () -> config.entityCulling, v -> config.entityCulling = v);
        addToggleButton(centerX, startY + spacing * 3, buttonWidth, buttonHeight, "Block Entity Culling",
            () -> config.blockEntityCulling, v -> config.blockEntityCulling = v);
        addToggleButton(centerX, startY + spacing * 4, buttonWidth, buttonHeight, "Particle Culling",
            () -> config.particleCulling, v -> config.particleCulling = v);
        addToggleButton(centerX, startY + spacing * 5, buttonWidth, buttonHeight, "Smart Animations",
            () -> config.smartAnimations, v -> config.smartAnimations = v);
        addToggleButton(centerX, startY + spacing * 6, buttonWidth, buttonHeight, "Dynamic FPS",
            () -> config.dynamicFPS, v -> config.dynamicFPS = v);

        // Render Settings
        addToggleButton(centerX, startY + spacing * 8, buttonWidth, buttonHeight, "Fog",
            () -> config.fogEnabled, v -> config.fogEnabled = v);
        addToggleButton(centerX, startY + spacing * 9, buttonWidth, buttonHeight, "Clouds",
            () -> config.cloudsEnabled, v -> config.cloudsEnabled = v);
        addToggleButton(centerX, startY + spacing * 10, buttonWidth, buttonHeight, "Sky",
            () -> config.skyEnabled, v -> config.skyEnabled = v);
        addToggleButton(centerX, startY + spacing * 11, buttonWidth, buttonHeight, "Sun & Moon",
            () -> config.sunMoonEnabled, v -> config.sunMoonEnabled = v);
        addToggleButton(centerX, startY + spacing * 12, buttonWidth, buttonHeight, "Weather Effects",
            () -> config.weatherEffectsEnabled, v -> config.weatherEffectsEnabled = v);

        // Preset Buttons
        int presetY = startY + spacing * 14;
        addPresetButton(centerX - 160, presetY, 70, 20, "Potato", () -> config.applyPreset("Potato"));
        addPresetButton(centerX - 80, presetY, 70, 20, "Balanced", () -> config.applyPreset("Balanced"));
        addPresetButton(centerX, presetY, 70, 20, "High FPS", () -> config.applyPreset("High FPS"));
        addPresetButton(centerX + 80, presetY, 70, 20, "Quality", () -> config.applyPreset("Quality"));

        // Back Button
        this.addDrawableChild(new ButtonWidget(centerX - 75, this.height - 30, 150, 20,
            Text.literal("Back"), button -> this.client.setScreen(parent)));
    }

    private void addToggleButton(int x, int y, int width, int height, String label,
            java.util.function.BooleanSupplier getter, java.util.function.Consumer<Boolean> setter) {
        this.addDrawableChild(new ButtonWidget(x - width / 2, y, width, height,
            Text.literal((getter.getAsBoolean() ? "[ON] " : "[OFF] ") + label)
                .formatted(getter.getAsBoolean() ? Formatting.GREEN : Formatting.RED),
            button -> {
                boolean newState = !getter.getAsBoolean();
                setter.accept(newState);
                button.setMessage(Text.literal((newState ? "[ON] " : "[OFF] ") + label)
                    .formatted(newState ? Formatting.GREEN : Formatting.RED));
                TetriumConfig.save();
            }));
    }

    private void addPresetButton(int x, int y, int width, int height, String label, Runnable action) {
        this.addDrawableChild(new ButtonWidget(x, y, width, height, Text.literal(label),
            button -> {
                action.run();
                this.init();
            }));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
