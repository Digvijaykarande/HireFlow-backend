package HireFlow.hireFlowProject.applications.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import HireFlow.hireFlowProject.applications.dto.ApplyJobRequest;
import HireFlow.hireFlowProject.applications.dto.UpdateApplicationStatusRequest;
import HireFlow.hireFlowProject.applications.model.Application;
import HireFlow.hireFlowProject.applications.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(
            ApplicationService service) {

        this.service = service;
    }

    // Candidate applies for a job
    @PreAuthorize("hasRole('CANDIDATE')")
    @PostMapping
    public Application applyJob(
            @RequestBody ApplyJobRequest request) {

        return service.applyJob(request);
    }

    // Candidate sees own applications
    @PreAuthorize("hasRole('CANDIDATE')")
    @GetMapping("/my")
    public List<Application> getMyApplications() {

        return service.getMyApplications();
    }

    // Recruiter sees all applicants for a job
    @PreAuthorize("hasRole('RECRUITER')")
    @GetMapping("/job/{jobId}")
    public List<Application> getApplicationsByJob(
            @PathVariable String jobId) {

        return service.getApplicationsByJob(jobId);
    }

    // Recruiter updates application status
    @PreAuthorize("hasRole('RECRUITER')")
    @PutMapping("/{applicationId}/status")
    public Application updateStatus(
            @PathVariable String applicationId,
            @RequestBody UpdateApplicationStatusRequest request) {

        return service.updateStatus(
                applicationId,
                request.getStatus());
    }
}