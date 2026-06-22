package HireFlow.hireFlowProject.jobs.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import HireFlow.hireFlowProject.jobs.model.Job;
import HireFlow.hireFlowProject.jobs.service.JobService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getJobs() {
        return jobService.getJobs();
    }

    @GetMapping("/{jobId}")
    public Job getJobById(
            @PathVariable String jobId) {

        return jobService.getJobById(jobId);
    }
    
    @GetMapping("/search")
    public List<Job> searchJobs(
            @RequestParam String keyword){

        return jobService.searchJobs(keyword);
    }
    
    @PreAuthorize("hasRole('RECRUITER')")
    @PostMapping
    public Job createJob(
            @RequestBody Job job) {

        return jobService.createJob(job);
    }
    
    
    @PreAuthorize("hasRole('RECRUITER')")
    @PutMapping("/{jobId}")
    public Job updateJob(
            @PathVariable String jobId,
            @RequestBody Job job) {

        return jobService.updateJob(jobId, job);
    }
    
    @PreAuthorize("hasRole('RECRUITER')")
    @DeleteMapping("/{jobId}")
    public String deleteJob(
            @PathVariable String jobId) {

        jobService.deleteJob(jobId);

        return "Job deleted successfully";
    }

    @GetMapping("/location/{location}")
    public List<Job> getJobsByLocation(
            @PathVariable String location) {

        return jobService.getJobsByLocation(location);
    }

    @GetMapping("/status/{status}")
    public List<Job> getJobsByStatus(
            @PathVariable String status) {

        return jobService.getJobsByStatus(status);
    }
}