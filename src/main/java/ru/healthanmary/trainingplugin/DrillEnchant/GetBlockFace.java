package ru.healthanmary.trainingplugin.DrillEnchant;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class GetBlockFace {
    public static BlockFace getBlockFace(Player p) {
        float pitch = p.getLocation().getPitch();
        float yaw = p.getLocation().getYaw();

        if (pitch < -45) return BlockFace.UP;
        if (pitch > 45) return BlockFace.DOWN;

        yaw = yaw % 360;
        if (yaw < 0) yaw += 360;

        if (yaw >= 315 || yaw < 45) return BlockFace.SOUTH;
        if (yaw < 135) return BlockFace.WEST;
        if (yaw < 225) return BlockFace.NORTH;
        return BlockFace.EAST;
    }
}
