package authSatellizer.services;

import authSatellizer.domains.User;

/**
 * Created by LaurentF on 07/04/2017.
 */
public interface UserService extends CrudService<User>{

    User findByUsername(String username);

}
