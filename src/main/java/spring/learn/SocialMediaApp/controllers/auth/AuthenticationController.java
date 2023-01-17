package spring.learn.SocialMediaApp.controllers.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import spring.learn.SocialMediaApp.ApplicationManager;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final ApplicationManager applicationManager;

    @GetMapping
    public String getLoginPage(){
        return "login-signup";
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request).getToken());
    }
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws ServletException, IOException {
        return ResponseEntity.ok(authService.authenticate(authenticationRequest).getToken());
    }
//    private HttpHeaders createHttpHeaders()
//    {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(applicationManager.getCURRENT_TOKEN());
//
////        headers.add("Authorization", applicationManager.getCURRENT_TOKEN());
//        return headers;
//    }
//
//    private ResponseEntity<String> getHomePage()
//    {
//        String theUrl = "http://localhost:8080/home";
//        RestTemplate restTemplate = new RestTemplate();
//        try {
//            HttpHeaders headers = createHttpHeaders();
//            HttpEntity<String> entity =
//                    new HttpEntity<String>(applicationManager.getCurrentUser().toString(), headers);
//            return restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class);
//        }
//        catch (Exception eek) {
//            System.out.println("** Exception: "+ eek.getMessage());
//        }
//        return null;
//    }
}
