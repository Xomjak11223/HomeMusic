package homeMusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HomeMusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeMusicApplication.class, args);
	}

}
