package HireFlow.hireFlowProject.mail.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HireFlow.hireFlowProject.mail.service.OtpService;

@RestController
@RequestMapping("/api/test")
public class OtpController {

	private final OtpService otpService;

	public OtpController(OtpService otpService) {
		this.otpService = otpService;
	}

	@GetMapping("/otp")
	public String testOtp() {

		String otp = otpService.generateOtp();

		otpService.saveOtp("test@gmail.com", otp);

		return otpService.getOtp("test@gmail.com");
	}
}