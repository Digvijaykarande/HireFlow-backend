package HireFlow.hireFlowProject.users.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import HireFlow.hireFlowProject.users.model.User;

public interface UserRepository extends MongoRepository<User,String> {
	Optional<User> findByEmail(String email);

	List<User> findByRole(String role);
	
}
