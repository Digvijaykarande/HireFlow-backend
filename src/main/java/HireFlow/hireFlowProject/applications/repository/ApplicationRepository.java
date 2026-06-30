package HireFlow.hireFlowProject.applications.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import HireFlow.hireFlowProject.applications.model.Application;

public interface ApplicationRepository extends MongoRepository<Application, String> {

	List<Application> findByCandidateEmail(String email);

	List<Application> findByJobId(String jobId);

	boolean existsByJobIdAndCandidateEmail(String jobId, String candidateEmail);

	long countByCandidateEmail(String candidateEmail);

	long countByCandidateEmailAndStatus(String candidateEmail, String status);
	
	long countByJobIdIn(List<String> jobIds);

	List<Application> findByJobIdIn(List<String> jobIds);
}