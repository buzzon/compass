package ru.project.compass;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import ru.project.compass.entity.*;
import ru.project.compass.repository.*;
import ru.project.compass.service.IRegistrationService;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class CompassApplication extends SpringBootServletInitializer {



	public static void main(String[] args) {
		SpringApplication.run(CompassApplication.class, args);
	}

		@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CompassApplication.class);
	}

	@Bean
	public CommandLineRunner createBaseRoles(RoleRepository roleRepository){
		return (args) -> {
			roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));
			roleRepository.save(new Role("PASSENGER"));
			roleRepository.save(new Role("CARRIER"));
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
	public CommandLineRunner createBaseTransport(TransportRepository transportRepository){
		return (args) -> {
			transportRepository.save(new Transport("fly"));
			transportRepository.save(new Transport("bus"));
			transportRepository.save(new Transport("train"));
		};
	}

	@Bean
	public CommandLineRunner createTestUser(IRegistrationService IRegistrationService){
		return (args) -> {
			User user = new User();
			user.setUsername("c");
			user.setPassword("c");
			IRegistrationService.save(user, new Carrier());
		};
	}

	@Bean
	public CommandLineRunner createTestPassenger(IRegistrationService IRegistrationService){
		return (args) -> {
			User user = new User();
			user.setUsername("p");
			user.setPassword("p");
			Passenger passenger = new Passenger();
			passenger.setF_name("Виталий");
			passenger.setL_name("Наливкин");
			passenger.setM_name("Игоревич");
			passenger.setRating(4.89f);
			IRegistrationService.save(user, passenger);
		};
	}

	@Bean
	public CommandLineRunner createTestStops(StopRepository stopRepository){
		return (args) -> {
			Stop a = new Stop("A");
			stopRepository.save(a);
			Stop b = new Stop("B");
			stopRepository.save(b);
			Stop c = new Stop("C");
			stopRepository.save(c);
			Stop d = new Stop("A");
			stopRepository.save(d);
		};
	}

















//	public void createTestRide(IRegistrationService registrationService,
//											TemplateRepository templateRepository,
//											UserRepository userRepository,
//											RideRepository rideRepository,
//											TicketRepository ticketRepository,
//											TicketstatusRepository ticketstatusRepository) {
//			User user = new User();
//			user.setUsername("TestCarrier");
//			user.setPassword("TestCarrier");
//			Carrier carrier = new Carrier();
//			carrier.setTitle("OOO TestCarrier");
//			registrationService.save(user, carrier);
//
//			// создать шаблон рейса
//			Template template = new Template();
//			template.setCountTickets(64);
//			template.setPrice(500.00f);
//			carrier.addChildTemplate(template);
//			templateRepository.save(template);
//
//			// добавить рейс по шаблону
//			Ride ride = new Ride();
//			ride.setValid(true);
//			template.addChildrenRide(ride);
//			rideRepository.save(ride);
//
//			Ride ride2 = new Ride();
//			ride2.setValid(true);
//			template.addChildrenRide(ride2);
//			rideRepository.save(ride2);
//
//			// новый юзер
//			User user2 = new User();
//			user2.setUsername("Ted");
//			user2.setPassword("123");
//			Passenger passenger = new Passenger();
//			passenger.setM_name("m_name");
//			passenger.setF_name("f_name");
//			passenger.setL_name("l_name");
//			passenger.setRating(4.89f);
//			registrationService.save(user2, passenger);
//
//			Ticket ticket = new Ticket();
//			Ticketstatus ticketstatus = ticketstatusRepository.findByTitle("bought");
//			List<Integer> freeSeats = ride.getFreeSeats();
//			Integer seat = freeSeats.get(0);
//			ticket.Config(ride, passenger, seat, ticketstatus);
//
//			ticketRepository.save(ticket);
//
//			Ticket ticket2 = new Ticket();
//			List<Integer> freeSeats2 = ride.getFreeSeats();
//			Integer seat2 = freeSeats2.get(0);
//			ticket2.Config(ride, passenger, seat2, ticketstatus);
//
//			ticketRepository.save(ticket2);
//	}
}

