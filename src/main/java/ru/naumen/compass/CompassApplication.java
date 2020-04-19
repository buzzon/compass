package ru.naumen.compass;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import ru.naumen.compass.entity.*;
import ru.naumen.compass.repository.*;
import ru.naumen.compass.service.RegistrationService;

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

	@Bean
	public CommandLineRunner createTestUser(RegistrationService registrationService){
		return (args) -> {
			User user = new User();
			user.setUsername("q");
			user.setPassword("q");
			registrationService.save(user);
		};
	}

	@Bean
	public CommandLineRunner createTestRide(RegistrationService registrationService,
											TemplateRepository templateRepository,
											RideRepository rideRepository) {
		return (args) -> {
			User user = new User();
			user.setUsername("w");
			user.setPassword("w");
			Carrier carrier = new Carrier();
			carrier.setTitle("OOO TestCarrier");
			registrationService.save(user, carrier);

			// создать шаблон рейса
			Template template = new Template();
			template.setCountTickets(64);
			template.setPrice(500.00f);
			carrier.addChildTemplate(template);
			templateRepository.save(template);

			// добавить рейс по шаблону
			Ride ride = new Ride();
			ride.setValid(true);
			template.addChildrenRide(ride);
			rideRepository.save(ride);
		};
	}
}

