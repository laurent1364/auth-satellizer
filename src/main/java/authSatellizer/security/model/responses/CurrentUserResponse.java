package authSatellizer.security.model.responses;

import authSatellizer.domains.Role;
import authSatellizer.domains.User;
import authSatellizer.security.model.ModelBase;

/**
 * Created by LaurentF on 06/04/2017.
 */
public class CurrentUserResponse extends ModelBase {


    private String username;
    private Boolean isAdmin;

    public CurrentUserResponse() {super(); }

    public CurrentUserResponse(User user) {
        this.setUsername(user.getUsername());
        this.setAdmin(isAdmin(user));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    private boolean isAdmin(User user) {
        for(Role role : user.getRoles()){
            if(role.getRole().equals("ADMIN")){
                return true;
            }
        }

        return false;
    }
}
