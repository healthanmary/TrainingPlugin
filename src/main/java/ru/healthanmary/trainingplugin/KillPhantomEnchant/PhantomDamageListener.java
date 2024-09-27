package ru.healthanmary.trainingplugin.KillPhantomEnchant;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import ru.healthanmary.trainingplugin.TrainingPlugin;

public class PhantomDamageListener implements Listener {
    @EventHandler
    public void on(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Phantom)) return;

        Player p = (Player) e.getEntity();
        ItemStack playerChestplate = p.getInventory().getChestplate();

        if (playerChestplate == null) return;

        Enchantment custoumEnchantment = TrainingPlugin.humpHitEnchant;
        if (!playerChestplate.getEnchantments().containsKey(Enchantment.getByKey(custoumEnchantment.getKey()))) return;

        Phantom phantom = (Phantom) e.getDamager();
        phantom.setHealth(0);
    }
}
