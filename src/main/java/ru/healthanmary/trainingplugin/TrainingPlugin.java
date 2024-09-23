package ru.healthanmary.trainingplugin;

import org.bukkit.plugin.java.JavaPlugin;
import ru.healthanmary.trainingplugin.EffecrMenu.ApplyEffectOnClick;
import ru.healthanmary.trainingplugin.EffecrMenu.EffectMenuCommandExecutor;
import ru.healthanmary.trainingplugin.EffecrMenu.ForbidGetItem;
import ru.healthanmary.trainingplugin.ArmorStdMesssage.PlayerSendMessageHooker;
import ru.healthanmary.trainingplugin.Other.GapleCooldown;
import ru.healthanmary.trainingplugin.ShulkerAnimation.SpawnCommnd;

public final class TrainingPlugin extends JavaPlugin {

    private static TrainingPlugin instance;
    @Override
    public void onEnable() {
        instance = this;
        getCommand("effectmenu").setExecutor(new EffectMenuCommandExecutor());
        getCommand("standspawn").setExecutor(new SpawnCommnd());
        getServer().getPluginManager().registerEvents(new ForbidGetItem(), this);
        getServer().getPluginManager().registerEvents(new ApplyEffectOnClick(), this);
        getServer().getPluginManager().registerEvents(new GapleCooldown(), this);
        getServer().getPluginManager().registerEvents(new PlayerSendMessageHooker(), this);
    }
    public static TrainingPlugin getInstance() {
        return instance;
    }
    @Override
    public void onDisable() {
    }
}
