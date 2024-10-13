package ru.healthanmary.trainingplugin;

import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;
import ru.healthanmary.trainingplugin.DrillEnchant.*;
import ru.healthanmary.trainingplugin.ArmorStdMesssage.PlayerSendMessageHooker;
import ru.healthanmary.trainingplugin.KillPhantomEnchant.GiveBook;
import ru.healthanmary.trainingplugin.KillPhantomEnchant.GiveEnchantedChestplate;
import ru.healthanmary.trainingplugin.KillPhantomEnchant.HumpHitEnchant;
import ru.healthanmary.trainingplugin.KillPhantomEnchant.PhantomDamageListener;
import ru.healthanmary.trainingplugin.Other.GapleCooldown;
import ru.healthanmary.trainingplugin.ShulkerAnimation.SpawnCommnd;
import ru.healthanmary.trainingplugin.commands.NearHelp;

import java.lang.reflect.Field;
import java.util.HashMap;

public final class TrainingPlugin extends JavaPlugin {
    private static TrainingPlugin instance;
    public static HumpHitEnchant humpHitEnchant;
    public static DrillEnchant drillEnchant;
    public final DrillRightClickListener drillRightClickListener = new DrillRightClickListener();
    @Override
    public void onEnable() {
        instance = this;
        humpHitEnchant = new HumpHitEnchant("humphit");
        drillEnchant = new DrillEnchant("drill");
        registerEnchantment(humpHitEnchant);
        registerEnchantment(drillEnchant);
        getCommand("nearbyplayers").setExecutor(new NearHelp());
        getCommand("getdrillenchant").setExecutor(new GiveDrillEnchantedBook(new DrillEnchant("drill")));
        getCommand("standspawn").setExecutor(new SpawnCommnd());
        getCommand("gp").setExecutor(new GiveDrillEnchantedPickaxe());
        getCommand("getchestplate").setExecutor(new GiveBook());
        getCommand("getbook").setExecutor(new GiveEnchantedChestplate());
        getServer().getPluginManager().registerEvents(new DrillRightClickListener(), this);
        getServer().getPluginManager().registerEvents(new PhantomDamageListener(), this);
        getServer().getPluginManager().registerEvents(new GapleCooldown(), this);
        getServer().getPluginManager().registerEvents(new PlayerSendMessageHooker(), this);
        getServer().getPluginManager().registerEvents(new DrillBlockBreakListener(drillRightClickListener), this);
        getServer().getPluginManager().registerEvents(new DrillRightClickListener(), this);

    }
    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            // It's been registered!
        }
    }
    public static TrainingPlugin getInstance() {
        return instance;
    }
    @Override
    public void onDisable() {
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            if(byKey.containsKey(humpHitEnchant.getKey())) {
                byKey.remove(humpHitEnchant.getKey());
            }
            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            if(byName.containsKey(humpHitEnchant.getName())) {
                byName.remove(humpHitEnchant.getName());
            }
        } catch (Exception ignored) { }
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            if(byKey.containsKey(drillEnchant.getKey())) {
                byKey.remove(drillEnchant.getKey());
            }
            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            if(byName.containsKey(drillEnchant.getName())) {
                byName.remove(drillEnchant.getName());
            }
        } catch (Exception ignored) { }
    }
}
