package ru.healthanmary.trainingplugin.KillPhantomEnchant;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import ru.healthanmary.trainingplugin.TrainingPlugin;

import java.util.ArrayList;

public class GiveBook implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        ItemStack enchBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
        if (enchBook.getItemMeta() instanceof EnchantmentStorageMeta) {
            EnchantmentStorageMeta enchMeta = (EnchantmentStorageMeta) enchBook.getItemMeta();
            enchMeta.addStoredEnchant(TrainingPlugin.humpHitEnchant, 1, true);
            enchBook.setItemMeta(enchMeta);
        } else {
            p.sendMessage("Check the console for errors or notify admins");
            TrainingPlugin.getInstance().getServer().getLogger().info(ChatColor.RED + p.getName() + "Tried to get the book but enchMeta isn`t an instanceof EnchantmentStorageMeta");
            return true;
        }
        ItemMeta meta = enchBook.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Удар горбом");
        meta.setLore(lore);
        enchBook.setItemMeta(meta);

        p.getInventory().addItem(enchBook);
        return true;
    }
}
