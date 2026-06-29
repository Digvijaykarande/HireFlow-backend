package HireFlow.hireFlowProject.cloudinary.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import HireFlow.hireFlowProject.cloudinary.dto.UploadResult;
import HireFlow.hireFlowProject.cloudinary.service.CloudinaryService;
import HireFlow.hireFlowProject.users.model.User;
import HireFlow.hireFlowProject.users.service.UserService;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

	private final CloudinaryService cloudinaryService;
	private final UserService userService;

	public UploadController(CloudinaryService cloudinaryService, UserService userService) {

		this.cloudinaryService = cloudinaryService;
		this.userService = userService;
	}

	@PostMapping(value = "/resume", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@PreAuthorize("hasRole('CANDIDATE')")
	public User uploadResume(@RequestParam("file") MultipartFile file) throws IOException {

		User currentUser =
		        userService.getCurrentUser();

		if(currentUser.getResumePublicId()!=null &&
		   !currentUser.getResumePublicId().isBlank()){

		    cloudinaryService.deleteFile(
		            currentUser.getResumePublicId());
		}

		UploadResult result =
		        cloudinaryService.uploadResume(file);

		return userService.updateResume(
		        result.getSecureUrl(),
		        result.getPublicId());
	}

	@PostMapping(value = "/profile-picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@PreAuthorize("hasRole('CANDIDATE')")
	public User uploadProfilePicture(@RequestParam("file") MultipartFile file) throws IOException {

		User currentUser =
		        userService.getCurrentUser();

		if(currentUser.getProfilePublicId()!=null &&
		   !currentUser.getProfilePublicId().isBlank()){

		    cloudinaryService.deleteFile(
		            currentUser.getProfilePublicId());
		}

		UploadResult result =
		        cloudinaryService.uploadProfilePicture(file);

		return userService.updateProfilePicture(
		        result.getSecureUrl(),
		        result.getPublicId());
	}

	@PreAuthorize("hasRole('RECRUITER')")
	@PostMapping(value = "/company-logo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Map<String, UploadResult> uploadCompanyLogo(@RequestParam("file") MultipartFile file) throws IOException {

		UploadResult url = cloudinaryService.uploadCompanyLogo(file);

		return Map.of("logoUrl", url);
	}
}