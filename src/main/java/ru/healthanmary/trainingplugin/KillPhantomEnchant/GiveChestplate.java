package ru.healthanmary.trainingplugin.KillPhantomEnchant;

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

public class GiveChestplate implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        ItemStack chestplate = new ItemStack(Material.ENCHANTED_BOOK, 1);
        chestplate.addUnsafeEnchantment(TrainingPlugin.humpHitEnchant, 0);
        ItemMeta meta = chestplate.getItemMeta();
        meta.addEnchant(TrainingPlugin.humpHitEnchant, 0, true);
        ArrayList<String> lore = new ArrayList<>();
//        lore.add(ChatColor.GRAY + "Удар горбом");
        meta.setLore(lore);
        chestplate.setItemMeta(meta);

        p.getInventory().addItem(chestplate);

        return true;
    }
}
