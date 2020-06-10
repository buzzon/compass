package ru.project.compass.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.project.compass.entity.*;
import ru.project.compass.repository.RideRepository;
import ru.project.compass.repository.StopRepository;
import ru.project.compass.repository.TemplateRepository;
import ru.project.compass.repository.TransportRepository;
import ru.project.compass.service.SecurityService;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Controller
public class CarrierController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private StopRepository stopRepository;

    @ModelAttribute("Transports")
    public List<Transport> transports() {
        return transportRepository.findAll();
    }

    @ModelAttribute("ALLStops")
    public List<Stop> stops() {
        return stopRepository.findAll();
    }

    @GetMapping(value = "/carrier/tools")
    public String showCarrierTools(Model model) {
        model.addAttribute("User", securityService.getLogged());
        Template template =  new Template();
        template.addStop(stops().get(0));
        template.addStop(stops().get(1));
        model.addAttribute("Template", template);
        return "/carrier/tools";
    }

    @PostMapping(value = "/carrier/tools", params = "deleteTemplate")
    public String DeleteTemplate(@RequestParam(name = "templateId") Long id, Model model) {
        model.addAttribute("User", securityService.getLogged());
        Template template =  new Template();
        template.addStop(stops().get(0));
        template.addStop(stops().get(1));
        model.addAttribute("Template", template);

        templateRepository.deleteById(id);

        return "/carrier/tools";
    }

    @PostMapping(value = "/carrier/tools", params = "deleteRide")
    public String DeleteRide(@RequestParam(name = "rideId") Long id, Model model) {
        model.addAttribute("User", securityService.getLogged());
        Template template =  new Template();
        template.addStop(stops().get(0));
        template.addStop(stops().get(1));
        model.addAttribute("Template", template);

        rideRepository.deleteById(id);

        return "/carrier/tools";
    }

    @PostMapping(value = "/carrier/tools", params = "addRide")
    public String addRide(@RequestParam(name = "templateId") Long id,
                          @RequestParam(name = "departureDate") String departureDate,
                          @RequestParam(name = "returnDate") String returnDate,
                          Model model) throws ParseException, IOException, URISyntaxException {
        model.addAttribute("User", securityService.getLogged());
        Template template =  new Template();
        template.addStop(stops().get(0));
        template.addStop(stops().get(1));
        model.addAttribute("Template", template);

        if (templateRepository.findById(id).isPresent()){
            Template template1 = templateRepository.findById(id).get();

            // добавить рейс по шаблону в базу
		    Ride ride = new Ride();
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            ride.setDepartureDate(formatter1.parse(departureDate));
            ride.setReturnDate(formatter1.parse(returnDate));
			ride.setValid(true);
            template1.addChildrenRide(ride);
			rideRepository.save(ride);

            // добавим рейс в json
            var stops = ride.getTemplate().getStops();

            // открываем json
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("static/json/places.json");
            var places_str = "";

            // to string
            try (Reader reader = new InputStreamReader(resource.openStream(), UTF_8)) {
                places_str =  FileCopyUtils.copyToString(reader);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }

            JSONObject places = new JSONObject(places_str);
            JSONArray places_arr = places.getJSONArray("features");


            var stops_ = ride.getTemplate().getStops();
            JSONObject way = null;
            boolean way_already = false;
            for (int i = 0; i < places_arr.length(); i++) {
                JSONObject geometry = places_arr.getJSONObject(i).getJSONObject("geometry");
                // совпала начальная точка и конечная
                if (    geometry.getJSONArray("coordinates").get(0).toString().equals(stops_.get(0).getE().toString()) &&
                        geometry.getJSONArray("coordinates").get(1).toString().equals(stops_.get(0).getN().toString()) &&
                        geometry.getJSONArray("coordinatesto").get(0).toString().equals(stops_.get(1).getE().toString()) &&
                        geometry.getJSONArray("coordinatesto").get(1).toString().equals(stops_.get(1).getN().toString()))
                {

                    geometry.put("count", Integer.parseInt(geometry.get("count").toString()) + 1);
                    way_already = true;
                }
            }

            if (!way_already)
            {
                JSONArray coordinates = new JSONArray();
                coordinates.put(0,stops_.get(0).getE());
                coordinates.put(1,stops_.get(0).getN());

                JSONArray coordinatesto = new JSONArray();
                coordinatesto.put(0,stops_.get(1).getE());
                coordinatesto.put(1,stops_.get(1).getN());

                JSONObject geometry = new JSONObject();

                geometry.put("type", "Point");
                geometry.put("coordinates",coordinates);
                geometry.put("coordinatesto",coordinatesto);
                geometry.put("count", 1);

                JSONObject geometry_arr  = new JSONObject();
                geometry_arr.put("type", "Feature");
                geometry_arr.put("geometry",geometry);

                places_arr.put(geometry_arr);
            }

            //save
            File file = new File(resource.toURI().getPath());

            PrintWriter fr = new PrintWriter(file);
            fr.write(places.toString());
            fr.close();
        }

        return "/carrier/tools";
    }

    @PostMapping(value = "/carrier/tools", params = "addTemplate")
    public String AddTemplate(@ModelAttribute("Template") Template template, Model model) {
        User user = securityService.getLogged();
        user.getCarrier().addChildTemplate(template);

        if (transportRepository.findById(template.getTransport().getId()).isPresent())
            template.setTransport(transportRepository.findById(template.getTransport().getId()).get());

        List<Stop> stops = new LinkedList<>();
        for (int i = 0; i < template.getStops().size(); i++)
            if (stopRepository.findById(template.getStops().get(i).getId()).isPresent())
                stops.add(stopRepository.findById(template.getStops().get(i).getId()).get());
        template.setStops(stops);

        templateRepository.save(template);

        model.addAttribute("User", user);
        model.addAttribute("Template", template);

        return "/carrier/tools";
    }

    @PostMapping(value = "/carrier/tools", params = "addStop")
    public String showCarrierAddStop(@ModelAttribute("Template") Template template, Model model) {
        model.addAttribute("User", securityService.getLogged());
        template.addStop(stops().get(0));
        model.addAttribute("Template", template);
        return "/carrier/tools";
    }
}
