package com.example.ommpproject.projectommp.models;

import java.time.LocalDateTime;

import javax.persistence.*;
import com.fasterxml.jackson.databind.JsonNode; // Importer la classe JsonNode de Jackson

@Entity
@Table(name = "events")
public class Events {

    @Id
    @Column(name = "event_id")
    private String eventId;

    @Column(name = "aggregate_id")
    private String aggregateId;

    @Column(name = "event_data", columnDefinition = "jsonb")
    @Convert(converter = JsonConverter.class) // Converter for JSON field
    private JsonNode eventData;

    public Events() {
		super();
	}

	public Events(String eventId, String aggregateId, JsonNode eventData, EventType eventType,
			LocalDateTime timestamp) {
		super();
		this.eventId = eventId;
		this.aggregateId = aggregateId;
		this.eventData = eventData;
		this.eventType = eventType;
		this.timestamp = timestamp;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(String aggregateId) {
		this.aggregateId = aggregateId;
	}

	public JsonNode getEventData() {
		return eventData;
	}

	public void setEventData(JsonNode eventData) {
		this.eventData = eventData;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Events [eventId=" + eventId + ", aggregateId=" + aggregateId + ", eventData=" + eventData
				+ ", eventType=" + eventType + ", timestamp=" + timestamp + "]";
	}

	@Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType eventType;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // Constructors, getters, and setters
}

