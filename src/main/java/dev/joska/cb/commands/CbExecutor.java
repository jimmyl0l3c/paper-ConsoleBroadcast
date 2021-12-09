package dev.joska.cb.commands;

import dev.joska.cb.ConsoleBroadcast;
import dev.joska.cb.LocaleService;
import dev.joska.cb.enums.locale.LocaleError;
import dev.joska.cb.enums.locale.LocaleInfo;
import dev.joska.cb.exceptions.InvalidLocaleException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class CbExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        LocaleService locale = ConsoleBroadcast.getInstance().getLocale();

        if (args.length == 0) {
            sender.sendMessage("§2ConsoleBroadcast plugin by §6" + ConsoleBroadcast.getInstance().getDescription().getAuthors().get(0));
        } else if (args.length == 1 && args[0].equals("reload")) {
            if (sender.isOp() || sender.hasPermission("cb.reload") || sender instanceof ConsoleCommandSender) {
                reloadPlugin(sender, locale);
            } else {
                sender.sendMessage(locale.getError(LocaleError.MISSING_PERMISSION));
            }
        } else {
            sender.sendMessage(locale.getError(LocaleError.INVALID_COMMAND));
        }
        return true;
    }

    private void reloadPlugin(CommandSender sender, LocaleService locale) {
        ConsoleBroadcast.getInstance().reloadConfig();
        sender.sendMessage(locale.getInfo(LocaleInfo.CONFIG_RELOADED));
        try {
            ConsoleBroadcast.getInstance().loadLocale(false);
            sender.sendMessage(locale.getInfo(LocaleInfo.LOCALE_RELOADED));
        } catch (InvalidLocaleException e) {
            sender.sendMessage(String.format("Error: %s", e.getMessage()));
        }
    }
}
