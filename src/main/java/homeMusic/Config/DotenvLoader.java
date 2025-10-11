package homeMusic.Config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class DotenvLoader implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        // set in .properties
        String ip = dotenv.get("YOUR_IP", "");
        String clientId = dotenv.get("SPOTIFY_CLIENT_ID", "");
        String clientSecret = dotenv.get("SPOTIFY_CLIENT_SECRET", "");

        // Werte in System Properties setzen
        System.setProperty("YOUR_IP", ip);
        System.setProperty("SPOTIFY_CLIENT_ID", clientId);
        System.setProperty("SPOTIFY_CLIENT_SECRET", clientSecret);

        // Debug
        System.out.println("[.env] YOUR_IP=" + ip);
        System.out.println("[.env] SPOTIFY_CLIENT_ID=" + clientId);
        System.out.println("[.env] SPOTIFY_CLIENT_SECRET=" + (clientSecret.isEmpty() ? "(leer)" : "********"));

    }
}
