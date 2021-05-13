package com.example.projectmgmt.controllers;

import com.example.projectmgmt.models.User;
import com.example.projectmgmt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    // RETRIEVES A LIST OF ALL USERS IN APPLICATION (DELETE AFTER PROJECT COMPLETION)
    // http://localhost:2021/users/users
    @GetMapping(value = "/users")
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAllUsers();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    // RETRIEVES INFO ABOUT A SPECIFIC USER (BY ID)
    // http://localhost:2021/users/user/{USERID}
    @GetMapping(value = "/user/{userid}", produces = {"application/json"})
    public ResponseEntity<?> findUserById(
            @PathVariable long userid)
    {
        User rtnUser = userService.findUserById(userid);
        return new ResponseEntity<>(rtnUser, HttpStatus.OK);
    }

    // POSTS A NEW USER TO DATABASE
    // http://localhost:2021/users/user
    @PostMapping(value = "/user", consumes = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newUser)
    {
        newUser.setUserid(0);
        newUser = userService.save(newUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newUser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);
        return new ResponseEntity<>(newUser, responseHeaders, HttpStatus.CREATED);
    }

    // UPDATES AN EXISTING USER (BY ID)
    // http://localhost:2021/users/user/{USERID}
    @PutMapping(value = "/user/{userid}", consumes = {"application/json"})
    public ResponseEntity<?> updateFullUser(
            @Valid
            @RequestBody
                    User updateUser,
            @PathVariable
                    long userid
    ){
        updateUser.setUserid(userid);
        userService.save(updateUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //RETRIEVES CURRENT USERS'S INFO
    // http://localhost:2021/users/getuserinfo
    @GetMapping(value = "/getuserinfo", produces = {"application/json"})
    public ResponseEntity<?> getCurrentUserInfo()
    {
        String emailaddress = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =  userService.findByEmailaddress(emailaddress);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
