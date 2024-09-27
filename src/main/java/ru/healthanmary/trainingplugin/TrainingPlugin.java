package ru.healthanmary.trainingplugin;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;
import ru.healthanmary.trainingplugin.EffectMenu.ApplyEffectOnClick;
import ru.healthanmary.trainingplugin.EffectMenu.EffectMenuCommandExecutor;
import ru.healthanmary.trainingplugin.EffectMenu.ForbidGetItem;
import ru.healthanmary.trainingplugin.ArmorStdMesssage.PlayerSendMessageHooker;
import ru.healthanmary.trainingplugin.KillPhantomEnchant.GiveChestplate;
import ru.healthanmary.trainingplugin.KillPhantomEnchant.HumpHitEnchant;
import ru.healthanmary.trainingplugin.KillPhantomEnchant.PhantomDamageListener;
import ru.healthanmary.trainingplugin.Other.GapleCooldown;
import ru.healthanmary.trainingplugin.ShulkerAnimation.SpawnCommnd;

import java.lang.reflect.Field;
import java.util.HashMap;

public final class TrainingPlugin extends JavaPlugin {
    private static TrainingPlugin instance;
    public static HumpHitEnchant humpHitEnchant;
    @Override
    public void onEnable() {
        instance = this;
        humpHitEnchant = new HumpHitEnchant("humphit");
        registerEnchantment(humpHitEnchant);
        getCommand("effectmenu").setExecutor(new EffectMenuCommandExecutor());
        getCommand("standspawn").setExecutor(new SpawnCommnd());
        getCommand("getchestplate").setExecutor(new GiveChestplate());
        getServer().getPluginManager().registerEvents(new PhantomDamageListener(), this);
        getServer().getPluginManager().registerEvents(new ForbidGetItem(), this);
        getServer().getPluginManager().registerEvents(new ApplyEffectOnClick(), this);
        getServer().getPluginManager().registerEvents(new GapleCooldown(), this);
        getServer().getPluginManager().registerEvents(new PlayerSendMessageHooker(), this);
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
    }
}
