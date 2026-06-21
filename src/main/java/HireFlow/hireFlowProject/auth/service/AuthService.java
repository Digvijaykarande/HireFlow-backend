package HireFlow.hireFlowProject.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import HireFlow.hireFlowProject.auth.dto.*;
import HireFlow.hireFlowProject.users.model.User;
import HireFlow.hireFlowProject.users.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepo,
            PasswordEncoder encoder,
            JwtService jwtService) {

        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request) {

        if(userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                encoder.encode(
                        request.getPassword()));

        user.setRole(request.getRole());

        user.setVerified(false);

        userRepo.save(user);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepo.findByEmail(
                request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException("User not found"));

        boolean valid =
                encoder.matches(
                        request.getPassword(),
                        user.getPassword());

        if(!valid) {
            throw new RuntimeException("Invalid credentials");
        }

        String token =
                jwtService.generateToken(
                        user.getEmail());

        return new AuthResponse(
                token,
                user.getEmail(),
                user.getRole());
    }
}