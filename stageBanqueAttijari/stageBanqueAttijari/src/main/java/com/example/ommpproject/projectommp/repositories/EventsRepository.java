package com.example.ommpproject.projectommp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ommpproject.projectommp.models.Events;

@Repository

public interface EventsRepository extends JpaRepository<Events, String> {

}
