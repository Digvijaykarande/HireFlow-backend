package HireFlow.hireFlowProject.applications.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import HireFlow.hireFlowProject.applications.dto.ApplyJobRequest;
import HireFlow.hireFlowProject.applications.model.Application;
import HireFlow.hireFlowProject.applications.repository.ApplicationRepository;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepo;

    public ApplicationService(
            ApplicationRepository applicationRepo) {

        this.applicationRepo = applicationRepo;
    }

    public Application applyJob(
            ApplyJobRequest request) {

        String candidateEmail =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        boolean alreadyApplied =
                applicationRepo
                        .existsByJobIdAndCandidateEmail(
                                request.getJobId(),
                                candidateEmail);

        if (alreadyApplied) {
            throw new RuntimeException(
                    "Already applied for this job");
        }

        Application application =
                new Application();

        application.setJobId(
                request.getJobId());

        application.setCandidateEmail(
                candidateEmail);

        application.setCoverLetter(
                request.getCoverLetter());

        application.setResumeUrl(
                request.getResumeUrl());

        application.setStatus(
                "SUBMITTED");

        application.setAppliedAt(
                LocalDateTime.now());

        application.setUpdatedAt(
                LocalDateTime.now());

        return applicationRepo.save(
                application);
    }

    public List<Application> getMyApplications() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return applicationRepo
                .findByCandidateEmail(email);
    }

    public List<Application> getApplicationsByJob(
            String jobId) {

        return applicationRepo
                .findByJobId(jobId);
    }

    public Application updateStatus(
            String applicationId,
            String status) {

        Application application =
                applicationRepo.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found"));

        application.setStatus(status);

        application.setUpdatedAt(
                LocalDateTime.now());

        return applicationRepo.save(
                application);
    }
}