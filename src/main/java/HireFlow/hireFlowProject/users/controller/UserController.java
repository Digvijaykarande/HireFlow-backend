package HireFlow.hireFlowProject.users.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import HireFlow.hireFlowProject.users.dto.CreateUserRequest;
import HireFlow.hireFlowProject.users.dto.UpdateProfileRequest;
import HireFlow.hireFlowProject.users.model.User;
import HireFlow.hireFlowProject.users.service.UserService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello this is Users Controller";
    }
    
    @GetMapping("/users/me")
    public User getCurrentUser() {

        return service.getCurrentUser();
    }
    
    @PutMapping("/users/profile")
    public User updateProfile(
            @Valid
            @RequestBody
            UpdateProfileRequest request){

        return service.updateProfile(request);
    }
    
    
    @GetMapping("/users")
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(
            @PathVariable String userId) {

        return service.getUserById(userId);
    }

    @PostMapping("/users")
    public User createUser(
            @Valid @RequestBody CreateUserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        return service.createUser(user);
    }

    @PutMapping("/users")
    public User updateUser(
            @RequestBody User user) {

        return service.updateUser(user);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(
            @PathVariable String userId) {

        service.deleteUser(userId);

        return "User deleted successfully";
    }

}