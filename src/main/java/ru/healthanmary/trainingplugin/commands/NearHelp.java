package ru.healthanmary.trainingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearHelp implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Доступно только игрокам!");
            return true;
        }
//        sender.sendMessage(ChatColor.AQUA + "Игроки неподалёку: ");
//        if (returnNearbyPlayers((Player) sender) == null) {
//            sender.sendMessage("Никого нема!");
//            return true;
//        }
//        for (Map.Entry<String, Double> entry : returnNearbyPlayers((Player) sender).entrySet()) {
//            sender.sendMessage(entry.getKey() + " (" + entry.getValue() + "м)");
//        }
        List<String> players = returnPlayers();
        if (players.isEmpty()) {
            sender.sendMessage("Никого нет, ты один(");
            return true;
        }
        sender.sendMessage("Все игроки: ");
        for (String nick : returnPlayers())
            sender.sendMessage(" -  " + nick);
        return true;
    }
    private HashMap<String, Double> returnNearbyPlayers(Player player) {
        HashMap<String, Double> map = new HashMap<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.equals(player)) {
                if (p.getLocation().distance(player.getLocation()) <= 200) {
                    double dist = p.getLocation().distance(player.getLocation());
                    map.put(p.getName(), dist);
                }
            }
        }
        return map;
    }
    private List<String> returnPlayers() {
        List<String> list = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            list.add(p.getName());
        }
        return list;
    }
}
