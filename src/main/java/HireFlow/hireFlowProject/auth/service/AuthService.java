package HireFlow.hireFlowProject.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import HireFlow.hireFlowProject.auth.dto.*;
import HireFlow.hireFlowProject.mail.service.EmailService;
import HireFlow.hireFlowProject.mail.service.OtpService;
import HireFlow.hireFlowProject.users.model.User;
import HireFlow.hireFlowProject.users.repository.UserRepository;

@Service
public class AuthService {

	private final UserRepository userRepo;
	private final PasswordEncoder encoder;
	private final JwtService jwtService;
	private final OtpService otpService;
	private final EmailService emailService;

	public AuthService(UserRepository userRepo, PasswordEncoder encoder, JwtService jwtService, OtpService otpService,
			EmailService emailService) {

		this.userRepo = userRepo;
		this.encoder = encoder;
		this.jwtService = jwtService;
		this.otpService = otpService;
		this.emailService = emailService;
	}

	public void register(RegisterRequest request) {

		if (userRepo.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists");
		}

		User user = new User();

		user.setName(request.getName());
		user.setEmail(request.getEmail());

		user.setPassword(encoder.encode(request.getPassword()));

		user.setRole(request.getRole());

		user.setVerified(false);

		userRepo.save(user);
	}

	public AuthResponse login(LoginRequest request) {

		User user = userRepo.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

		boolean valid = encoder.matches(request.getPassword(), user.getPassword());

		if (!valid) {
			throw new RuntimeException("Invalid credentials");
		}

		String token = jwtService.generateToken(user.getEmail());

		return new AuthResponse(token, user.getEmail(), user.getRole());
	}

	public String sendVerificationOtp(String email) {

		User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		if (user.isVerified()) {
			throw new RuntimeException("Email already verified");
		}

		String otp = otpService.generateOtp();

		otpService.saveOtp(email, otp);

		emailService.sendOtpEmail(email, otp);

		return "OTP sent successfully.";
	}

	public String verifyOtp(String email, String otp) {

		String storedOtp = otpService.getOtp(email);

		if (storedOtp == null) {
			throw new RuntimeException("OTP expired");
		}

		if (!storedOtp.equals(otp)) {
			throw new RuntimeException("Invalid OTP");
		}

		User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		user.setVerified(true);

		userRepo.save(user);

		otpService.deleteOtp(email);

		return "Email verified successfully.";
	}

	public String forgotPassword(String email) {

		User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		String otp = otpService.generateOtp();

		otpService.saveOtp(email, otp);

		emailService.sendPasswordResetOtp(email, otp);

		return "Password reset OTP sent successfully.";
	}

	public String resetPassword(String email, String otp, String newPassword) {

		String storedOtp = otpService.getOtp(email);

		if (storedOtp == null) {
			throw new RuntimeException("OTP expired");
		}

		if (!storedOtp.equals(otp)) {
			throw new RuntimeException("Invalid OTP");
		}

		User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		user.setPassword(encoder.encode(newPassword));
		
		userRepo.save(user);

		otpService.deleteOtp(email);

		return "Password reset successfully.";
	}

}