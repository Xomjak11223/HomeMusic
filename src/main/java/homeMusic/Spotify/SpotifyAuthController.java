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
import java.util.Map;
import java.util.UUID;

import java.nio.charset.StandardCharsets;

/*  NEEDS HTTPS, Spotify Premium Account, Own Project on the Developer Dashboard
    with the following details in the application.properties:
    - redirect URL
    - spotify client.id
    - spotify client.secret in
*/

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

    @Value("${spotify.token.url}")
    String tokenUrl;


    @GetMapping("/spotify/login")
    public String login(HttpSession session) {
        String state = UUID.randomUUID().toString();
        session.setAttribute("spotify_state", state);
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

        System.out.println(redirectUri);
        System.out.println("state0: " + state);
        return "redirect:" + url;
    }
    @GetMapping("/spotify/callback")
    public ResponseEntity<String> callback(
            @RequestParam("code") String code,
            @RequestParam("state") String state,
            HttpSession session
    )
    {
        System.out.println("state1: " + state);

        String savedState = (String) session.getAttribute("spotify_state");
        session.removeAttribute("spotify_state");
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
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                tokenUrl,
                HttpMethod.POST,
                request,
                new org.springframework.core.ParameterizedTypeReference<>() {}
        );


        Map<String, Object> responseBody = response.getBody();
        if (responseBody == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No response from Spotify");
        }

        // Save token for Spotify API Access
        String accessToken = (String) response.getBody().get("access_token");
        String refreshToken = (String) response.getBody().get("refresh_token");
        session.setAttribute("spotify_access_token", accessToken);
        session.setAttribute("spotify_refresh_token", refreshToken);

        System.out.println("Access-Token: "+ accessToken);
        System.out.println("Refresh-Token: "+ refreshToken);
        System.out.println("Scope: "+ response.getBody().get("scope")); // scope defines what you can do with the access token
        System.out.println("Access Token received");

        return ResponseEntity.ok("Saved Access Token.");
    }
    @GetMapping("/spotify/refresh")

    public ResponseEntity<String> refreshAccessToken(HttpSession session) {
        String refreshToken = (String) session.getAttribute("spotify_refresh_token");
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Kein Refresh Token vorhanden.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String authString = clientId + ":" + clientSecret;
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString(authString.getBytes(StandardCharsets.UTF_8)));

        String body = "grant_type=refresh_token&refresh_token=" + refreshToken;
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.exchange(
                tokenUrl,
                HttpMethod.POST,
                request,
                Map.class
        );

        String newAccessToken = (String) response.getBody().get("access_token");
        session.setAttribute("spotify_access_token", newAccessToken);

        System.out.println("Access Token erneuert.");
        return ResponseEntity.ok("Neues Access Token: " + newAccessToken);
    }

}

