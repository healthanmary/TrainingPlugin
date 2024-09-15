package ru.healthanmary.trainingplugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class GetPotionEffect implements Listener {
    private void gh(Player p, String perm_name, PotionEffectType potionEffectType, String effect_name, int duration, int lvl) {
        Location plLoc = p.getLocation();
        if (p.hasPermission(perm_name)) {
            p.addPotionEffect(new PotionEffect(potionEffectType, duration, lvl));
            p.playSound(plLoc, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            p.sendMessage(ChatColor.GOLD + "Вы получили эффект: " + ChatColor.RED + effect_name); }
        else {
            p.sendMessage(ChatColor.RED + "Вам недоступен эффект: " + ChatColor.WHITE + effect_name);
            p.playSound(plLoc, Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f); }
        }
    @EventHandler
    public void on(InventoryClickEvent e) {
        if (!e.getInventory().equals(CreateMenu.getInv())) return;
        Player p = (Player) e.getWhoClicked();
        switch (e.getSlot()) {
            case 1:
                gh(p, "effectmenu.get.sila", PotionEffectType.INCREASE_DAMAGE, "силы", 1800, 1);
                break;
            case 3:
                gh(p, "effectmenu.get.speed", PotionEffectType.SPEED, "скорости", 1800, 1);
                break;
            case 5:
                gh(p, "effectmenu.get.ogna", PotionEffectType.FIRE_RESISTANCE, "огнестойкости", 9600, 0);
                break;
            case 7:
                gh(p, "effectmenu.get.regen", PotionEffectType.REGENERATION, "регенерации", 1800, 0);
                break;
        }
    }
}
