package HireFlow.hireFlowProject.companies.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateCompanyRequest {

    @NotBlank
    private String name;

    private String logoUrl;

    private String description;

    private String website;

    private String industry;

    private Integer companySize;

    private String location;

	public CreateCompanyRequest(@NotBlank String name, String logoUrl, String description, String website,
			String industry, Integer companySize, String location) {
		super();
		this.name = name;
		this.logoUrl = logoUrl;
		this.description = description;
		this.website = website;
		this.industry = industry;
		this.companySize = companySize;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Integer getCompanySize() {
		return companySize;
	}

	public void setCompanySize(Integer companySize) {
		this.companySize = companySize;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

    
}