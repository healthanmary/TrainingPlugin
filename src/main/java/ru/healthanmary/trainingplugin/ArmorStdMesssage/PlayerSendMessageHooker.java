package ru.healthanmary.trainingplugin.ArmorStdMesssage;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerSendMessageHooker implements Listener {
    @EventHandler
    public void on(AsyncPlayerChatEvent e) {
        SpawnStand.spawnStand(e);
    }
}
