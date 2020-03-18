package com.lezgo.events.controllers;

import com.lezgo.events.models.EventReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EventReporterImplementation implements EventReporter {
    @Autowired
    private EventReporterService eventReporterService;

    @Transactional
    @RequestMapping(value = "/reportevent", method = RequestMethod.POST)
    public ResponseEntity<?> reportEvent(@RequestBody @Valid EventReportRequest eventReportRequest) {
        return eventReporterService.reportEvent(eventReportRequest);
    }
}
