package ru.healthanmary.trainingplugin.ArmorStdMesssage;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;
import ru.healthanmary.trainingplugin.TrainingPlugin;

public class SpawnStand {
    public static void spawnStand(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (p == null)
            return;
        Bukkit.getScheduler().runTask(TrainingPlugin.getInstance(), () -> {
            ArmorStand armorStand = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);

            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName(e.getMessage());

            Bukkit.getScheduler().runTaskLater(TrainingPlugin.getInstance(), () -> {
                if (armorStand.isValid()) {
                    armorStand.remove();
                }
            }, 100L);

            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    if (!armorStand.isValid() || !p.isOnline()) {
                        this.cancel();
                        return;
                    }
                    armorStand.teleport(p.getLocation());
                }
            };

            task.runTaskTimer(TrainingPlugin.getInstance(), 0L, 1L);
        });
    }
}
