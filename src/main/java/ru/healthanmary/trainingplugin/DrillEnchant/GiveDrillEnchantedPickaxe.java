package ru.healthanmary.trainingplugin.DrillEnchant;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import ru.healthanmary.trainingplugin.TrainingPlugin;

import java.util.ArrayList;

public class GiveDrillEnchantedPickaxe implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Только для игроков!");
            return true; }

        Player p = (Player) sender;
        ItemStack pickaxe = new ItemStack(Material.NETHERITE_PICKAXE, 1);
        ItemMeta meta = pickaxe.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Бур I (META)");
        meta.setLore(lore);
        pickaxe.setItemMeta(meta);
        pickaxe.addUnsafeEnchantment(TrainingPlugin.drillEnchant, 1);
        p.getInventory().addItem(pickaxe);

        return true;
    }
}
