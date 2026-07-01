package HireFlow.hireFlowProject.auth.controller;

import org.springframework.web.bind.annotation.*;

import HireFlow.hireFlowProject.auth.dto.*;
import HireFlow.hireFlowProject.auth.service.AuthService;
import HireFlow.hireFlowProject.mail.dto.EmailRequest;
import HireFlow.hireFlowProject.mail.dto.VerifyOtpRequest;
import jakarta.validation.Valid;
import HireFlow.hireFlowProject.auth.dto.ForgotPasswordRequest;
import HireFlow.hireFlowProject.auth.dto.ResetPasswordRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {

		this.authService = authService;
	}

	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest request) {

		authService.register(request);

		return "User Registered Successfully";
	}

	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest request) {

		return authService.login(request);
	}

	@PostMapping("/send-otp")
	public String sendOtp(@Valid @RequestBody EmailRequest request) {

		return authService.sendVerificationOtp(request.getEmail());
	}

	@PostMapping("/verify-otp")
	public String verifyOtp(@Valid @RequestBody VerifyOtpRequest request) {

		return authService.verifyOtp(request.getEmail(), request.getOtp());
	}

	@PostMapping("/forgot-password")
	public String forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {

		return authService.forgotPassword(request.getEmail());
	}

	@PostMapping("/reset-password")
	public String resetPassword(@Valid @RequestBody ResetPasswordRequest request) {

		return authService.resetPassword(request.getEmail(), request.getOtp(), request.getNewPassword());
	}
}