package com.example.ommpproject.projectommp.models;

import javax.persistence.*;

@Entity
@Table(name = "event_type_mapping")
public class EventTypeMapping {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;
    public EventTypeMapping(Long id, EventType eventType, String description) {
		super();
		this.id = id;
		this.eventType = eventType;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EventTypeMapping() {
		super();
	}

	public EventTypeMapping(EventType eventType, String description) {
		super();
		this.eventType = eventType;
		this.description = description;
	}



	@Override
	public String toString() {
		return "EventTypeMapping [id=" + id + ", eventType=" + eventType + ", description=" + description + "]";
	}



	@Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType eventType;

    @Column(name = "description")
    private String description;

    // Constructors, getters, and setters
}

