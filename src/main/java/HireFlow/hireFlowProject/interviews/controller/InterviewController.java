package HireFlow.hireFlowProject.interviews.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import HireFlow.hireFlowProject.interviews.dto.CreateInterviewRequest;
import HireFlow.hireFlowProject.interviews.model.Interview;
import HireFlow.hireFlowProject.interviews.service.InterviewService;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService service;

    public InterviewController(
            InterviewService service) {

        this.service = service;
    }

    // Recruiter schedules interview
    @PreAuthorize("hasRole('RECRUITER')")
    @PostMapping
    public Interview scheduleInterview(
            @RequestBody CreateInterviewRequest request) {

        return service.scheduleInterview(
                request);
    }

    // Candidate views interviews
    @PreAuthorize("hasRole('CANDIDATE')")
    @GetMapping("/my")
    public List<Interview> getMyInterviews() {

        return service.getMyInterviews();
    }

    // Recruiter views interviews for a job
    @PreAuthorize("hasRole('RECRUITER')")
    @GetMapping("/job/{jobId}")
    public List<Interview> getJobInterviews(
            @PathVariable String jobId) {

        return service.getJobInterviews(
                jobId);
    }

    // Recruiter marks completed
    @PreAuthorize("hasRole('RECRUITER')")
    @PutMapping("/{id}/complete")
    public Interview completeInterview(
            @PathVariable String id) {

        return service.completeInterview(
                id);
    }

    // Recruiter cancels interview
    @PreAuthorize("hasRole('RECRUITER')")
    @PutMapping("/{id}/cancel")
    public Interview cancelInterview(
            @PathVariable String id) {

        return service.cancelInterview(
                id);
    }
}