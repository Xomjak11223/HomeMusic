package homeMusic.Config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class DotenvLoader implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        // Werte in System Properties setzen
        System.setProperty("YOUR_IP", dotenv.get("YOUR_IP", ""));
        System.setProperty("SPOTIFY_CLIENT_ID", dotenv.get("SPOTIFY_CLIENT_ID", ""));
        System.setProperty("SPOTIFY_CLIENT_SECRET", dotenv.get("SPOTIFY_CLIENT_SECRET", ""));
        System.setProperty("SPOTIFY_REDIRECT_URI", dotenv.get("SPOTIFY_REDIRECT_URI", ""));
    }
}
