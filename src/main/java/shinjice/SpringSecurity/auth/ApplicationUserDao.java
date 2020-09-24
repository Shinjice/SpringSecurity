package shinjice.SpringSecurity.auth;

import java.util.Optional;

interface ApplicationUserDao {

    public Optional<ApplicationUser> selectApplicationByusername(String username);

}
