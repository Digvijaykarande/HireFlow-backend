package HireFlow.hireFlowProject.savedJobs.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import HireFlow.hireFlowProject.jobs.model.Job;
import HireFlow.hireFlowProject.jobs.repository.JobRepository;
import HireFlow.hireFlowProject.savedJobs.dto.SavedJobResponse;
import HireFlow.hireFlowProject.savedJobs.model.SavedJob;
import HireFlow.hireFlowProject.savedJobs.repository.SavedJobRepository;

@Service
public class SavedJobService {

	private final SavedJobRepository savedJobRepo;
	private final JobRepository jobRepo;

	public SavedJobService(SavedJobRepository savedJobRepo, JobRepository jobRepo) {

		this.savedJobRepo = savedJobRepo;
		this.jobRepo = jobRepo;
	}

	// Save Job
	public SavedJob saveJob(String jobId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		Job job = jobRepo.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));

		if (savedJobRepo.findByCandidateEmailAndJobId(email, jobId).isPresent()) {
			throw new RuntimeException("Job already saved");
		}

		SavedJob savedJob = new SavedJob();

		savedJob.setCandidateEmail(email);
		savedJob.setJobId(job.getId());
		savedJob.setSavedAt(LocalDateTime.now());

		return savedJobRepo.save(savedJob);
	}

	// Get My Saved Jobs
	public List<SavedJobResponse> getMySavedJobs() {

	    String email =
	            SecurityContextHolder
	                    .getContext()
	                    .getAuthentication()
	                    .getName();

	    List<SavedJob> savedJobs =
	            savedJobRepo.findByCandidateEmail(email);

	    List<SavedJobResponse> response =
	            new ArrayList<>();

	    for (SavedJob savedJob : savedJobs) {

	        Job job =
	                jobRepo.findById(savedJob.getJobId())
	                        .orElse(null);

	        if (job == null) {
	            continue;
	        }

	        SavedJobResponse dto =
	                new SavedJobResponse();

	        dto.setSavedJobId(savedJob.getId());
	        dto.setJobId(job.getId());

	        dto.setTitle(job.getTitle());
	        dto.setDescription(job.getDescription());

	        dto.setCompanyId(job.getCompanyId());

	        dto.setLocation(job.getLocation());

	        dto.setSalaryMin(job.getSalaryMin());
	        dto.setSalaryMax(job.getSalaryMax());

	        dto.setStatus(job.getStatus());

	        dto.setSavedAt(savedJob.getSavedAt());

	        response.add(dto);
	    }

	    return response;
	}

	// Remove Saved Job
	public void removeSavedJob(String jobId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		SavedJob savedJob = savedJobRepo.findByCandidateEmailAndJobId(email, jobId)
				.orElseThrow(() -> new RuntimeException("Saved job not found"));

		savedJobRepo.delete(savedJob);
	}
}