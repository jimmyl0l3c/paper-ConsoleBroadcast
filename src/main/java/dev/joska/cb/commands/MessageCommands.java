package dev.joska.cb.commands;

import dev.joska.cb.ConsoleBroadcast;
import dev.joska.cb.LocaleService;
import dev.joska.cb.enums.MsgType;
import dev.joska.cb.enums.locale.LocaleError;
import dev.joska.cb.enums.locale.LocaleWarning;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class MessageCommands implements CommandExecutor {

    private final MsgType messageType;

    public MessageCommands(MsgType type) {
        this.messageType = type;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        LocaleService locale = ConsoleBroadcast.getInstance().getLocale();

        if (sender.hasPermission("cb.god") && !sender.hasPermission(this.messageType.permission)) {
            sender.sendMessage(String.format(locale.getWarning(LocaleWarning.DEPRECATED_REPLACED), "cb.god", this.messageType.permission));
        }

        if (!(sender instanceof ConsoleCommandSender || sender.hasPermission("cb.god") || sender.hasPermission(this.messageType.permission))) {
            sender.sendMessage(locale.getError(LocaleError.MISSING_PERMISSION));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(locale.getError(LocaleError.MISSING_ARGUMENT));
            return false;
        } else {
            String prefix = "";
            FileConfiguration config = ConsoleBroadcast.getInstance().getConfig();
            switch (this.messageType) {
                case SAY -> prefix = config.getString("prefixes.say");
                case INFO -> prefix = config.getString("prefixes.info");
                case WARNING -> prefix = config.getString("prefixes.warn");
            }

            if (prefix == null) {
                sender.sendMessage(locale.getError(LocaleError.INVALID_CONFIG));
                return true;
            }

            StringBuilder message = new StringBuilder(prefix);
            for (String arg : args) {
                message.append(" ").append(arg);
            }

            sender.getServer().broadcast(Component.text(message.toString()));
        }
        return true;
    }
}
