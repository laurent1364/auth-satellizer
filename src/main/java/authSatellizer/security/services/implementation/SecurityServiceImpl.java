package authSatellizer.security.services.implementation;

import authSatellizer.security.services.SecurityService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by LaurentF on 07/04/2017.
 */
@Service
public class SecurityServiceImpl implements SecurityService {
    @Override
    public Boolean hasProtectedAccess() {
        return (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("USER")));
    }

    @Override
    public Boolean isUser(Authentication auth) {
        if(auth.isAuthenticated()){
            return auth.getAuthorities().contains(new SimpleGrantedAuthority("USER"));
        }
        return false;
    }

    @Override
    public Boolean isAdmin(Authentication auth) {
        if(auth.isAuthenticated()){
            return auth.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
        }
        return false;
    }
}
