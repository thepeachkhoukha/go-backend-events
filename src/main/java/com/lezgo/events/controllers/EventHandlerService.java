package com.lezgo.events.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lezgo.events.controllers.Util.Coordinates;
import com.lezgo.events.entities.Event;
import com.lezgo.events.entities.EventParticipant;
import com.lezgo.events.models.EventJoinRequest;
import com.lezgo.events.models.EventSaveRequest;
import com.lezgo.events.repository.EventParticipantsRepository;
import com.lezgo.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class EventHandlerService {

    private final String KEY = "7ed7d48183da57";

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventParticipantsRepository eventParticipantsRepository;

    @Transactional
    public void saveEvent(EventSaveRequest eventSaveRequest) {
        Coordinates eventCoordinate = getCoordinates(eventSaveRequest.getLocation());
        Event event = Event.builder()
                .ownerUsername(eventSaveRequest.getOwnerUsername())
                .title(eventSaveRequest.getTitle())
                .description(eventSaveRequest.getDescription())
                .shortDescription(eventSaveRequest.getShortDescription())
                .location(eventSaveRequest.getLocation())
                .city(eventSaveRequest.getCity())
                .lat(eventCoordinate.getLat())
                .lng(eventCoordinate.getLng())
                .coverImage(eventSaveRequest.getCoverImage())
                .date(eventSaveRequest.getDate())
                .startTime(eventSaveRequest.getStartTime())
                .endTime(eventSaveRequest.getEndTime())
                .threshold(eventSaveRequest.getThreshold())
                .maximum(eventSaveRequest.getMaximum())
                .build();
        eventRepository.save(event);
    }

    @Transactional
    public void joinEvent(EventJoinRequest eventJoinRequest) {
        if (this.eventParticipantsRepository.findByEventIdAndUsername(eventJoinRequest.getEventId(), eventJoinRequest.getUsername()) == null) {
            EventParticipant eventParticipant = EventParticipant.builder()
                    .eventId(eventJoinRequest.getEventId())
                    .username(eventJoinRequest.getUsername())
                    .build();
            eventParticipantsRepository.save(eventParticipant);
        }
    }

    private Coordinates getCoordinates(String address) {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper
                    .writeValueAsString(address);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String link = "https://eu1.locationiq.com/v1/search.php?key=" + KEY + "&q='" + sanitizing(address) + "'&format=json";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .GET()
                .headers("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray inputArray = new JSONArray(response.body());
            JSONObject jo = inputArray.getJSONObject(0);

            return new Coordinates(Double.parseDouble(jo.get("lat").toString()), Double.parseDouble(jo.get("lon").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Coordinates(0, 0);
    }

    //replaces all spaces with + sign
    private String sanitizing(String address) {
        return address.replaceAll(" ", "+");
    }
}
