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
//	public CommandLineRunner createTestRide(IRegistrationService registrationService){
//		return (args) -> {
//
//			// Carrier
//			User userCarrier = new User();
//			userCarrier.setUsername("cc");
//			userCarrier.setPassword("cc");
//			Carrier carrier = new Carrier();
//			carrier.setTitle("OOO TestCarrier");
//			registrationService.save(userCarrier, carrier);
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
//		};
//	}

}