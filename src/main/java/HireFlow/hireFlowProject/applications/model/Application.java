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

    @Indexed
    private String candidateEmail;

    private String status;

    private String coverLetter;

    private String resumeUrl;

    private LocalDateTime appliedAt;

    private LocalDateTime updatedAt;
    
    
    public Application() {}

	public Application(String id, String jobId, String candidateEmail, String status, String coverLetter,
			String resumeUrl, LocalDateTime appliedAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.candidateEmail = candidateEmail;
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

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
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

    
}