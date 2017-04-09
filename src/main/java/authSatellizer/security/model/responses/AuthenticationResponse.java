package authSatellizer.security.model.responses;


import authSatellizer.domains.User;
import authSatellizer.security.model.ModelBase;

/**
 * Created by LaurentF on 14/03/2017.
 */
public class AuthenticationResponse extends ModelBase {

    private static final long serialVersionUID = -6624726180748515507L;

    private String token;

    public AuthenticationResponse() {
        super();
    }

    public AuthenticationResponse(String token) {
        this.setToken(token);

    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}