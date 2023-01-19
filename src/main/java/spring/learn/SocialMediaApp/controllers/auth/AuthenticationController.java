package spring.learn.SocialMediaApp.controllers.auth;

import jakarta.annotation.Nullable;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import spring.learn.SocialMediaApp.ApplicationManager;

import java.io.IOException;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final ApplicationManager applicationManager;

    @GetMapping
    public String getRout(
            @CookieValue(value = "token", required = false) String token,
            HttpServletResponse response
    ){
        String rout = "login-signup";
        if(token != null){
            try{
                rout = Objects.requireNonNull(sendRequestWithToken("http://localhost:8080/home", token, null)).getBody();
            }catch (HttpClientErrorException e){
                //TODO delete cookie here
                System.out.println("Forbidden");
//                return rout;
            }
        }
        return rout;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request).getToken());
    }
    @PostMapping("/login")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpServletResponse response
    ) throws ServletException, IOException {
        AuthenticationResponse authResponse = authService.authenticate(authenticationRequest);
        Cookie cookie = new Cookie("token",authResponse.getToken());
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok(authResponse.getUser().toString());
    }
    private HttpHeaders createHttpHeaders(String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

//        headers.add("Authorization", applicationManager.getCURRENT_TOKEN());
        return headers;
    }

    private ResponseEntity<String> sendRequestWithToken(
            String theUrl,
            String token,
            @Nullable String requestBody)
    {
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = createHttpHeaders(token);
            HttpEntity<String> entity =
                    new HttpEntity<String>(requestBody, headers);
            return restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class);
        }
        catch (Exception eek) {
            throw eek;
        }
    }
}
