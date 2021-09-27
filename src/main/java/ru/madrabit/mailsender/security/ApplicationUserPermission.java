package ru.madrabit.mailsender.security;

public enum ApplicationUserPermission {
    MANAGER_READ("manager:read"),
    ADMIN_READ("manager:read");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
