package dev.joska.cb.enums.locale;

public enum LocaleWarning implements LocaleString{
    DEPRECATED_REMOVED("deprecated-removed"),
    DEPRECATED_REPLACED("deprecated-s");

    private final String path;

    LocaleWarning(String s) {
        this.path = s;
    }

    @Override
    public String getPath() {
        return this.path;
    }
}
