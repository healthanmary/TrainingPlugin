package ru.healthanmary.trainingplugin.EffecrMenu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EffectMenuCommandExecutor implements org.bukkit.command.CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("Только для игроков");
            return true; }
//        CreateMenu.fillInventory();
        CreateMenu.openMenu(p);
        return true;
    }
}
