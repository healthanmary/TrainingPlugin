package ru.healthanmary.trainingplugin.DrillEnchant;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import ru.healthanmary.trainingplugin.TrainingPlugin;

import java.util.Set;

public class DrillBlockBreakListener implements Listener {
    @EventHandler
    public void on(BlockBreakEvent e) {
        if (!(e.getPlayer().getInventory().getItemInMainHand().getType().name().endsWith("_PICKAXE"))) return;
        ItemStack itemInHand = e.getPlayer().getInventory().getItemInMainHand();
        Enchantment custoumEnchantment = TrainingPlugin.drillEnchant;
        if (!itemInHand.getEnchantments().containsKey(Enchantment.getByKey(custoumEnchantment.getKey()))) return;
        breakBlocks(e);
    }

    private void breakBlocks(BlockBreakEvent e) {
        Block centralBlock = e.getBlock();
        Player p = e.getPlayer();
        BlockFace blockFace = GetBlockFace.getBlockFace(p);
        int breakedBlocks = 0;
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {

                Block nearbyBlock;
                if (blockFace == BlockFace.UP || blockFace == BlockFace.DOWN)
                    nearbyBlock = centralBlock.getRelative(x, 0, y);
                else if (blockFace == BlockFace.NORTH || blockFace == BlockFace.SOUTH)
                    nearbyBlock = centralBlock.getRelative(x, y, 0);
                else nearbyBlock = centralBlock.getRelative(0, x, y);

                Set<Material> items = Set.of(
                        Material.AIR,
                        Material.BEDROCK,
                        Material.WATER,
                        Material.LAVA,
                        Material.BARREL,
                        Material.NETHER_PORTAL,
                        Material.COMMAND_BLOCK,
                        Material.END_PORTAL_FRAME,
                        Material.END_PORTAL,
                        Material.STRUCTURE_BLOCK,
                        Material.STRUCTURE_VOID
                );
                if (!items.contains(nearbyBlock.getType()) && !nearbyBlock.equals(centralBlock)) {
                    breakedBlocks++;
                    Bukkit.getScheduler().runTask(TrainingPlugin.getInstance(), () -> {
                        nearbyBlock.breakNaturally(p.getInventory().getItemInMainHand());
                    });
                }
            }
        }
        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        Damageable damageable = (Damageable) meta;
        damageable.setDamage(damageable.getDamage() + breakedBlocks);
        item.setItemMeta(meta);
    }
}
