package authSatellizer.bootstrap;

import authSatellizer.domains.Role;
import authSatellizer.domains.User;
import authSatellizer.services.RoleService;
import authSatellizer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by LaurentF on 07/04/2017.
 */
@Component
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();
        loadRoles();
        assignRoleToUser();
    }

    private void assignRoleToUser() {
        Role ADMIN = roleService.findOneByRole("ADMIN");
        Role USER = roleService.findOneByRole("USER");

        User lfaivre = userService.findByUsername("lfaivre");
        User lsouby = userService.findByUsername("lsouby");

        lfaivre.addRole(USER);
        lfaivre = userService.saveOrUpdate(lfaivre);
        lfaivre.addRole(ADMIN);
        lfaivre = userService.saveOrUpdate(lfaivre);

        lsouby.addRole(USER);
        lsouby = userService.saveOrUpdate(lsouby);
    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("USER");

        roleService.saveOrUpdate(role);

        Role role1 = new Role();
        role1.setRole("ADMIN");

        roleService.saveOrUpdate(role1);
    }

    private void loadUsers() {
        User user = new User();
        user.setUsername("lfaivre");
        user.setPassword("password");
        user.setFirstName("Laurent");
        user.setLastName("FAIVRE");

        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String date1 = "24/06/1989";

        try {
            date = simpleDateFormat.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setDateOfBirth(date);
        user.setEmail("laurent.faivre@gmail.com");
        user.setPhoneNumber("02102862167");
        user.setAddress("5/20 hay street, Wellington");

        userService.saveOrUpdate(user);

        User user1 = new User();
        user1.setUsername("lsouby");
        user1.setPassword("password");
        user1.setFirstName("Lea");
        user1.setLastName("Souby");


        String date2 = "27/09/1990";

        try {
            date = simpleDateFormat.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user1.setDateOfBirth(date);
        user1.setEmail("toto.lolo@toto.ch");
        user1.setPhoneNumber("0123456789");
        user1.setAddress("5/20 hay street, Wellington");

        userService.saveOrUpdate(user1);
    }


}
