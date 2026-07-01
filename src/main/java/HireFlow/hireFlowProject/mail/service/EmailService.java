package HireFlow.hireFlowProject.mail.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private final JavaMailSender mailSender;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendOtpEmail(String to, String otp) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(to);
		message.setSubject("HireFlow Email Verification");

		message.setText("Your HireFlow verification code is: " + otp + "\n\nThis OTP expires in 5 minutes.");

		mailSender.send(message);
	}

	public void sendPasswordResetOtp(String to, String otp) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(to);

		message.setSubject("HireFlow Password Reset");

		message.setText("Your HireFlow password reset OTP is: " + otp + "\n\nThis OTP expires in 5 minutes.");

		mailSender.send(message);
	}
}