package HireFlow.hireFlowProject.applications.dto;

public class ApplyJobRequest {
	private String jobId;
	private String coverLetter;
	private String resumeUrl;
	
	public ApplyJobRequest() {}
	public ApplyJobRequest(String jobId, String coverLetter, String resumeUrl) {
		super();
		this.jobId = jobId;
		this.coverLetter = coverLetter;
		this.resumeUrl = resumeUrl;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
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

	
	
}
