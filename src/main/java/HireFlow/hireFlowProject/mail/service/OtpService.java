package HireFlow.hireFlowProject.mail.service;

import java.time.Duration;
import java.util.Random;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

	private static final String OTP_PREFIX = "OTP:";

	private final RedisTemplate<String, Object> redisTemplate;

	public OtpService(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	// Generate 6-digit OTP
	public String generateOtp() {

		Random random = new Random();

		return String.valueOf(100000 + random.nextInt(900000));
	}

	// Save OTP (Expires in 5 minutes)
	public void saveOtp(String email, String otp) {

		redisTemplate.opsForValue().set(OTP_PREFIX + email, otp, Duration.ofMinutes(5));
	}

	// Get OTP
	public String getOtp(String email) {

		Object otp = redisTemplate.opsForValue().get(OTP_PREFIX + email);

		return otp == null ? null : otp.toString();
	}

	// Delete OTP
	public void deleteOtp(String email) {

		redisTemplate.delete(OTP_PREFIX + email);
	}
}