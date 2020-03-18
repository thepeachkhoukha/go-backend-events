package com.lezgo.events.controllers;

import com.lezgo.events.models.EventJoinRequest;
import com.lezgo.events.models.EventSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EventHandlerImplementation implements EventHandler {

    @Autowired
    private EventHandlerService eventHandlerService;

    @Transactional
    @RequestMapping(value = "/saveevent", method = RequestMethod.POST)
    public ResponseEntity<?> saveEvent(@RequestBody @Valid EventSaveRequest eventSaveRequest) {
        eventHandlerService.saveEvent(eventSaveRequest);
        return ResponseEntity.ok().body("event saved");
    }

    @Transactional
    @RequestMapping(value = "/joinevent", method = RequestMethod.POST)
    public ResponseEntity<?> joinEvent(@RequestBody @Valid EventJoinRequest eventJoinRequest) {
        eventHandlerService.joinEvent(eventJoinRequest);
        return ResponseEntity.ok().body("event saved");
    }
}
