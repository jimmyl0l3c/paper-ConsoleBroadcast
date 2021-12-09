package dev.joska.cb;

import dev.joska.cb.commands.CbExecutor;
import dev.joska.cb.commands.MessageCommands;
import dev.joska.cb.enums.MsgType;
import dev.joska.cb.exceptions.InvalidLocaleException;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ConsoleBroadcast extends JavaPlugin {
    private static ConsoleBroadcast INSTANCE;

    private LocaleService locale;

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;
        this.saveDefaultConfig();

        try {
            loadLocale(false);
        } catch (InvalidLocaleException e) {
            getLogger().severe(e.getMessage());
            try {
                loadLocale(true);
            } catch (InvalidLocaleException ex) {
                getLogger().severe(e.getMessage());
            }
        }

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

    public LocaleService getLocale() {
        return this.locale;
    }

    public void loadLocale(boolean replace) throws InvalidLocaleException {
        String localePath = String.format("locale/%s.yml", getConfig().getString("locale"));
        File localeFile = new File(getDataFolder(), localePath);
        if (!localeFile.exists()) {
            saveResource(localePath, replace);
        }
        FileConfiguration localeCfg = YamlConfiguration.loadConfiguration(localeFile);
        if (this.locale == null)
            this.locale = new LocaleService(localeCfg);
        else
            this.locale.setLocaleCfg(localeCfg);

        setCmdDescription(getCommand("cb"));
        setCmdDescription(getCommand("cb reload"));
        setCmdDescription(getCommand("s"));
        setCmdDescription(getCommand("info"));
        setCmdDescription(getCommand("warn"));
    }

    private void setCmdDescription(PluginCommand cmd) {
        cmd.setDescription(locale.getCommandDescription(cmd.getName()));
    }

    public static ConsoleBroadcast getInstance() {
        return INSTANCE;
    }
}
