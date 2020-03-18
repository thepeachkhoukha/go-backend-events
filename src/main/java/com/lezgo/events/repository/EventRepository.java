package com.lezgo.events.repository;

import com.lezgo.events.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM lezgo_events e  " +
            "LEFT JOIN lezgo_event_reports re  " +
            "ON re.eventId = e.id " +
            "WHERE (re.username is null OR re.username != :username) AND e.city = :city")
    List<Event> findByCityContaining(String city, String username);

    List<Event> findByOwnerUsername(String ownerUsername);

    @Query(nativeQuery = true, value = "SELECT * FROM lezgo_events e  " +
            "INNER JOIN lezgo_events_participants se  " +
            "ON se.eventId = e.id " +
            "WHERE se.username = :username")
    List<Event> findParticipatedInEventsByUsername(String username);
}
