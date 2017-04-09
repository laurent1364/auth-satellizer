package authSatellizer.repositories;

import authSatellizer.domains.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by LaurentF on 07/04/2017.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByRole(String role);
}
