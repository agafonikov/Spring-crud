package userworker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import userworker.model.Role;
import userworker.model.User;
import userworker.service.UserService;

import java.util.*;

@RestController
@RequestMapping(value = "/api")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    List<User> getAll(){
        return userService.getAll();
    }


    @PostMapping(value = "/users")
    User newUser(@RequestBody User user){
        return userService.add(user);
    }

    @PutMapping("/users/")
    User updateUser(@RequestBody User userUpdated){
        User user = userService.getById(userUpdated.getId());
        user.setUsername(userUpdated.getUsername());
        user.setName(userUpdated.getName());
        user.setLastName(userUpdated.getLastName());
        user.setRoles((Set<Role>) userUpdated.getAuthorities());
        System.out.println(userUpdated.getLastName());
        return userService.update(user);
    }

    @DeleteMapping("/users/")
    User deleteUser(@RequestBody User user){
        return userService.delete(user);
    }
}
