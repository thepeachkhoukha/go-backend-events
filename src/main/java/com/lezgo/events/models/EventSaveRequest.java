package com.lezgo.events.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EventSaveRequest {
    private String ownerUsername;
    private String title;
    private String location;
    private String city;
    private String description;
    private String shortDescription;
    private String coverImage;
    private Timestamp date;
    private String startTime;
    private String endTime;
    private int threshold;
    private int maximum;
}
