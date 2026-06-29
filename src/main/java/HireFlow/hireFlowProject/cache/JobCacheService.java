package HireFlow.hireFlowProject.cache;

import java.time.Duration;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import HireFlow.hireFlowProject.jobs.model.Job;

@Service
public class JobCacheService {

    private static final String ALL_JOBS_KEY = "jobs:all";

    private final RedisTemplate<String, Object> redisTemplate;

    public JobCacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @SuppressWarnings("unchecked")
    public List<Job> getAllJobs() {

        return (List<Job>) redisTemplate.opsForValue()
                .get(ALL_JOBS_KEY);
    }

    public void saveAllJobs(List<Job> jobs) {

        redisTemplate.opsForValue().set(
                ALL_JOBS_KEY,
                jobs,
                Duration.ofMinutes(15));
    }

    public void clearAllJobsCache() {

        redisTemplate.delete(ALL_JOBS_KEY);
    }

}