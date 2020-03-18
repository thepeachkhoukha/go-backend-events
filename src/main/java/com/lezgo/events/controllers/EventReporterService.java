package com.lezgo.events.controllers;

import com.lezgo.events.entities.EventReport;
import com.lezgo.events.models.EventReportRequest;
import com.lezgo.events.repository.EventParticipantsRepository;
import com.lezgo.events.repository.EventReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventReporterService {
    @Autowired
    private EventReportRepository eventReportRepository;

    @Autowired
    private EventParticipantsRepository eventParticipantsRepository;

    @Transactional
    public ResponseEntity<?> reportEvent(EventReportRequest eventReportRequest) {
        if (eventReportRepository.findByEventIdAndUsername(eventReportRequest.getEventId(), eventReportRequest.getUsername()) == null) {
            EventReport eventReport = EventReport.builder()
                    .username(eventReportRequest.getUsername())
                    .eventId(eventReportRequest.getEventId())
                    .reason(eventReportRequest.getReason())
                    .build();
            eventParticipantsRepository.deleteByEventIdAndUsername(eventReportRequest.getEventId(), eventReportRequest.getUsername());
            eventReportRepository.save(eventReport);
            return ResponseEntity.ok().body("report added");
        } else {
            return ResponseEntity.badRequest().body("this event was already reported by you");
        }
    }
}

