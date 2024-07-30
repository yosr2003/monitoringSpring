package com.example.ommpproject.projectommp.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ommpproject.projectommp.models.Events;
import com.example.ommpproject.projectommp.repositories.EventsRepository;
import com.example.ommpproject.projectommp.services.EventsService;


@Service
public class EventsServiceImpl implements EventsService {
	  @Autowired
	    private EventsRepository repository;

	@Override
	public Events saveEvent(Events Event) {
		// TODO Auto-generated method stub
		return this.repository.save(Event);
	}

	@Override
	public List<Events> getAllEvents() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Events getEventById(String EventId) {
		// TODO Auto-generated method stub
	    return repository.findById(EventId).orElse(null);
	}

	@Override
	public String deleteEvent(String id) {
		// TODO Auto-generated method stub
		 repository.deleteById(id);
	     return "Event removed !! " + id;
	}

	@Override
	public Events updateUser(Events Event) {
		// TODO Auto-generated method stub
		return null;
	}


}
