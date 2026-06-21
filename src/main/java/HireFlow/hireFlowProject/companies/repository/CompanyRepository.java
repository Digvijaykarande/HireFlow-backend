package HireFlow.hireFlowProject.companies.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import HireFlow.hireFlowProject.companies.model.Company;

public interface CompanyRepository
        extends MongoRepository<Company, String> {

    Optional<Company> findByName(String name);
}