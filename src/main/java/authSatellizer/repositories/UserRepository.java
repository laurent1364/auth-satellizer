package authSatellizer.repositories;

import authSatellizer.domains.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by LaurentF on 07/04/2017.
 */
public interface UserRepository extends CrudRepository<User, Integer>{

    User findByUsername(String username);

}
