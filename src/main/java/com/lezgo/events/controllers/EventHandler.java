package com.lezgo.events.controllers;

import com.lezgo.events.models.EventJoinRequest;
import com.lezgo.events.models.EventSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface EventHandler {
    @RequestMapping(value = "/saveevent", method = RequestMethod.POST)
    ResponseEntity<?> saveEvent(@RequestBody EventSaveRequest eventSaveRequest);

    @RequestMapping(value = "/joinevent", method = RequestMethod.POST)
    ResponseEntity<?> joinEvent(@RequestBody EventJoinRequest eventJoinRequest);
}
