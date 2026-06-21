package HireFlow.hireFlowProject.users.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import HireFlow.hireFlowProject.common.exception.EmailAlreadyExistsException;
import HireFlow.hireFlowProject.common.exception.UserNotFoundException;
import HireFlow.hireFlowProject.users.model.User;
import HireFlow.hireFlowProject.users.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	public List<User> getUsers(){
    	return userRepo.findAll();
    }
	
	public User getUserById(String userId) {
		return userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("user not found"));
	}
	
	public void createUser(User user) {
		user.setCreatedAt(LocalDateTime.now());
		if(userRepo.findByEmail(user.getEmail()).isPresent()) {
	        throw new EmailAlreadyExistsException(
	                "Email already exists");
	    }

	}
	
	public void updateUser(User user) {
		userRepo.save(user);
	}
	
	public void deleteUser(String user) {
		userRepo.deleteById(user);
	}

}
