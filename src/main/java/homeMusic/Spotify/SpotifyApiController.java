package homeMusic.Spotify;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/spotify/api")
public class SpotifyApiController {

    private final RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders buildHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentTrack(HttpSession session) {
        String accessToken = (String) session.getAttribute("spotify_access_token");
        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No access token");
        }

        HttpHeaders headers = buildHeaders(accessToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                "https://api.spotify.com/v1/me/player/currently-playing",
                HttpMethod.GET,
                entity,
                Map.class
        );

        if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
            return ResponseEntity.ok("Nothing is currently playing");
        }

        return ResponseEntity.ok(response.getBody());
    }

    @PutMapping("/play")
    public ResponseEntity<String> play(HttpSession session) {
        String accessToken = (String) session.getAttribute("spotify_access_token");
        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No access token");
        }

        HttpHeaders headers = buildHeaders(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("{}", headers);

        restTemplate.exchange(
                "https://api.spotify.com/v1/me/player/play",
                HttpMethod.PUT,
                entity,
                String.class
        );

        return ResponseEntity.ok("Playback started");
    }

    @PutMapping("/pause")
    public ResponseEntity<String> pause(HttpSession session) {
        String accessToken = (String) session.getAttribute("spotify_access_token");
        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No access token");
        }

        HttpHeaders headers = buildHeaders(accessToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        restTemplate.exchange(
                "https://api.spotify.com/v1/me/player/pause",
                HttpMethod.PUT,
                entity,
                String.class
        );

        return ResponseEntity.ok("Playback paused");
    }
}
