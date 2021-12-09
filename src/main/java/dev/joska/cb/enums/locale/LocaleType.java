package dev.joska.cb.enums.locale;

public enum LocaleType {
    INFO("info"),
    WARNING("warnings"),
    ERROR("errors");

    private final String path;

    LocaleType(String s) {
        this.path = s;
    }

    public String getPath() {
        return this.path;
    }
}
