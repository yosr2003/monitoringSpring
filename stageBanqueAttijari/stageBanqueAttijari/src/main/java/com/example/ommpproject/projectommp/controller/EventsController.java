package com.example.ommpproject.projectommp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ommpproject.projectommp.models.Events;
import com.example.ommpproject.projectommp.services.EventsService;
@CrossOrigin(origins = "*")

@RestController
public class EventsController {

	@Autowired
	private EventsService service;

	//Create
	@PostMapping("/addEvent")
	public Events addEvent(@RequestBody Events Events) {
		return service.saveEvent(Events);
	}



	//Read
	@GetMapping("/Events")
	public List<Events> findAllEvents() {
		System.out.println("Getting all events");
		return service.getAllEvents();
	}

	@GetMapping("/EventById/{EventId}")
	public Events findEventbyId(@PathVariable String EventId) {
		return service.getEventById(EventId);
	}



	//Update
	@PutMapping("/update")
	public Events updateEvent(@RequestBody Events e) {
		return service.updateUser(e);
	}
	


}
