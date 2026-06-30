package HireFlow.hireFlowProject.jobs.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import HireFlow.hireFlowProject.jobs.model.Job;

public interface JobRepository extends MongoRepository<Job, String> {

	List<Job> findByLocationIgnoreCase(String location);

	List<Job> findByStatus(String status);

	List<Job> findByCompanyId(String companyId);

	List<Job> findByTitleContainingIgnoreCase(String title);

	List<Job> findByCreatedBy(String createdBy);

	long countByCreatedBy(String createdBy);

	long countByCreatedByAndStatus(String createdBy, String status);
}