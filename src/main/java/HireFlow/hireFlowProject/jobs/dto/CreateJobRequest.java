package HireFlow.hireFlowProject.jobs.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class CreateJobRequest {

    @NotBlank
    private String companyId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private List<String> skills;

    private String location;

    private String jobType;

    private String experienceLevel;

    private Integer salaryMin;

    private Integer salaryMax;

    private Integer expiryDays;

    
    
	public CreateJobRequest(@NotBlank String companyId, @NotBlank String title, @NotBlank String description,
			List<String> skills, String location, String jobType, String experienceLevel, Integer salaryMin,
			Integer salaryMax, Integer expiryDays) {
		super();
		this.companyId = companyId;
		this.title = title;
		this.description = description;
		this.skills = skills;
		this.location = location;
		this.jobType = jobType;
		this.experienceLevel = experienceLevel;
		this.salaryMin = salaryMin;
		this.salaryMax = salaryMax;
		this.expiryDays = expiryDays;
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

	public Integer getExpiryDays() {
		return expiryDays;
	}

	public void setExpiryDays(Integer expiryDays) {
		this.expiryDays = expiryDays;
	}
    
    
}