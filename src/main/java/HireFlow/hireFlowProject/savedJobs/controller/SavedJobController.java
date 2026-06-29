package HireFlow.hireFlowProject.savedJobs.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import HireFlow.hireFlowProject.savedJobs.dto.SavedJobResponse;
import HireFlow.hireFlowProject.savedJobs.model.SavedJob;
import HireFlow.hireFlowProject.savedJobs.service.SavedJobService;

@RestController
@RequestMapping("/api/saved-jobs")
public class SavedJobController {

    private final SavedJobService savedJobService;

    public SavedJobController(
            SavedJobService savedJobService) {

        this.savedJobService = savedJobService;
    }

    // Save a Job
    @PostMapping("/{jobId}")
    @PreAuthorize("hasRole('CANDIDATE')")
    public SavedJob saveJob(
            @PathVariable String jobId) {

        return savedJobService.saveJob(jobId);
    }

    // Get My Saved Jobs
    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public List<SavedJobResponse> getMySavedJobs() {

        return savedJobService.getMySavedJobs();
    }
    
    // Remove Saved Job
    @DeleteMapping("/{jobId}")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String removeSavedJob(
            @PathVariable String jobId) {

        savedJobService.removeSavedJob(jobId);

        return "Job removed from saved jobs.";
    }
}