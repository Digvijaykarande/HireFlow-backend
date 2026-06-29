package HireFlow.hireFlowProject.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTestService implements CommandLineRunner {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisTestService(
            RedisTemplate<String, Object> redisTemplate) {

        this.redisTemplate = redisTemplate;
    }

    @Override
    public void run(String... args) {

        redisTemplate.opsForValue()
                .set("hireflow", "Redis Connected");

        Object value =
                redisTemplate.opsForValue()
                        .get("hireflow");

        System.out.println("--------------------------------");
        System.out.println(value);
        System.out.println("--------------------------------");
    }

} 
