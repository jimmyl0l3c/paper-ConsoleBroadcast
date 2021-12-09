package dev.joska.cb;

import dev.joska.cb.enums.locale.*;
import dev.joska.cb.exceptions.InvalidLocaleException;
import org.bukkit.configuration.file.FileConfiguration;

public class LocaleService {
    private FileConfiguration localeCfg;

    public LocaleService(FileConfiguration localeCfg) throws InvalidLocaleException {
        setLocaleCfg(localeCfg);
    }

    public String getError(LocaleError error) {
        return getMessage(LocaleType.ERROR, error);
    }

    public String getWarning(LocaleWarning warning) {
        return getMessage(LocaleType.WARNING, warning);
    }

    public String getInfo(LocaleInfo info) {
        return getMessage(LocaleType.INFO, info);
    }

    private String getMessage(LocaleType type, LocaleString msg) {
        String title = null;
        if (type != LocaleType.INFO) {
            title = this.localeCfg.getString(String.format("%s.title", type.getPath()));
            if (title == null)
                title = type.getPath();
        }

        String message = this.localeCfg.getString(String.format("%s.%s", type.getPath(), msg.getPath()));
        if (message == null) {
            message = String.format("%s. (UNABLE TO LOAD LOCALE)", msg.getPath());
        }

        if (title == null) {
            return message;
        }
        return String.format("%s: %s", title, message);
    }

    public String getCommandDescription(String path) {
        String description = this.localeCfg.getString(String.format("commands.%s", path));
        if (description == null) {
            description = path;
        }
        return description;
    }

    public void setLocaleCfg(FileConfiguration localeCfg) throws InvalidLocaleException {
        if (localeCfg == null)
            throw new InvalidLocaleException("Locale is null");
        // TODO: add validity check
        this.localeCfg = localeCfg;
    }
}
