package HireFlow.hireFlowProject.applications.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "applications")
public class Application {

    @Id
    private String id;
    
    @Indexed
    private String jobId;

    private String companyId;

    private String candidateId;
    
    private String candidateName;

    private String candidateEmail;

    private String recruiterEmail;

    private String status;

    private String coverLetter;

    private String resumeUrl;

    private LocalDateTime appliedAt;

    private LocalDateTime updatedAt;
    
    public Application() {}
        
	public Application(String id, String jobId, String companyId, String candidateId, String candidateName,
			String candidateEmail, String recruiterEmail, String status, String coverLetter, String resumeUrl,
			LocalDateTime appliedAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.companyId = companyId;
		this.candidateId = candidateId;
		this.candidateName = candidateName;
		this.candidateEmail = candidateEmail;
		this.recruiterEmail = recruiterEmail;
		this.status = status;
		this.coverLetter = coverLetter;
		this.resumeUrl = resumeUrl;
		this.appliedAt = appliedAt;
		this.updatedAt = updatedAt;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getRecruiterEmail() {
		return recruiterEmail;
	}

	public void setRecruiterEmail(String recruiterEmail) {
		this.recruiterEmail = recruiterEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public String getResumeUrl() {
		return resumeUrl;
	}

	public void setResumeUrl(String resumeUrl) {
		this.resumeUrl = resumeUrl;
	}

	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}

	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", jobId=" + jobId + ", companyId=" + companyId + ", candidateId="
				+ candidateId + ", candidateName=" + candidateName + ", candidateEmail=" + candidateEmail
				+ ", recruiterEmail=" + recruiterEmail + ", status=" + status + ", coverLetter=" + coverLetter
				+ ", resumeUrl=" + resumeUrl + ", appliedAt=" + appliedAt + ", updatedAt=" + updatedAt + "]";
	}
    
    
    
    
   
}