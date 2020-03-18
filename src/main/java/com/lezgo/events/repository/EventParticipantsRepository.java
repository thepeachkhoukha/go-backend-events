package com.lezgo.events.repository;

import com.lezgo.events.entities.EventParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventParticipantsRepository extends JpaRepository<EventParticipant, Integer> {
    EventParticipant findByEventIdAndUsername(int eventId, String username);

    @Query(nativeQuery = true, value = "SELECT lep.id, lep.eventId, lep.username FROM lezgo_events le "+
            "INNER JOIN lezgo_events_participants lep "+
            "ON le.id = lep.eventId "+
            "WHERE le.city = :city")
    List<EventParticipant> getEventsParticipantsByCity(String city);

    void deleteByEventIdAndUsername(int eventId, String username);
}
