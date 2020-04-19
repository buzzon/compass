package ru.naumen.compass;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.naumen.compass.entity.Carrier;
import ru.naumen.compass.entity.Passenger;
import ru.naumen.compass.entity.Template;
import ru.naumen.compass.entity.User;
import ru.naumen.compass.repository.TemplateRepository;
import ru.naumen.compass.service.RegistrationService;
import ru.naumen.compass.service.RegistrationServiceImpl;

@SpringBootTest
class CompassApplicationTests {

	@Autowired
	RegistrationService registrationService;

	@Autowired
	TemplateRepository templateRepository;

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
		carrier.getTemplates().add(template);
		template.setCarrier(carrier);
		templateRepository.save(template);

		Template template1 = new Template();
		template1.setCountTickets(32);
		template1.setPrice(250.00f);
		template1.setCarrier(carrier);
		carrier.getTemplates().add(template1);
		templateRepository.save(template1);
	}
}
