package dev.joska.cb.enums.locale;

public enum LocaleInfo implements LocaleString{
    CONFIG_RELOADED("cfg-reload"),
    LOCALE_RELOADED("locale-reload");

    private final String path;

    LocaleInfo(String s) {
        this.path = s;
    }

    @Override
    public String getPath() {
        return this.path;
    }
}
