package homeMusic;

import java.util.ArrayList;
import java.util.List;

import homeMusic.Tracks.SoundcloudTrack;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import homeMusic.Tracks.Track;
import homeMusic.Tracks.SoundcloudTrack;

@SpringBootApplication
@RestController
public class HomeMusicApplication {
	public static List<Track> tracks;

	public static void main(String[] args) {
		tracks = new ArrayList<>();
		SoundcloudTrack s = new SoundcloudTrack("https://www.soundcloud.com");
		tracks.add(s);
		SpringApplication.run(HomeMusicApplication.class, args);
	}

}
