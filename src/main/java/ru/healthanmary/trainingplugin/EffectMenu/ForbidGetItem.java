package ru.healthanmary.trainingplugin.EffectMenu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ForbidGetItem implements Listener {
    @EventHandler
    public void on(InventoryClickEvent e) {
        if (e.getInventory().equals(CreateMenu.getInv()))
            e.setCancelled(true);
    }
}
