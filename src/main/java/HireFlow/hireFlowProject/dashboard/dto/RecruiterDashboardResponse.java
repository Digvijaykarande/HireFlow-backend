package HireFlow.hireFlowProject.dashboard.dto;

import java.util.List;

import HireFlow.hireFlowProject.applications.model.Application;
import HireFlow.hireFlowProject.jobs.model.Job;

public class RecruiterDashboardResponse {

    private Integer totalJobs;

    private Integer activeJobs;

    private Integer closedJobs;

    private Integer totalApplications;

    private Integer interviewsScheduled;

    private Integer interviewsCompleted;

    private List<Job> jobs;

    private List<Application> recentApplications;

    public RecruiterDashboardResponse() {
    }

    public Integer getTotalJobs() {
        return totalJobs;
    }

    public void setTotalJobs(Integer totalJobs) {
        this.totalJobs = totalJobs;
    }

    public Integer getActiveJobs() {
        return activeJobs;
    }

    public void setActiveJobs(Integer activeJobs) {
        this.activeJobs = activeJobs;
    }

    public Integer getClosedJobs() {
        return closedJobs;
    }

    public void setClosedJobs(Integer closedJobs) {
        this.closedJobs = closedJobs;
    }

    public Integer getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(Integer totalApplications) {
        this.totalApplications = totalApplications;
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

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Application> getRecentApplications() {
        return recentApplications;
    }

    public void setRecentApplications(List<Application> recentApplications) {
        this.recentApplications = recentApplications;
    }
}