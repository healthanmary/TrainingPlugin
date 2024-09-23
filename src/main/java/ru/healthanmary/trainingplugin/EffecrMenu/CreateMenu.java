package ru.healthanmary.trainingplugin.EffecrMenu;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class CreateMenu {
    @Getter
    private static Inventory inv = Bukkit.createInventory(null, 9, "Доступные эффекты");
    private static ItemStack potion = new ItemStack(Material.POTION);

    private static void setParams(PotionType pt, boolean extended, boolean upgraded, int index) {
        PotionMeta pm = (PotionMeta) potion.getItemMeta();
        pm.setBasePotionData(new PotionData(pt, extended, upgraded));
        potion.setItemMeta(pm);
        inv.setItem(index, potion);
    }

    public static void openMenu(Player p) {
        ItemStack pane = new ItemStack(Material.PINK_STAINED_GLASS_PANE, 1);
        ItemMeta pane_meta = pane.getItemMeta();
        pane_meta.setDisplayName(" ");
        pane.setItemMeta(pane_meta);
        for (int i = 0; i < 9; i += 2)
            inv.setItem(i, pane);

        setParams(PotionType.STRENGTH, false, true, 1);
        setParams(PotionType.SPEED, false, true, 3);
        setParams(PotionType.FIRE_RESISTANCE, true, false, 5);
        setParams(PotionType.REGEN, true, false, 7);

        p.openInventory(inv);
    }
}
