package HireFlow.hireFlowProject.dashboard.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import HireFlow.hireFlowProject.applications.model.Application;
import HireFlow.hireFlowProject.applications.repository.ApplicationRepository;
import HireFlow.hireFlowProject.dashboard.dto.CandidateDashboardResponse;
import HireFlow.hireFlowProject.dashboard.dto.RecruiterDashboardResponse;
import HireFlow.hireFlowProject.interviews.model.Interview;
import HireFlow.hireFlowProject.interviews.repository.InterviewRepository;
import HireFlow.hireFlowProject.jobs.model.Job;
import HireFlow.hireFlowProject.jobs.repository.JobRepository;
import HireFlow.hireFlowProject.savedJobs.repository.SavedJobRepository;
import HireFlow.hireFlowProject.users.model.User;
import HireFlow.hireFlowProject.users.repository.UserRepository;

@Service
public class DashboardService {

	private final UserRepository userRepo;
	private final ApplicationRepository applicationRepo;
	private final SavedJobRepository savedJobRepo;
	private final InterviewRepository interviewRepo;
	private final JobRepository jobRepo;

	public DashboardService(UserRepository userRepo, ApplicationRepository applicationRepo,
			SavedJobRepository savedJobRepo, InterviewRepository interviewRepo,JobRepository jobRepo) {

		this.userRepo = userRepo;
		this.applicationRepo = applicationRepo;
		this.savedJobRepo = savedJobRepo;
		this.interviewRepo = interviewRepo;
		this.jobRepo = jobRepo;
	}

	public CandidateDashboardResponse getCandidateDashboard() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		CandidateDashboardResponse response = new CandidateDashboardResponse();

		// Profile Completion
		response.setProfileCompletion(calculateProfileCompletion(user));

		// Counts
		response.setJobsApplied((int) applicationRepo.countByCandidateEmail(email));

		response.setSavedJobs((int) savedJobRepo.countByCandidateEmail(email));

		response.setInterviewsScheduled((int) interviewRepo.countByCandidateEmailAndStatus(email, "SCHEDULED"));

		response.setInterviewsCompleted((int) interviewRepo.countByCandidateEmailAndStatus(email, "COMPLETED"));

		// Lists
		List<Application> applications = applicationRepo.findByCandidateEmail(email);

		List<Interview> interviews = interviewRepo.findByCandidateEmail(email);

		response.setApplications(applications);
		response.setInterviews(interviews);

		return response;
	}

	public RecruiterDashboardResponse getRecruiterDashboard() {

		String recruiterEmail = SecurityContextHolder.getContext().getAuthentication().getName();

		RecruiterDashboardResponse response = new RecruiterDashboardResponse();

		// Recruiter's Jobs
		List<Job> jobs = jobRepo.findByCreatedBy(recruiterEmail);

		response.setJobs(jobs);

		// Total Jobs
		response.setTotalJobs((int) jobRepo.countByCreatedBy(recruiterEmail));

		// Active Jobs
		response.setActiveJobs((int) jobRepo.countByCreatedByAndStatus(recruiterEmail, "OPEN"));

		// Closed Jobs
		response.setClosedJobs((int) jobRepo.countByCreatedByAndStatus(recruiterEmail, "CLOSED"));

		// Collect Job IDs
		List<String> jobIds = jobs.stream().map(Job::getId).toList();

		if (jobIds.isEmpty()) {

			response.setTotalApplications(0);
			response.setInterviewsScheduled(0);
			response.setInterviewsCompleted(0);
			response.setRecentApplications(List.of());

			return response;
		}

		// Applications
		response.setTotalApplications((int) applicationRepo.countByJobIdIn(jobIds));

		response.setRecentApplications(applicationRepo.findByJobIdIn(jobIds));

		// Interviews
		response.setInterviewsScheduled((int) interviewRepo.countByJobIdInAndStatus(jobIds, "SCHEDULED"));

		response.setInterviewsCompleted((int) interviewRepo.countByJobIdInAndStatus(jobIds, "COMPLETED"));

		return response;
	}

	private Integer calculateProfileCompletion(User user) {

		int score = 0;

		if (user.getName() != null && !user.getName().isBlank())
			score += 15;

		if (user.getEmail() != null && !user.getEmail().isBlank())
			score += 15;

		if (user.getPhone() != null && !user.getPhone().isBlank())
			score += 15;

		if (user.getLocation() != null && !user.getLocation().isBlank())
			score += 15;

		if (user.getHeading() != null && !user.getHeading().isBlank())
			score += 15;

		if (user.getResumeUrl() != null && !user.getResumeUrl().isBlank())
			score += 15;

		if (user.getProfileUrl() != null && !user.getProfileUrl().isBlank())
			score += 10;

		return score;
	}
}