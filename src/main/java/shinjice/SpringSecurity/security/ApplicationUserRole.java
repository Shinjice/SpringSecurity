package shinjice.SpringSecurity.security;

import com.google.common.collect.Sets;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static shinjice.SpringSecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    KLANT(Sets.newHashSet()),
    VERKOPER(Sets.newHashSet(KLANT_READ, KLANT_WRITE, VERKOPER_WRITE, VERKOPER_READ)),
    ADMINISTRATIE(Sets.newHashSet(KLANT_READ, VERKOPER_READ));

    private final Set<ApplicationUserPermission> permission;


    ApplicationUserRole(Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }

    public Set<ApplicationUserPermission> getPermission() {
        return permission;
    }

    public Set<SimpleGrantedAuthority> getGrandedAuthority() {
        Set<SimpleGrantedAuthority> permissions = getPermission().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
