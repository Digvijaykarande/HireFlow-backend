package HireFlow.hireFlowProject.dashboard.dto;

import java.util.List;

import HireFlow.hireFlowProject.applications.model.Application;
import HireFlow.hireFlowProject.interviews.model.Interview;

public class CandidateDashboardResponse {

    private Integer profileCompletion;

    private Integer jobsApplied;

    private Integer savedJobs;

    private Integer interviewsScheduled;

    private Integer interviewsCompleted;

    private List<Application> applications;

    private List<Interview> interviews;

    public CandidateDashboardResponse() {
    }

    public Integer getProfileCompletion() {
        return profileCompletion;
    }

    public void setProfileCompletion(Integer profileCompletion) {
        this.profileCompletion = profileCompletion;
    }

    public Integer getJobsApplied() {
        return jobsApplied;
    }

    public void setJobsApplied(Integer jobsApplied) {
        this.jobsApplied = jobsApplied;
    }

    public Integer getSavedJobs() {
        return savedJobs;
    }

    public void setSavedJobs(Integer savedJobs) {
        this.savedJobs = savedJobs;
    }

    public Integer getInterviewsScheduled() {
        return interviewsScheduled;
    }

    public void setInterviewsScheduled(Integer interviewsScheduled) {
        this.interviewsScheduled = interviewsScheduled;
    }

    public Integer getInterviewsCompleted() {
        return interviewsCompleted;
    }

    public void setInterviewsCompleted(Integer interviewsCompleted) {
        this.interviewsCompleted = interviewsCompleted;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }
}