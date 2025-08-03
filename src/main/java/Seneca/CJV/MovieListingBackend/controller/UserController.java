package Seneca.CJV.MovieListingBackend.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Seneca.CJV.MovieListingBackend.CustomizedResponse;
import Seneca.CJV.MovieListingBackend.model.User;
import Seneca.CJV.MovieListingBackend.service.UserService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint that will allow a user to register - sign up
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)  
    public ResponseEntity<CustomizedResponse<User>> registerNewUser(@Valid @RequestBody User newUser) {

        CustomizedResponse<User> customizedResponse;
        try{
            User registeredUser = userService.registerNewUser(newUser);
            customizedResponse = new CustomizedResponse<>("User registered successfully", Collections.singletonList(registeredUser));
            
        } catch (Exception e){
            customizedResponse = new CustomizedResponse<>(e.getMessage(), null);
            return new ResponseEntity<>(customizedResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customizedResponse, HttpStatus.OK);
    }

    // Endpoint to retrieves a specific User by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomizedResponse<User>> getUserById(@PathVariable("id") String id) {
        CustomizedResponse<User> customizedResponse;
        try {
            User user = userService.getUserById(id).orElseThrow(() -> new Exception("User not found"));
            customizedResponse = new CustomizedResponse<>("User with id " + id + " retrieved successfully", Collections.singletonList(user));
        } catch (Exception e){
            customizedResponse = new CustomizedResponse<>(e.getMessage(), null);
            return new ResponseEntity<>(customizedResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customizedResponse, HttpStatus.OK);
    }

    //end point to check if the email is already registered
    @GetMapping("/checkEmail")
    public ResponseEntity<String> checkEmail(@RequestParam("email") String email) {
        try {
            boolean isRegistered = userService.checkEmailExists(email);
            if (isRegistered) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already registered");
            } else {
                return ResponseEntity.ok("Email is available");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
