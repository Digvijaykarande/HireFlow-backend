package HireFlow.hireFlowProject.jobs.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jobs")
public class Job {

    @Id
    private String id;

    @Indexed
    private String companyId;

    private String title;

    @TextIndexed
    private String description;

    private List<String> skills;

    @Indexed
    private String location;

    private String jobType;

    private String experienceLevel;

    private Integer salaryMin;

    private Integer salaryMax;

    @Indexed
    private String status;

    private String createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    
    public Job() {
    	
    }
	public Job(String id, String companyId, String title, String description, List<String> skills, String location,
			String jobType, String experienceLevel, Integer salaryMin, Integer salaryMax, String status,
			String createdBy, LocalDateTime createdAt, LocalDateTime expiresAt) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.title = title;
		this.description = description;
		this.skills = skills;
		this.location = location;
		this.jobType = jobType;
		this.experienceLevel = experienceLevel;
		this.salaryMin = salaryMin;
		this.salaryMax = salaryMax;
		this.status = status;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public Integer getSalaryMin() {
		return salaryMin;
	}

	public void setSalaryMin(Integer salaryMin) {
		this.salaryMin = salaryMin;
	}

	public Integer getSalaryMax() {
		return salaryMax;
	}

	public void setSalaryMax(Integer salaryMax) {
		this.salaryMax = salaryMax;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}
    
    
}