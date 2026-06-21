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
	
	@Indexed(unique = true)
	private String email;
	
	@Indexed
	private String role;
	
	private boolean isVerified;
	
	private String profileUrl;
	
	private LocalDateTime createdAt;
	
	public User() {
		
	}
	
	public User(String id, String name, String password, String email, String role, boolean isVerified,
			String profileUrl, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
		this.isVerified = isVerified;
		this.profileUrl = profileUrl;
		this.createdAt=createdAt;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", role=" + role
				+ ", isVerified=" + isVerified + ", profileUrl=" + profileUrl + ", createdAt=" + createdAt + "]";
	}
	
}
