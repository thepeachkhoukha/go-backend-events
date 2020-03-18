package com.lezgo.events.repository;

import com.lezgo.events.entities.EventReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventReportRepository extends JpaRepository<EventReport, Integer> {
    EventReport findByEventIdAndUsername(int eventId, String username);
}
