package dev.joska.cb.enums.locale;

public enum LocaleError implements LocaleString{
    MISSING_PERMISSION("permission"),
    INVALID_COMMAND("invalid-cmd"),
    INVALID_ARGUMENT("invalid-argument"),
    MISSING_ARGUMENT("missing-argument"),
    INVALID_CONFIG("config-error");

    private final String path;

    LocaleError(String s) {
        this.path = s;
    }

    @Override
    public String getPath() {
        return this.path;
    }
}
