package com.lezgo.events.controllers;

import com.lezgo.events.models.EventTopRequest;
import com.lezgo.events.models.EventsFetchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public interface EventFetcher {
    @RequestMapping(value = "/nearme", method = RequestMethod.POST)
    ResponseEntity<?> getEventsByLocation(@RequestBody @Valid EventsFetchRequest eventsFetchRequest);

    @RequestMapping(value = "/byusername", method = RequestMethod.GET)
    ResponseEntity<?> getEventsByOwnerUsername(@RequestParam @NotBlank String ownerUsername);

    @RequestMapping(value = "/participatedin", method = RequestMethod.GET)
    ResponseEntity<?> getEventsParticipatedInByUsername(@RequestParam @NotBlank String username);

    @RequestMapping(value = "/topevents", method = RequestMethod.POST)
    ResponseEntity<?> getTopEvents(@RequestBody @Valid EventTopRequest eventTopRequest);

    @RequestMapping(value = "/bydate", method = RequestMethod.POST)
    ResponseEntity<?> getEventsByDate(@RequestBody @Valid EventTopRequest eventTopRequest);

    @RequestMapping(value = "/today", method = RequestMethod.GET)
    ResponseEntity<?> getNumberEventsToday(@RequestParam @Valid String username);
}
