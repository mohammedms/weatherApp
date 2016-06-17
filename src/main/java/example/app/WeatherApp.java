package example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Standalone App using Spring Boot.
 * @author mo
 */
@SpringBootApplication
public class WeatherApp {

	public static void main(String[] args) {
        SpringApplication.run(WeatherApp.class, args);
    }
}
