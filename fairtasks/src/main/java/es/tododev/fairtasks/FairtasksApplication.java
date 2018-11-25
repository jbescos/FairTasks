package es.tododev.fairtasks;

import javax.servlet.Filter;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
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
