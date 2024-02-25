package io.github.bigcookie233.velocityteleporter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class onCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED + "You must be a player to run this command.");
                return false;
            }
            VelocityTeleporter.getInstance().sendMsg((Player) commandSender, strings[0]);
            return true;
        } else if (strings.length == 2) {
            Player player = VelocityTeleporter.getInstance().getServer().getPlayer(strings[1]);
            if (player == null) {
                commandSender.sendMessage(ChatColor.RED + "Player not found.");
                return false;
            }
            VelocityTeleporter.getInstance().sendMsg(player, strings[0]);
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return new ArrayList<>();
    }
}
