package es.tododev.fairtasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:application.properties" })
@ComponentScan(basePackages = "es.tododev.fairtasks")
public class FairtasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(FairtasksApplication.class, args);
	}
}
