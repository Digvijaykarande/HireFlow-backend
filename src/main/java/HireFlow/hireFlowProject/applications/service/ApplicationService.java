package HireFlow.hireFlowProject.applications.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import HireFlow.hireFlowProject.applications.dto.ApplyJobRequest;
import HireFlow.hireFlowProject.applications.model.Application;
import HireFlow.hireFlowProject.applications.repository.ApplicationRepository;
import HireFlow.hireFlowProject.companies.model.Company;
import HireFlow.hireFlowProject.companies.repository.CompanyRepository;
import HireFlow.hireFlowProject.jobs.model.Job;
import HireFlow.hireFlowProject.jobs.repository.JobRepository;
import HireFlow.hireFlowProject.notifications.service.NotificationService;
import HireFlow.hireFlowProject.users.model.User;
import HireFlow.hireFlowProject.users.repository.UserRepository;

@Service
public class ApplicationService {

	private final ApplicationRepository applicationRepo;
	private final NotificationService notificationService;
	private final JobRepository jobRepo;
	private final UserRepository userRepo;

	public ApplicationService(ApplicationRepository applicationRepo, JobRepository jobRepo,
			NotificationService notificationService, UserRepository userRepo) {

		this.applicationRepo = applicationRepo;
		this.jobRepo = jobRepo;
		this.notificationService = notificationService;
		this.userRepo = userRepo;
	}

	public Application applyJob(ApplyJobRequest request) {

		String candidateEmail = SecurityContextHolder.getContext().getAuthentication().getName();

		boolean alreadyApplied = applicationRepo.existsByJobIdAndCandidateEmail(request.getJobId(), candidateEmail);

		if (alreadyApplied) {
			throw new RuntimeException("Already applied for this job");
		}

		Application application = new Application();
		User candidate = userRepo.findByEmail(candidateEmail).orElseThrow();
		User candidate2 = userRepo.findByEmail(candidateEmail).orElseThrow();
		application.setJobId(request.getJobId());
		application.setCandidateEmail(candidateEmail);
		application.setCoverLetter(request.getCoverLetter());
		application.setResumeUrl(candidate2.getResumeUrl());
		application.setStatus("SUBMITTED");
		application.setAppliedAt(LocalDateTime.now());
		application.setUpdatedAt(LocalDateTime.now());

		Application savedApplication = applicationRepo.save(application);

		Job job = jobRepo.findById(request.getJobId()).orElseThrow(() -> new RuntimeException("Job not found"));

		notificationService.createNotification(job.getCreatedBy(), "New Application Received",
				candidate.getName() + " applied for " + job.getTitle() + ".", "APPLICATION", savedApplication.getId(),
				"APPLICATION", "/applications/" + savedApplication.getId());

		return savedApplication;
	}

	public List<Application> getMyApplications() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return applicationRepo.findByCandidateEmail(email);
	}

	public List<Application> getApplicationsByJob(String jobId) {

		return applicationRepo.findByJobId(jobId);
	}

	public Application updateStatus(String applicationId, String status) {

		Application application = applicationRepo.findById(applicationId)
				.orElseThrow(() -> new RuntimeException("Application not found"));

		application.setStatus(status);

		application.setUpdatedAt(LocalDateTime.now());

		Application savedApplication = applicationRepo.save(application);

		notificationService.createNotification(application.getCandidateEmail(), "Application Updated",
				"Your application status is now " + application.getStatus(), "APPLICATION", application.getId(),
				"APPLICATION", "/applications/" + application.getId());
		return savedApplication;
	}
}