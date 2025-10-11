package homeMusic.Config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class DotenvLoader implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        // get .env indexes
        String ip = dotenv.get("YOUR_IP", "");
        String clientId = dotenv.get("SPOTIFY_CLIENT_ID", "");
        String clientSecret = dotenv.get("SPOTIFY_CLIENT_SECRET", "");
        String keystore_pw =  dotenv.get("KEYSTORE_PASSWORD", "");

        // set in .properties
        System.setProperty("YOUR_IP", ip);
        System.setProperty("SPOTIFY_CLIENT_ID", clientId);
        System.setProperty("SPOTIFY_CLIENT_SECRET", clientSecret);
        System.setProperty("KEYSTORE_PASSWORD", keystore_pw);

        // Debug
        System.out.println("[.env] YOUR_IP=" + ip);
        System.out.println("[.env] SPOTIFY_CLIENT_ID=" + clientId);
        System.out.println("[.env] SPOTIFY_CLIENT_SECRET=" + (clientSecret.isEmpty() ? "(leer)" : "********"));
        System.out.println("[.env] KEYSTORE_PASSWORD=" + (keystore_pw.isEmpty() ? "(leer)" : "********"));
    }
}
