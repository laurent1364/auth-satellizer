package authSatellizer.controllers;

import authSatellizer.domains.User;
import authSatellizer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by LaurentF on 07/04/2017.
 */
@RestController
@RequestMapping("/users")
public class UserRestController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> listAll(){
        return ResponseEntity.ok(userService.listAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody @Valid User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(userService.saveOrUpdate(user));
    }

    @RequestMapping(value = "/{userId}" , method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updatePassword(@RequestBody @Valid User user, @PathVariable Integer userId, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        if (userService.getById(userId) == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userService.saveOrUpdate(user));
    }

    @RequestMapping(value = "/{userId}" , method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer userId){

        if (userService.getById(userId) == null) {
            return ResponseEntity.notFound().build();
        }

        userService.delete(userId);

        if(userService.getById(userId) == null){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
