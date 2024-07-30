package com.example.ommpproject.projectommp.repositories;



import com.example.ommpproject.projectommp.models.EventType;
import com.example.ommpproject.projectommp.models.EventTypeMapping;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventTypeMappingRepository extends JpaRepository<EventTypeMapping, Long> {
    EventTypeMapping findByEventType(EventType eventType);
}

