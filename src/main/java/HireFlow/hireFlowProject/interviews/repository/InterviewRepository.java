package HireFlow.hireFlowProject.interviews.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import HireFlow.hireFlowProject.interviews.model.Interview;

public interface InterviewRepository
        extends MongoRepository<Interview, String>{

    List<Interview> findByCandidateEmail(String candidateEmail);

    List<Interview> findByJobId(String jobId);

    List<Interview> findByApplicationId(String applicationId);
}