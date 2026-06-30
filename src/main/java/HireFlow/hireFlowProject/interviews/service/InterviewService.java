package HireFlow.hireFlowProject.interviews.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import HireFlow.hireFlowProject.applications.model.Application;
import HireFlow.hireFlowProject.applications.repository.ApplicationRepository;
import HireFlow.hireFlowProject.interviews.dto.CreateInterviewRequest;
import HireFlow.hireFlowProject.interviews.model.Interview;
import HireFlow.hireFlowProject.interviews.repository.InterviewRepository;
import HireFlow.hireFlowProject.notifications.service.NotificationService;

@Service
public class InterviewService {

	private final InterviewRepository interviewRepo;

	private final ApplicationRepository applicationRepo;

	private final NotificationService notificationService;

	public InterviewService(InterviewRepository interviewRepo, ApplicationRepository applicationRepo,
			NotificationService notificationService) {

		this.interviewRepo = interviewRepo;
		this.applicationRepo = applicationRepo;
		this.notificationService = notificationService;
	}

	public Interview scheduleInterview(CreateInterviewRequest request) {

		Application application = applicationRepo.findById(request.getApplicationId())
				.orElseThrow(() -> new RuntimeException("Application not found"));

		Interview interview = new Interview();

		interview.setApplicationId(request.getApplicationId());

		interview.setJobId(request.getJobId());

		interview.setCandidateEmail(request.getCandidateEmail());

		interview.setInterviewDate(request.getInterviewDate());

		interview.setMode(request.getMode());

		interview.setMeetingLink(request.getMeetingLink());

		interview.setAddress(request.getAddress());

		interview.setNotes(request.getNotes());

		interview.setStatus("SCHEDULED");

		interview.setCreatedAt(LocalDateTime.now());

		Interview saved = interviewRepo.save(interview);

		application.setStatus("INTERVIEW_SCHEDULED");

		notificationService.createNotification(interview.getCandidateEmail(), "Interview Scheduled",
				"Interview scheduled for " + interview.getInterviewDate(), "INTERVIEW", saved.getId(), "INTERVIEW",
				"/interviews/" + saved.getId());
		applicationRepo.save(application);

		return saved;
	}

	public List<Interview> getMyInterviews() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return interviewRepo.findByCandidateEmail(email);
	}

	public List<Interview> getJobInterviews(String jobId) {

		return interviewRepo.findByJobId(jobId);
	}

	public Interview completeInterview(String interviewId) {

		Interview interview = interviewRepo.findById(interviewId)
				.orElseThrow(() -> new RuntimeException("Interview not found"));

		interview.setStatus("COMPLETED");

		Interview saved = interviewRepo.save(interview);

		Application application = applicationRepo.findById(interview.getApplicationId())
				.orElseThrow(() -> new RuntimeException("Application not found"));

		application.setStatus("INTERVIEW_COMPLETED");

		applicationRepo.save(application);

		return saved;
	}

	public Interview cancelInterview(String interviewId) {

		Interview interview = interviewRepo.findById(interviewId)
				.orElseThrow(() -> new RuntimeException("Interview not found"));

		interview.setStatus("CANCELLED");

		return interviewRepo.save(interview);
	}
}