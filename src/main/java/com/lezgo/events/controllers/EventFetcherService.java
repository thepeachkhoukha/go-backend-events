package com.lezgo.events.controllers;

import com.lezgo.events.controllers.Util.FlatEarthDist;
import com.lezgo.events.entities.Event;
import com.lezgo.events.entities.EventParticipant;
import com.lezgo.events.models.EventsFetchRequest;
import com.lezgo.events.repository.EventParticipantsRepository;
import com.lezgo.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventFetcherService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventParticipantsRepository eventParticipantsRepository;

    public List<Event> getEventsByLocation(EventsFetchRequest eventsFetchRequest) {
        List<Event> events = eventRepository.findByCityContaining(eventsFetchRequest.getCity(), eventsFetchRequest.getUsername());

        return events.stream().filter(event -> {
            return FlatEarthDist.distance(event.getLat(),
                    event.getLng(),
                    eventsFetchRequest.getLat(),
                    eventsFetchRequest.getLng()) < eventsFetchRequest.getDiameter();
        }).collect(Collectors.toList());
    }

    public Optional<Event> getTopEvent(String city) {
        List<EventParticipant> allEventParticipants = eventParticipantsRepository.getEventsParticipantsByCity(city);
        Map<Integer, List<EventParticipant>> eventParticipants = allEventParticipants.stream().collect(Collectors.groupingBy(EventParticipant::getEventId));
        Map<Integer, Integer> eventPerNumberOfParticipants = new HashMap<>();
        for (Integer eventId : eventParticipants.keySet()) {
            eventPerNumberOfParticipants.put(eventId, eventParticipants.get(eventId).size());
        }
        int max = 0;
        Integer maxEventId = null;
        for (Integer eventId : eventPerNumberOfParticipants.keySet()) {
            if (eventPerNumberOfParticipants.get(eventId) >= max) {
                max = eventPerNumberOfParticipants.get(eventId);
                maxEventId = eventId;
            }
        }

        if (maxEventId != null) {
            return eventRepository.findById(maxEventId);
        }
        return null;
    }

    public long getNumberEventsToday(String username) {
        List<Event> events = eventRepository.findParticipatedInEventsByUsername(username);
        Timestamp today = new Timestamp((new Date()).getTime());

        return events.stream().filter(event -> event.getDate().getDay() == today.getDay() &
                event.getDate().getMonth() == today.getMonth() &
                event.getDate().getYear() == today.getYear()).count();
    }

    public List<Event> getEventsByOwnerUsername(String ownerUsername) {
        return eventRepository.findByOwnerUsername(ownerUsername);
    }

    public List<Event> getEventsParticipatedInByUsername(String username) {
        return eventRepository.findParticipatedInEventsByUsername(username);
    }
}
