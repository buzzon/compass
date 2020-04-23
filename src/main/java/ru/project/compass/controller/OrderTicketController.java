package ru.project.compass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.project.compass.entity.Passenger;
import ru.project.compass.entity.Ride;
import ru.project.compass.entity.Ticket;
import ru.project.compass.entity.Ticketstatus;
import ru.project.compass.repository.RideRepository;
import ru.project.compass.repository.TicketRepository;
import ru.project.compass.repository.TicketstatusRepository;
import ru.project.compass.service.SecurityService;

import java.util.List;

@Controller
public class OrderTicketController {

    @Autowired
    private TicketstatusRepository ticketstatusRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping(value = {"orderTicket"})
    public String orderTicket(@RequestParam(name = "rideId") Long id){
        Passenger passenger = securityService.getLogged().getPassenger();

        Ticket ticket = new Ticket();
        Ticketstatus ticketstatus = ticketstatusRepository.findByTitle("bought");

        Ride ride = rideRepository.findById(id).get();
        List<Integer> freeSeats = ride.getFreeSeats();
        if (freeSeats.size() > 0){
            Integer seat = freeSeats.get(0);
            ticket.Config(ride, passenger, seat, ticketstatus);
            ticketRepository.save(ticket);
        }

        return "redirect:/";
    }
}
