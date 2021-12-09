package dev.joska.cb;

import dev.joska.cb.commands.CbExecutor;
import dev.joska.cb.commands.MessageCommands;
import dev.joska.cb.enums.MsgType;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConsoleBroadcast extends JavaPlugin {
    private static ConsoleBroadcast INSTANCE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;
        this.saveDefaultConfig();

        getCommand("cb").setExecutor(new CbExecutor());

        getCommand("s").setExecutor(new MessageCommands(MsgType.SAY));
        getCommand("info").setExecutor(new MessageCommands(MsgType.INFO));
        getCommand("warn").setExecutor(new MessageCommands(MsgType.WARNING));

        getLogger().info(" enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getScheduler().cancelTasks(this);
        getLogger().info(" disabled!");
    }

    public static ConsoleBroadcast getInstance() {
        return INSTANCE;
    }
}
