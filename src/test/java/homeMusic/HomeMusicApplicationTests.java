package homeMusic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = homeMusic.Config.DotenvLoader.class)
class HomeMusicApplicationTests {

	@Test
	void contextLoads() {
	}

}
