package authSatellizer.services;

import authSatellizer.domains.Role;

/**
 * Created by LaurentF on 07/04/2017.
 */
public interface RoleService extends CrudService<Role> {
    Role findOneByRole(String role);
}
