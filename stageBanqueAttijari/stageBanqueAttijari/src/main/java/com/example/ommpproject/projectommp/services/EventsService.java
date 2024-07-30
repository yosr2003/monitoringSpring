package com.example.ommpproject.projectommp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ommpproject.projectommp.models.Events;



@Service
public interface EventsService {
	public Events saveEvent(Events Event) ;
  
	public List<Events> getAllEvents();
  
    public Events getEventById(String EventId) ;
  
     
       
  
    public String deleteEvent(String id) ;
  
    public Events updateUser(Events Event) ;
}
