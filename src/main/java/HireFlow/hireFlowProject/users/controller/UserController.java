package HireFlow.hireFlowProject.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HireFlow.hireFlowProject.users.dto.CreateUserRequest;
import HireFlow.hireFlowProject.users.model.User;
import HireFlow.hireFlowProject.users.service.UserService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserService service;
	
	
    @GetMapping("/")
    public String hello() {
        return "hello this is users controller";
    }
    
    @GetMapping("/users")
    public List<User>getUsers(){
    	return service.getUsers();
    }
    
    @RequestMapping("/users/{userId}")
    public User getUserById(@PathVariable String userId) {
    	return service.getUserById(userId);
    }
    
    @PostMapping("/users")
    public void createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = new User();
        
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setVerified(false);

        service.createUser(user);
    }
    
    @PutMapping("/users")
    public void updateUser(@RequestBody User user) {
    	service.updateUser(user);
    }
    
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable String userId) {
    	service.deleteUser(userId);
    }
        
}