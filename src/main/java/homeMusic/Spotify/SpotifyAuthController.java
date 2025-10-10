package homeMusic.Spotify;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;
import java.util.UUID;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.random.RandomGenerator;

@Controller
public class SpotifyAuthController {

    // @Value injects external parameters into variables
    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.client.secret}")
    private String clientSecret;

    @Value("${spotify.redirect.uri}")
    private String redirectUri;

    @Value("${spotify.scope}")
    private String scope;


    @GetMapping("/login")
    public String login() {
        String state = UUID.randomUUID().toString();
        String url = UriComponentsBuilder
                .fromUriString("https://accounts.spotify.com/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", clientId)
                .queryParam("scope", scope)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("state", state) // Security
                .build()
                .encode()
                .toUriString();


        return "redirect:" + url;
    }
    @GetMapping("/callback")
    public ResponseEntity<String> callback(
            @RequestParam("code") String code,
            @RequestParam("state") String state,
            HttpSession session
    ) {
        String savedState = (String) session.getAttribute("state");
        if (savedState == null || !savedState.equals(state)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid state parameter");
        }

        String tokenUrl = "https://accounts.spotify.com/api/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String authString = clientId + ":" + clientSecret;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(authString.getBytes(StandardCharsets.UTF_8));
        headers.set("Authorization", authHeader);

        String body = "grant_type=authorization_code" +
                "&code=" + code +
                "&redirect_uri=" + redirectUri;

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                tokenUrl,
                HttpMethod.POST,
                request,
                String.class
        );

        // Hier bekommst du access_token & refresh_token
        // Später: Token speichern und Benutzer weiterleiten
        return ResponseEntity.ok(response.getBody());
    }


}

