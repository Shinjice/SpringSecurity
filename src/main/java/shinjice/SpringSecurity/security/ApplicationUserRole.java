package shinjice.SpringSecurity.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static shinjice.SpringSecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    KLANT(Sets.newHashSet()),
    VERKOPER(Sets.newHashSet(KLANT_READ, KLANT_WRITE, VERKOPER_WRITE, VERKOPER_READ)),
    ADMINISTRATIE(Sets.newHashSet(KLANT_READ, KLANT_WRITE));

    private final Set<ApplicationUserPermission> permission;


    ApplicationUserRole(Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }

    public Set<ApplicationUserPermission> getPermission() {
        return permission;
    }
}
