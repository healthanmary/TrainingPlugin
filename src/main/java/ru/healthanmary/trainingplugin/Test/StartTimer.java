package ru.healthanmary.trainingplugin.Test;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StartTimer {
    private static int seconds = 5;
    public static void startTimer(Player p) {
        int taskId = Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                if (!p.isOnline()) {
                    Bukkit.getScheduler().cancelTask();
                    return; }
                p.sendMessage("Алмаз будет выдан через " + seconds);
                seconds--;

                if (seconds < 1) {
                    p.sendMessage("Алмаз выдан!");
                    Bukkit.getScheduler().cancelTask(ta);
                }
            }
        }, 0L, 20L).getTaskId();
    }
}
