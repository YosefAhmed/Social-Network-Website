package spring.learn.SocialMediaApp.controllers.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.learn.SocialMediaApp.ApplicationManager;
import spring.learn.SocialMediaApp.models.Role;
import spring.learn.SocialMediaApp.models.UserModel;
import spring.learn.SocialMediaApp.reositories.UserRepository;
import spring.learn.SocialMediaApp.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final ApplicationManager applicationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = UserModel.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        applicationManager.setCurrentUser(user);
        applicationManager.setCURRENT_TOKEN(jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try{
            authManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getEmail(),
                      request.getPassword()
              )
            );
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        applicationManager.setCurrentUser(user);
        applicationManager.setCURRENT_TOKEN(jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(user)
                .build();
    }
}
