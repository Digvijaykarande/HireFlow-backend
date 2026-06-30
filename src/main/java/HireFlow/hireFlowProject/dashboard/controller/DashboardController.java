package HireFlow.hireFlowProject.dashboard.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HireFlow.hireFlowProject.dashboard.dto.CandidateDashboardResponse;
import HireFlow.hireFlowProject.dashboard.dto.RecruiterDashboardResponse;
import HireFlow.hireFlowProject.dashboard.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	private final DashboardService dashboardService;

	public DashboardController(DashboardService dashboardService) {

		this.dashboardService = dashboardService;
	}

	@GetMapping("/candidate")
	@PreAuthorize("hasRole('CANDIDATE')")
	public CandidateDashboardResponse getCandidateDashboard() {

		return dashboardService.getCandidateDashboard();
	}
	
	@GetMapping("/recruiter")
	@PreAuthorize("hasRole('RECRUITER')")
	public RecruiterDashboardResponse getRecruiterDashboard() {

	    return dashboardService.getRecruiterDashboard();
	}
}