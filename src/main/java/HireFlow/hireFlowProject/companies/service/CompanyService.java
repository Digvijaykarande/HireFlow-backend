package HireFlow.hireFlowProject.companies.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import HireFlow.hireFlowProject.companies.dto.CreateCompanyRequest;
import HireFlow.hireFlowProject.companies.model.Company;
import HireFlow.hireFlowProject.companies.repository.CompanyRepository;
import HireFlow.hireFlowProject.common.exception.CompanyAlreadyExistsException;

@Service
public class CompanyService {

    private final CompanyRepository companyRepo;

    public CompanyService(
            CompanyRepository companyRepo) {

        this.companyRepo = companyRepo;
    }

    public Company createCompany(
            CreateCompanyRequest request,
            String creatorEmail) {

        if(companyRepo.findByName(
                request.getName()).isPresent()) {

            throw new CompanyAlreadyExistsException(
                    "Company already exists");
        }

        Company company = new Company();

        company.setName(request.getName());
        company.setLogoUrl(request.getLogoUrl());
        company.setDescription(request.getDescription());
        company.setWebsite(request.getWebsite());
        company.setIndustry(request.getIndustry());
        company.setCompanySize(request.getCompanySize());
        company.setLocation(request.getLocation());

        company.setVerified(false);

        company.setCreatedBy(creatorEmail);

        company.setCreatedAt(
                LocalDateTime.now());

        return companyRepo.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }
}