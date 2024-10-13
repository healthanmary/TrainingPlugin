package ru.healthanmary.trainingplugin.DrillEnchant;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GiveDrillEnchantedBook implements CommandExecutor {
    private final DrillEnchant drillEnchant;

    public GiveDrillEnchantedBook(DrillEnchant drillEnchant) {
        this.drillEnchant = drillEnchant;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Только для игроков!");
            return true; }

        Player p = (Player) sender;
        ItemStack enchBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) enchBook.getItemMeta();
        meta.addStoredEnchant(drillEnchant, 1, true);
        enchBook.setItemMeta(meta);

        p.getInventory().addItem(enchBook);
        return true;
    }
}
