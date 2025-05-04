package me.seyit.fireworkhelper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class FireworkUtils {
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    private static int prevSlot = -1;

    public static void requestUseFirework() {
        if (nullCheck()) return;

        if (!mc.player.isFallFlying()) {
            return;
        }

        prevSlot = mc.player.getInventory().selectedSlot;

        if (!selectFirework()) {
            return;
        }

        Vec3d pos = mc.player.getPos();
        Direction direction = Direction.DOWN;

        mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);

        mc.execute(() -> {
            if (prevSlot != -1) {
                mc.player.getInventory().selectedSlot = prevSlot;
                prevSlot = -1;
            }
        });
    }

    private static boolean selectFirework() {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (stack.getItem() == Items.FIREWORK_ROCKET) {
                mc.player.getInventory().selectedSlot = i;
                return true;
            }
        }
        return false;
    }

    public static boolean nullCheck() {
        return MinecraftClient.getInstance().world == null || MinecraftClient.getInstance().player == null;
    }
} 