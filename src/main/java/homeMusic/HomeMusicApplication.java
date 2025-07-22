package homeMusic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import homeMusic.Tracks.Track;

@SpringBootApplication
@RestController
public class HomeMusicApplication {
	public static List<Track> tracks;

	public static void main(String[] args) {
		tracks = new ArrayList<>();
		SpringApplication.run(HomeMusicApplication.class, args);
	}

}
