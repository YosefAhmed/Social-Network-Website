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
import org.springframework.web.util.WebUtils;
import spring.learn.SocialMediaApp.models.UserModel;
import spring.learn.SocialMediaApp.security.JwtService;

import java.io.IOException;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class AuthenticationController {

    private final AuthenticationService authService;
    @GetMapping
    public String getRout(
            @CookieValue(value = "token", required = false) String token,
            @CookieValue(value = "rememberMe", required = false)String rememberMe,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        String rout = "login-signup";
        if(token != null){
            try{
                rout = Objects.requireNonNull(sendRequestWithToken("http://localhost:8080/home", token, null)).getBody();
                if(!Boolean.parseBoolean(rememberMe)){
                    deleteCookie("token", request, response);
                }
            }catch (HttpClientErrorException e){
                deleteCookie("token", request,response);
            }
        }
        return rout;
    }
    @GetMapping("/home")
    @ResponseBody
    public String getHomePage(){
        return "home";
    }

    @GetMapping("authenticated/getToken")
    public ResponseEntity<String> getToken(
//            @RequestBody String currentUser,
            HttpServletRequest request){
        Cookie cookie = WebUtils.getCookie(request,"token");
        assert cookie != null;
//        if(new JwtService().isTokenValid(cookie.getValue(),  UserModel.builder().email(currentUser).build())){
//            return ResponseEntity.ok(cookie.getValue());
//        }
//        else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return ResponseEntity.ok(cookie.getValue());
    }
    @PostMapping("authenticated/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request,
            HttpServletResponse response
    ){
        AuthenticationResponse authResponse = authService.register(request);
        addCookie("token", authResponse.getToken(),response);
        return ResponseEntity.ok(authResponse.getUser().toString());
    }
    @PostMapping("authenticated/login")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpServletResponse response
    ) throws ServletException, IOException {
        AuthenticationResponse authResponse = authService.authenticate(authenticationRequest);
        addCookie("token", authResponse.getToken(),response);
        addCookie("rememberMe", authenticationRequest.getRememberMe().toString(), response);
        return ResponseEntity.ok(authResponse.getUser().toString());
    }
    private void deleteCookie(String key,HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = WebUtils.getCookie(request,key);
        assert cookie != null;
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
    private void addCookie(String key, String value, HttpServletResponse response){
        Cookie cookie = new Cookie(key,value);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
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
