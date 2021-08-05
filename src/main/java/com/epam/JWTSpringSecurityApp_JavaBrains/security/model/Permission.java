package com.epam.JWTSpringSecurityApp_JavaBrains.security.model;

public enum Permission {
    ORDERS_READ("orders:read"),
    ORDERS_WRITE("orders:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
