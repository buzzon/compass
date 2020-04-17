package ru.naumen.compass;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import ru.naumen.compass.entity.Role;
import ru.naumen.compass.repository.RoleRepository;

@SpringBootApplication
public class CompassApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CompassApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CompassApplication.class, args);
	}

	@Bean
	public CommandLineRunner createRoles(RoleRepository roleRepository){
		return (args) -> {
			roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));
		};
	}
}

