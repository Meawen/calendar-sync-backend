package com.calendarsyncbackend.services;

import com.calendarsyncbackend.models.Event;
import com.calendarsyncbackend.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    public Optional<Event> getEventById(int id) {
        return eventRepository.findById(id);
    }
    public Page<Event> getEventsPageable(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
    public Event updateEvent(int id, Event event) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            eventOptional.get().setName(event.getName());
            eventOptional.get().setDescription(event.getDescription());
            eventOptional.get().setDatetimeFrom(event.getDatetimeFrom());
            eventOptional.get().setDatetimeTo(event.getDatetimeTo());
            return eventRepository.save(eventOptional.get());
        }
        System.out.println("Something went wrong, id not found");
        return null;
    }
    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }
}
