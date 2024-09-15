package ru.healthanmary.trainingplugin;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.*;

public class CreateMenu {
    @Getter
    private static Inventory inv = Bukkit.createInventory(null, 9, "Доступные эффекты");
    public static void fillInventory() {
        ItemStack pane = new ItemStack(Material.PINK_STAINED_GLASS_PANE, 1);
        ItemMeta pane_meta = pane.getItemMeta();
        pane_meta.setDisplayName(" ");
        pane.setItemMeta(pane_meta);
        for (int i = 0; i < 9; i += 2)
            inv.setItem(i, pane);
    }
    private static ItemStack potion = new ItemStack(Material.POTION);
    private static void silka() {
        PotionMeta pm = (PotionMeta) potion.getItemMeta();
        pm.setBasePotionData(new PotionData(PotionType.STRENGTH, false, true));
        potion.setItemMeta(pm);
        inv.setItem(1, potion); }
    private static void speed() {
        PotionMeta pm = (PotionMeta) potion.getItemMeta();
        pm.setBasePotionData(new PotionData(PotionType.SPEED, false, true));
        potion.setItemMeta(pm);
        inv.setItem(3, potion); }
    private static void ogna() {
        PotionMeta pm = (PotionMeta) potion.getItemMeta();
        pm.setBasePotionData(new PotionData(PotionType.FIRE_RESISTANCE, true, false));
        potion.setItemMeta(pm);
        inv.setItem(5, potion); }
    private static void regen() {
        PotionMeta pm = (PotionMeta) potion.getItemMeta();
        pm.setBasePotionData(new PotionData(PotionType.REGEN, true, false));
        potion.setItemMeta(pm);
        inv.setItem(7, potion); }

    public static void openMenu(Player p) {
        silka();
        speed();
        ogna();
        regen();
        p.openInventory(inv);
    }
}
