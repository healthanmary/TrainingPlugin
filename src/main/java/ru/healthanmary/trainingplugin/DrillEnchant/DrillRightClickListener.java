package ru.healthanmary.trainingplugin.DrillEnchant;

import lombok.Getter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import ru.healthanmary.trainingplugin.TrainingPlugin;

import java.util.HashMap;

public class DrillRightClickListener implements Listener {
    @Getter
    private HashMap<Player, Boolean> isDrillActive = new HashMap<>();
    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player p = e.getPlayer();;
        if (!p.isSneaking()) return;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (!item.getEnchantments().containsKey(Enchantment.getByKey(TrainingPlugin.drillEnchant.getKey()))) return;
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Boolean active = isDrillActive.get(p);
            if (active == null || active) {
                isDrillActive.put(p, false);
                p.sendActionBar("Бур отключен!");
                p.sendMessage("Бур отключен!");
            }
            else {
                isDrillActive.put(p, true);
                p.sendActionBar("Бур включен!");
                p.sendMessage("Бур включен!");
            }
        }
    }
    public boolean isActivePlayer(Player p) {
        return isDrillActive.get(p);
    }
}
