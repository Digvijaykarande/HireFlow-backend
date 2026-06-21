package HireFlow.hireFlowProject.companies.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import HireFlow.hireFlowProject.companies.dto.CreateCompanyRequest;
import HireFlow.hireFlowProject.companies.model.Company;
import HireFlow.hireFlowProject.companies.service.CompanyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(
            CompanyService service) {

        this.service = service;
    }

    @PostMapping
    public Company createCompany(
            @Valid
            @RequestBody CreateCompanyRequest request,
            Principal principal) {

        return service.createCompany(
                request,
                principal.getName());
    }

    @GetMapping
    public List<Company> getCompanies() {
        return service.getAllCompanies();
    }
}