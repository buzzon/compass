package ru.project.compass;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.project.compass.entity.*;
import ru.project.compass.repository.RideRepository;
import ru.project.compass.repository.TemplateRepository;
import ru.project.compass.repository.TicketstatusRepository;
import ru.project.compass.service.IRegistrationService;

import java.util.List;

@SpringBootTest
class CompassApplicationTests {

	@Autowired
	IRegistrationService registrationService;

	@Autowired
	TemplateRepository templateRepository;

	@Autowired
	RideRepository rideRepository;

	@Autowired
	TicketstatusRepository ticketstatusRepository;

	@Test
	void AddCarrier_And_CreateRide() {
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

		// новый юзер
		User user2 = new User();
		user2.setUsername("Ted");
		user2.setPassword("123");
		Passenger passenger = new Passenger();
		passenger.setM_name("m_name");
		passenger.setF_name("f_name");
		passenger.setL_name("l_name");
		passenger.setRating(4.89f);
		registrationService.save(user2, passenger);

		Ticket ticket = new Ticket();
		Ticketstatus ticketstatus = ticketstatusRepository.findByTitle("bought");
		List<Integer> freeSeats = ride.getFreeSeats();
		Integer seat = freeSeats.get(0);
		ticket.Config(ride, passenger, seat, ticketstatus);
	}
}
