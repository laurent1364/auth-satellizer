package authSatellizer.converters;

import authSatellizer.domains.User;
import authSatellizer.security.model.UserDemo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Mirage on 24/02/2017.
 */
@Component
public class UserToUserDetails implements Converter<User, UserDetails> {

    @Override
    public UserDetails convert(User user) {

        UserDemo userDetails = new UserDemo();

        if (user != null) {
            userDetails.setUsername(user.getUsername());
            userDetails.setPassword(user.getEncryptedPassword());
            userDetails.setEnabled(user.getEnabled());

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            });

            userDetails.setAuthorities(authorities);
        }

        return userDetails;


    }
}
