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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class CompassApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CompassApplication.class, args);
	}

		@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CompassApplication.class);
	}

//
//	@Bean
//	public CommandLineRunner createBaseRoles(RoleRepository roleRepository){
//		return (args) -> {
//			roleRepository.save(new Role("ADMIN"));
//			roleRepository.save(new Role("USER"));
//			roleRepository.save(new Role("PASSENGER"));
//			roleRepository.save(new Role("CARRIER"));
//		};
//	}
//
//	@Bean
//	public CommandLineRunner createBaseTicketstatus(TicketstatusRepository ticketstatusRepository){
//		return (args) -> {
//			ticketstatusRepository.save(new Ticketstatus("bought"));
//			ticketstatusRepository.save(new Ticketstatus("made"));
//			ticketstatusRepository.save(new Ticketstatus("canceled"));
//		};
//	}
//
//	@Bean
//	public CommandLineRunner createBaseTransport(TransportRepository transportRepository){
//		return (args) -> {
//			transportRepository.save(new Transport("fly"));
//			transportRepository.save(new Transport("bus"));
//			transportRepository.save(new Transport("train"));
//		};
//	}
//
//	@Bean
//	public CommandLineRunner createTestUser(IRegistrationService registrationService){
//		return (args) -> {
//			User user = new User();
//			user.setUsername("c");
//			user.setPassword("c");
//			registrationService.save(user, new Carrier());
//		};
//	}
//
//	@Bean
//	public CommandLineRunner createTestPassenger(IRegistrationService registrationService){
//		return (args) -> {
//			User user = new User();
//			user.setUsername("p");
//			user.setPassword("p");
//			Passenger passenger = new Passenger();
//			passenger.setF_name("Виталий");
//			passenger.setL_name("Наливкин");
//			passenger.setM_name("Игоревич");
//			passenger.setRating(4.89f);
//			registrationService.save(user, passenger);
//		};
//	}
//
//	@Bean
//	public CommandLineRunner LoadTestDataPassenger(IRegistrationService registrationService){
//		return (args) -> {
//			try {
//				List<String> lines = Files.readAllLines(Paths.get("passenger_data.txt"), StandardCharsets.UTF_8);
//
//				for (String line :
//						lines) {
//					String[] half = line.split(";");
//					User user = new User();
//					user.setUsername(half[4]);
//					user.setPassword(half[5]);
//					Passenger passenger = new Passenger();
//					passenger.setL_name(half[1]);
//					passenger.setF_name(half[2]);
//					passenger.setM_name(half[3]);
//					passenger.setRating(Float.valueOf(half[0]));
//					registrationService.save(user, passenger);
//				}
//			} catch (IOException | NumberFormatException e) {
//				e.printStackTrace();
//			}
//		};
//	}
//
//	@Bean
//	public CommandLineRunner createTestStops(StopRepository stopRepository){
//		return (args) -> {
//			try {
//				List<String> lines = Files.readAllLines(Paths.get("stop_data.txt"), StandardCharsets.UTF_8);
//
//				for (String line :
//						lines) {
//					String[] half = line.split(";");
//					Stop stop = new Stop();
//					stop.setTitle(half[0]);
//					stop.setN(Double.valueOf(half[1]));
//					stop.setE(Double.valueOf(half[2]));
//					stopRepository.save(stop);
//				}
//			} catch (IOException | NumberFormatException e) {
//				e.printStackTrace();
//			}
//		};
//	}
//
//	@Bean
//	public CommandLineRunner createTestCarrier(IRegistrationService registrationService){
//		return (args) -> {
//			try {
//				List<String> lines = Files.readAllLines(Paths.get("carrier_data.txt"), StandardCharsets.UTF_8);
//
//				for (String line :
//						lines) {
//					String[] half = line.split(";");
//					User user = new User();
//					user.setUsername(half[3]);
//					user.setPassword(half[4]);
//					Carrier carrier = new Carrier();
//					carrier.setTitle(half[0]);
//					carrier.setRating(Float.valueOf(half[1]));
//					registrationService.save(user, carrier);
//				}
//			} catch (IOException | NumberFormatException e) {
//				e.printStackTrace();
//			}
//		};
//	}
//
//	@Bean
//	public CommandLineRunner createTestRide(IRegistrationService registrationService,
//											TicketstatusRepository ticketstatusRepository,
//											TemplateRepository templateRepository,
//											RideRepository rideRepository,
//											TicketRepository ticketRepository,
//											StopRepository stopRepository,
//											TransportRepository transportRepository){
//		return (args) -> {
//			// Carrier
//			User userCarrier = new User();
//			userCarrier.setUsername("cc");
//			userCarrier.setPassword("cc");
//			Carrier carrier = new Carrier();
//			carrier.setTitle("OOO TestCarrier");
//			registrationService.save(userCarrier, carrier);
//
//			// создать шаблон рейса
//			Template template = new Template();
//			template.setCountTickets(64);
//			template.setPrice(500.00f);
//			template.setTransport(transportRepository.findAll().get(1));
//			//template.setStops(stopRepository.findAll());
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
//			// создать шаблон рейса
//			Template template2 = new Template();
//			template2.setCountTickets(12);
//			template2.setPrice(8530.00f);
//			template2.setTransport(transportRepository.findAll().get(0));
//			//template2.setStops(stopRepository.findAll());
//			carrier.addChildTemplate(template2);
//			templateRepository.save(template2);
//
//			// добавить рейс по шаблону
//			Ride ride3 = new Ride();
//			ride3.setValid(true);
//			template2.addChildrenRide(ride3);
//			rideRepository.save(ride3);
//
//			// Passenger
//			User userPassenger = new User();
//			userPassenger.setUsername("pp");
//			userPassenger.setPassword("pp");
//			Passenger passenger = new Passenger();
//			passenger.setF_name("Рябова");
//			passenger.setL_name("Вероника");
//			passenger.setM_name("Наумовна");
//			passenger.setRating(4.71f);
//			registrationService.save(userPassenger, passenger);
//
//			Ticket ticket = new Ticket();
//			Ticketstatus ticketstatus = ticketstatusRepository.findByTitle("bought");
//			List<Integer> freeSeats = ride.getFreeSeats();
//			Integer seat = freeSeats.get(0);
//			ticket.Config(ride, passenger, seat, ticketstatus);
//
//			ticketRepository.save(ticket);
//		};
//	}

}