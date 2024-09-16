package ru.healthanmary.trainingplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.HashMap;
import java.util.UUID;

public class GapleCooldown implements Listener {
    private final HashMap<UUID, Long> cooldown;
    public GapleCooldown() { this.cooldown = new HashMap<>(); }

    @EventHandler
    public void on(PlayerItemConsumeEvent e) {
        if (!e.getItem().getType().equals(Material.GOLDEN_APPLE))
            return;

        UUID uuid = e.getPlayer().getUniqueId();
        if (!this.cooldown.containsKey(uuid)) {
            e.getPlayer().setCooldown(Material.GOLDEN_APPLE, 100);
            this.cooldown.put(uuid, System.currentTimeMillis());
            return;
        } else {
            long time = System.currentTimeMillis() - this.cooldown.get(uuid);
            if (time >= 5000) {
                e.getPlayer().setCooldown(Material.GOLDEN_APPLE, 100);
                this.cooldown.put(uuid, System.currentTimeMillis());
                return;
            } else {
                e.getPlayer().sendMessage(ChatColor.RED + "Подождите! Вы не можете делать это еще: "
                        + ChatColor.GOLD + (5000 - time) / 1000 + " секунд!");
                e.setCancelled(true);
            }
        }
    }
}
