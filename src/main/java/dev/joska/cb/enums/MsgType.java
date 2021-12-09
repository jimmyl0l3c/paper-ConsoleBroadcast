package dev.joska.cb.enums;

public enum MsgType {
    SAY("cb.say"),
    WARNING("cb.warn"),
    INFO("cb.info");

    public final String permission;

    MsgType(String s) {
        this.permission = s;
    }
}
