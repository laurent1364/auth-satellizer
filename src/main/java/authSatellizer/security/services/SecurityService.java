package authSatellizer.security.services;

import org.springframework.security.core.Authentication;

/**
 * Created by LaurentF on 07/04/2017.
 */
public interface SecurityService {

    Boolean hasProtectedAccess();
    Boolean isUser(Authentication auth);
    Boolean isAdmin(Authentication auth);
}
