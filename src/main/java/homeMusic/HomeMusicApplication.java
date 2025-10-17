package homeMusic;

import java.util.ArrayList;
import java.util.List;

import homeMusic.Config.DotenvLoader;
import homeMusic.Tracks.SoundcloudTrack;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import io.github.cdimascio.dotenv.Dotenv;

import homeMusic.Tracks.Track;
import homeMusic.Tracks.SoundcloudTrack;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@RestController
public class HomeMusicApplication {
	public static List<Track> tracks;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(HomeMusicApplication.class);
		app.addInitializers(new DotenvLoader());

		// Test
		tracks = new ArrayList<>();
		SoundcloudTrack s = new SoundcloudTrack("https://www.soundcloud.com");
		tracks.add(s);

		Dotenv dotenv = Dotenv.load();
		dotenv.get("");

		app.run(args);
	}

}
