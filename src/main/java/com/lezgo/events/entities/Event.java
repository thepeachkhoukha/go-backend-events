package com.lezgo.events.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name= "lezgo_events")
public class Event {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "ownerUsername")
    @NotBlank(message = "owner username can't be blank")
    private String ownerUsername;

    @Column(name = "title")
    @NotBlank(message = "title can't be blank")
    private String title;

    @Column(name = "description")
    @NotBlank(message = "description can't be blank")
    private String description;

    @Column(name = "shortDescription")
    @NotBlank(message = "short description can't be blank")
    private String shortDescription;

    @Column(name = "coverImage")
    @NotBlank(message = "cover image can't be blank")
    private String coverImage;

    @Column(name = "location")
    @NotBlank(message = "location can't be blank")
    private String location;

    @Column(name = "city")
    @NotBlank(message = "city can't be blank")
    private String city;

    @Column(name = "lng")
    private double lng;

    @Column(name = "lat")
    private double lat;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "startTime")
    @NotBlank(message = "start time can't be blank")
    private String startTime;

    @Column(name = "endTime")
    @NotBlank(message = "end time can't be blank")
    private String endTime;

    @Column(name = "threshold")
    private int threshold;

    @Column(name = "maximum")
    private int maximum;
}