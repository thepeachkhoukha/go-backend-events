package com.lezgo.events.controllers;

import com.lezgo.events.models.EventReportRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface EventReporter {
    @RequestMapping(value = "/reportEvent", method = RequestMethod.POST)
    ResponseEntity<?> reportEvent(@RequestBody @Valid EventReportRequest eventReportRequest);
}
