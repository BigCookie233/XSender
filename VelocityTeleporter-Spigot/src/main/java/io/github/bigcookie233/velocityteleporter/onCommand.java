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
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You must be a player to run this command.");
            return false;
        }

        if (strings.length != 1) {
            return false;
        }

        VelocityTeleporter.getInstance().sendMsg((Player) commandSender, strings[0]);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return new ArrayList<>();
    }

    private List<String> getMatchedStrings(String string, String[] strings) {
        ArrayList<String> ret = new ArrayList<>();
        for (String s: strings) {
            if (compareStringIgnoreCase(s, string)) {
                ret.add(s);
            }
        }
        return ret;
    }

    private boolean compareStringIgnoreCase(String s1, String s2) {
        if (s2.length() > s1.length()) return false;
        return s1.substring(0, s2.length()).equalsIgnoreCase(s2);
    }
}
