package shinjice.SpringSecurity.security;

public enum ApplicationUserPermission {
    VERKOPER_READ("verkoper:read"),
    VERKOPER_WRITE("verkoper:write"),
    KLANT_READ("klant:read"),
    KLANT_WRITE("klant:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
    public String getPermission(){
        return permission;
    }

}
