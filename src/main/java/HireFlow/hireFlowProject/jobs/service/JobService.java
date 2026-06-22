package HireFlow.hireFlowProject.jobs.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import HireFlow.hireFlowProject.jobs.model.Job;
import HireFlow.hireFlowProject.jobs.repository.JobRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class JobService {

    private final JobRepository jobRepo;

    public JobService(JobRepository jobRepo) {
        this.jobRepo = jobRepo;
    }

    public List<Job> getJobs() {
        return jobRepo.findAll();
    }

    public Job getJobById(String jobId) {

        return jobRepo.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job Not Found"));
    }

    public Job createJob(Job job) {
    	Authentication auth =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        job.setCreatedBy(auth.getName());

        job.setCreatedAt(LocalDateTime.now());

        return jobRepo.save(job);
    }

    public Job updateJob(String jobId, Job updatedJob) {

        Job existingJob = getJobById(jobId);

        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setSkills(updatedJob.getSkills());

        if(updatedJob.getLocation() != null) {
            existingJob.setLocation(
                updatedJob.getLocation()
                    .trim()
                    .toUpperCase()
            );
        }

        existingJob.setSalaryMin(updatedJob.getSalaryMin());
        existingJob.setSalaryMax(updatedJob.getSalaryMax());
        existingJob.setStatus(updatedJob.getStatus());

        return jobRepo.save(existingJob);
    }

    public void deleteJob(String jobId) {

        Job job = getJobById(jobId);

        jobRepo.delete(job);
    }

    public List<Job> getJobsByLocation(String location) {
        return jobRepo.findByLocationIgnoreCase(location);
    }

    public List<Job> getJobsByStatus(String status) {
        return jobRepo.findByStatus(status);
    }
    
    public List<Job> searchJobs(String keyword){
        return jobRepo.findByTitleContainingIgnoreCase(keyword);
    }
}