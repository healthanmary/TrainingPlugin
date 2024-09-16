package ru.healthanmary.trainingplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class TrainingPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("effectmenu").setExecutor(new EffectMenuCommandExecutor());
        getServer().getPluginManager().registerEvents(new ForbidGetItem(), this);
        getServer().getPluginManager().registerEvents(new ApplyEffectOnClick(), this);
        getServer().getPluginManager().registerEvents(new GapleCooldown(), this);
    }

    @Override
    public void onDisable() {
    }
}
