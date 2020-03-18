package com.lezgo.events.controllers;

import com.lezgo.events.models.EventTopRequest;
import com.lezgo.events.models.EventsFetchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EventFetcherImplementation implements EventFetcher {
    @Autowired
    private EventFetcherService eventFetcherService;

    @RequestMapping(value = "/nearme", method = RequestMethod.POST)
    public ResponseEntity<?> getEventsByLocation(@RequestBody @Valid EventsFetchRequest eventsFetchRequest) {
        return ResponseEntity.ok().body(eventFetcherService.getEventsByLocation(eventsFetchRequest));
    }

    @RequestMapping(value = "/byusername", method = RequestMethod.GET)
    public ResponseEntity<?> getEventsByOwnerUsername(@RequestParam String ownerUsername) {
        return ResponseEntity.ok().body(eventFetcherService.getEventsByOwnerUsername(ownerUsername));
    }

    @RequestMapping(value = "/participatedin", method = RequestMethod.GET)
    public ResponseEntity<?> getEventsParticipatedInByUsername(@RequestParam String username) {
        return ResponseEntity.ok().body(eventFetcherService.getEventsParticipatedInByUsername(username));
    }

    @RequestMapping(value = "/topevents", method = RequestMethod.POST)
    public ResponseEntity<?> getTopEvents(@RequestBody @Valid EventTopRequest eventTopRequest) {
        return ResponseEntity.ok().body(eventFetcherService.getTopEvent(eventTopRequest.getCity()));
    }

    @RequestMapping(value = "/bydate", method = RequestMethod.POST)
    public ResponseEntity<?> getEventsByDate(@RequestBody @Valid EventTopRequest eventTopRequest){
        return null;
    }

    @RequestMapping(value = "/today", method = RequestMethod.GET)
    public ResponseEntity<?> getNumberEventsToday(@RequestParam @Valid String username){
        return ResponseEntity.ok(eventFetcherService.getNumberEventsToday(username));
    }
}
