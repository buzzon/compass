package ru.naumen.compass;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import ru.naumen.compass.entity.Role;
import ru.naumen.compass.entity.Ticketstatus;
import ru.naumen.compass.repository.RoleRepository;
import ru.naumen.compass.repository.TicketstatusRepository;

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
	public CommandLineRunner createBaseRoles(RoleRepository roleRepository){
		return (args) -> {
			roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));
		};
	}

	@Bean
	public CommandLineRunner createBaseTicketstatus(TicketstatusRepository ticketstatusRepository){
		return (args) -> {
			ticketstatusRepository.save(new Ticketstatus("bought"));
			ticketstatusRepository.save(new Ticketstatus("made"));
			ticketstatusRepository.save(new Ticketstatus("canceled"));
		};
	}
}

