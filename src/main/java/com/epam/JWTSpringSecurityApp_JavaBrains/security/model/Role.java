package com.epam.JWTSpringSecurityApp_JavaBrains.security.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(Permission.ORDERS_READ, Permission.ORDERS_WRITE)),
    WORKER(Set.of(Permission.ORDERS_READ, Permission.ORDERS_WRITE)),
    CUSTOMER(Set.of(Permission.ORDERS_READ, Permission.ORDERS_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
