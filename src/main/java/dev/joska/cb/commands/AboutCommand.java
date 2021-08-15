package dev.joska.cb.commands;

import dev.joska.cb.ConsoleBroadcast;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AboutCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§2ConsoleBroadcast plugin by §6" + ConsoleBroadcast.getInstance().getDescription().getAuthors().get(0));
        } else if (args.length == 1 && args[0].equals("reload")) {
            ConsoleBroadcast.getInstance().reloadConfig();
            sender.sendMessage("Config reloaded.");
        } else {
            sender.sendMessage("Invalid command.");
        }
        return true;
    }
}
