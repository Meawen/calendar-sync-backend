package com.calendarsyncbackend.repositories;

import com.calendarsyncbackend.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}