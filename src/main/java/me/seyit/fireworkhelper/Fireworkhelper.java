package me.seyit.fireworkhelper;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Fireworkhelper implements ModInitializer {

    private static KeyBinding useFireworkKey;

    @Override
    public void onInitialize() {
        useFireworkKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fireworkhelper.use_firework",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.fireworkhelper.keys"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> onTick());
    }

    private void onTick() {
        if (FireworkUtils.nullCheck()) return;

        if (useFireworkKey.wasPressed()) {
            FireworkUtils.requestUseFirework();
        }
    }
} 