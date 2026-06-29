package HireFlow.hireFlowProject.savedJobs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import HireFlow.hireFlowProject.savedJobs.model.SavedJob;


public interface SavedJobRepository
        extends MongoRepository<SavedJob, String> {

    List<SavedJob> findByCandidateEmail(String candidateEmail);

    Optional<SavedJob> findByCandidateEmailAndJobId(
            String candidateEmail,
            String jobId);
}