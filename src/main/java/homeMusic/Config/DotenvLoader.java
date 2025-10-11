package homeMusic.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

@Component
public class DotenvLoader implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        ConfigurableEnvironment env = event.getEnvironment();
        dotenv.entries().forEach(entry -> {
            // Setzt die Werte als System Properties → Spring kann sie wie gewohnt mit @Value lesen
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }
}
