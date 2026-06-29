package HireFlow.hireFlowProject.users.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	@Id
	private String id;
	
	private String name;
	
	private String password;
	
	private String phone;
	private String resumeUrl;
	
	@Indexed
	private String heading;
	
	@Indexed
	private String location;
	private String resumePublicId;
	
	@Indexed(unique = true)
	private String email;
	
	@Indexed
	private String role;
	
	private boolean isVerified;
	
	private String profileUrl;
	
	private String profilePublicId;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	
	public User() {
		
	}

	public User(String id, String name, String password, String phone, String resumeUrl, String heading,
			String location, String resumePublicId, String email, String role, boolean isVerified, String profileUrl,
			String profilePublicId, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.resumeUrl = resumeUrl;
		this.heading = heading;
		this.location = location;
		this.resumePublicId = resumePublicId;
		this.email = email;
		this.role = role;
		this.isVerified = isVerified;
		this.profileUrl = profileUrl;
		this.profilePublicId = profilePublicId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isVerified() {
		return isVerified;
	}
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	public String getProfileUrl() {
		return profileUrl;
	}
	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getResumeUrl() {
		return resumeUrl;
	}

	public void setResumeUrl(String resumeUrl) {
		this.resumeUrl = resumeUrl;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getResumePublicId() {
		return resumePublicId;
	}

	public void setResumePublicId(String resumePublicId) {
		this.resumePublicId = resumePublicId;
	}

	public String getProfilePublicId() {
		return profilePublicId;
	}

	public void setProfilePublicId(String profilePublicId) {
		this.profilePublicId = profilePublicId;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", role=" + role
				+ ", isVerified=" + isVerified + ", profileUrl=" + profileUrl + ", createdAt=" + createdAt + "]";
	}
	
}
