package me.seyit;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class NoMissClient implements ClientModInitializer {

    private static boolean enabled = true;
    private static KeyMapping toggleKey;

    @Override
    public void onInitializeClient() {

        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.no_miss.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                "key.categories.no_miss"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.consumeClick()) {
                enabled = !enabled;
            }
        });
    }

    public static boolean isEnabled() {
        return enabled;
    }
}
