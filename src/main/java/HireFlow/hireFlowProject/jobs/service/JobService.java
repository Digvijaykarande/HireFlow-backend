package HireFlow.hireFlowProject.jobs.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import HireFlow.hireFlowProject.cache.JobCacheService;
import HireFlow.hireFlowProject.jobs.model.Job;
import HireFlow.hireFlowProject.jobs.repository.JobRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class JobService {

	private final JobRepository jobRepo;

	private final JobCacheService cacheService;

	public JobService(JobRepository jobRepo, JobCacheService cacheService) {

		this.jobRepo = jobRepo;
		this.cacheService = cacheService;
	}

	public List<Job> getJobs() {

		List<Job> cachedJobs = cacheService.getAllJobs();

		if (cachedJobs != null) {

			System.out.println("Returned from Redis");

			return cachedJobs;
		}

		System.out.println("Returned from MongoDB");

		List<Job> jobs = jobRepo.findAll();

		cacheService.saveAllJobs(jobs);

		return jobs;
	}

	public Job getJobById(String jobId) {

		return jobRepo.findById(jobId).orElseThrow(() -> new RuntimeException("Job Not Found"));
	}

	public Job createJob(Job job) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		job.setCreatedBy(auth.getName());

		job.setCreatedAt(LocalDateTime.now());
		
		Job savedJob=jobRepo.save(job);
		cacheService.clearAllJobsCache();
		return savedJob;
		
	}

	public Job updateJob(String jobId, Job updatedJob) {

		Job existingJob = getJobById(jobId);

		existingJob.setTitle(updatedJob.getTitle());
		existingJob.setDescription(updatedJob.getDescription());
		existingJob.setSkills(updatedJob.getSkills());

		if (updatedJob.getLocation() != null) {
			existingJob.setLocation(updatedJob.getLocation().trim().toUpperCase());
		}

		existingJob.setSalaryMin(updatedJob.getSalaryMin());
		existingJob.setSalaryMax(updatedJob.getSalaryMax());
		existingJob.setStatus(updatedJob.getStatus());

		 Job savedJob =jobRepo.save(existingJob);

		 cacheService.clearAllJobsCache();

		 return savedJob;
	}

	public void deleteJob(String jobId) {

		Job job = getJobById(jobId);

		jobRepo.delete(job);
		cacheService.clearAllJobsCache();
	}

	public List<Job> getJobsByLocation(String location) {
		return jobRepo.findByLocationIgnoreCase(location);
	}

	public List<Job> getJobsByStatus(String status) {
		return jobRepo.findByStatus(status);
	}

	public List<Job> searchJobs(String keyword) {
		return jobRepo.findByTitleContainingIgnoreCase(keyword);
	}
}