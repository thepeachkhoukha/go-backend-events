package com.lezgo.events.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name= "lezgo_event_reports")
public class EventReport {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "eventId")
    private int eventId;

    @Column(name = "reason")
    private String reason;
}
