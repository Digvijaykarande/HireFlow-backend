package HireFlow.hireFlowProject.interviews.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "interviews")
public class Interview {

    @Id
    private String id;

    @Indexed
    private String applicationId;

    @Indexed
    private String jobId;

    @Indexed
    private String candidateEmail;

    private LocalDateTime interviewDate;

    private String mode;

    private String meetingLink;

    private String address;

    private String notes;

    private String status;

    private LocalDateTime createdAt;
    
    public Interview() {}

	public Interview(String id, String applicationId, String jobId, String candidateEmail, LocalDateTime interviewDate,
			String mode, String meetingLink, String address, String notes, String status, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.applicationId = applicationId;
		this.jobId = jobId;
		this.candidateEmail = candidateEmail;
		this.interviewDate = interviewDate;
		this.mode = mode;
		this.meetingLink = meetingLink;
		this.address = address;
		this.notes = notes;
		this.status = status;
		this.createdAt = createdAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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

	public LocalDateTime getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(LocalDateTime interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMeetingLink() {
		return meetingLink;
	}

	public void setMeetingLink(String meetingLink) {
		this.meetingLink = meetingLink;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Interview [id=" + id + ", applicationId=" + applicationId + ", jobId=" + jobId + ", candidateEmail="
				+ candidateEmail + ", interviewDate=" + interviewDate + ", mode=" + mode + ", meetingLink="
				+ meetingLink + ", address=" + address + ", notes=" + notes + ", status=" + status + ", createdAt="
				+ createdAt + "]";
	}    
    
}