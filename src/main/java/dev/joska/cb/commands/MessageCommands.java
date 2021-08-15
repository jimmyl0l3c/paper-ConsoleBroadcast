package dev.joska.cb.commands;

import dev.joska.cb.ConsoleBroadcast;
import dev.joska.cb.enums.MsgType;
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
        if (!(sender instanceof ConsoleCommandSender || sender.hasPermission("cb.god"))) {
            sender.sendMessage("Missing required permission.");
            return  true;
        }

        if (args.length == 0) {
            sender.sendMessage("Missing argument.");
        } else {
            String prefix = "";
            FileConfiguration config = ConsoleBroadcast.getInstance().getConfig();
            switch (this.messageType) {
                case SAY -> prefix = config.getString("say-prefix");
                case INFO -> prefix = config.getString("info-prefix");
                case WARNING -> prefix = config.getString("warn-prefix");
            }

            if (prefix == null) {
                sender.sendMessage("§cPlugin error: Config is invalid.");
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
