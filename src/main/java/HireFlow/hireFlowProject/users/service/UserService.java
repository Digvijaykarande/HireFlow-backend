package HireFlow.hireFlowProject.users.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import HireFlow.hireFlowProject.common.exception.EmailAlreadyExistsException;
import HireFlow.hireFlowProject.common.exception.UserNotFoundException;
import HireFlow.hireFlowProject.users.dto.UpdateProfileRequest;
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
	
	public List<User> findByRole(String role) {
		return userRepo.findByRole(role);
	}
	
	public User createUser(User user) {

	    if (userRepo.findByEmail(user.getEmail()).isPresent()) {
	        throw new EmailAlreadyExistsException("Email already exists");
	    }

	    user.setVerified(false);

	    user.setProfileUrl("");
	    user.setProfilePublicId("");

	    user.setResumeUrl("");
	    user.setResumePublicId("");

	    user.setPhone("");
	    user.setHeading("");
	    user.setLocation("");

	    user.setCreatedAt(LocalDateTime.now());
	    user.setUpdatedAt(LocalDateTime.now());

	    return userRepo.save(user);
	}
	
	public User getCurrentUser() {

	    String email =
	            SecurityContextHolder
	                    .getContext()
	                    .getAuthentication()
	                    .getName();

	    return userRepo.findByEmail(email)
	            .orElseThrow(() ->
	                    new UserNotFoundException(
	                            "User not found"));
	}
	
	public User updateProfile(
	        UpdateProfileRequest request) {

	    User user = getCurrentUser();

	    user.setName(request.getName());
	    user.setPhone(request.getPhone());
	    user.setHeading(request.getHeadline());
	    user.setLocation(request.getLocation());

	    user.setUpdatedAt(LocalDateTime.now());

	    return userRepo.save(user);
	}
	
	public User updateUser(User user) {
		user.setUpdatedAt(LocalDateTime.now());

		return userRepo.save(user);
	}
	
	public void deleteUser(String user) {
		userRepo.deleteById(user);
	}
	
	public User updateResume(
	        String resumeUrl,
	        String publicId) {

	    User user = getCurrentUser();

	    user.setResumeUrl(resumeUrl);
	    user.setResumePublicId(publicId);
	    user.setUpdatedAt(LocalDateTime.now());

	    return userRepo.save(user);
	}
	
	
	public User updateProfilePicture(
	        String profileUrl,
	        String publicId) {

	    User user = getCurrentUser();

	    user.setProfileUrl(profileUrl);
	    user.setProfilePublicId(publicId);
	    user.setUpdatedAt(LocalDateTime.now());

	    return userRepo.save(user);
	}

}
