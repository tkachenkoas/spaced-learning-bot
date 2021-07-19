package com.atstudio.spacedlearningbot.service;

public class CurrentOwnerIdHolder {
    private static final ThreadLocal<String> OWNER_ID_HOLDER = new ThreadLocal<>();

    public static String getCurrentOwnerId() {
        return OWNER_ID_HOLDER.get();
    }

    public static void setCurrentOwnerId(String ownerId) {
        OWNER_ID_HOLDER.set(ownerId);
    }

    public static void clear() {
        OWNER_ID_HOLDER.remove();
    }

}
