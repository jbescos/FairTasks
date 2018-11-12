package es.tododev.fairtasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:application.properties" })
public class FairtasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(FairtasksApplication.class, args);
	}
}
