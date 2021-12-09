package dev.joska.cb.commands;

import dev.joska.cb.ConsoleBroadcast;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class CbExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§2ConsoleBroadcast plugin by §6" + ConsoleBroadcast.getInstance().getDescription().getAuthors().get(0));
        } else if (args.length == 1 && args[0].equals("reload")) {
            if (sender.isOp() || sender.hasPermission("cb.reload") || sender instanceof ConsoleCommandSender) {
                reloadPlugin();
                sender.sendMessage("Config reloaded.");
            } else {
                sender.sendMessage("Insufficient permissions");
            }
        } else {
            sender.sendMessage("Invalid command.");
        }
        return true;
    }

    private void reloadPlugin() {
        ConsoleBroadcast.getInstance().reloadConfig();
        // reload locale, ...
    }
}
