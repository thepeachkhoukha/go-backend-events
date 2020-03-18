package com.lezgo.events.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name= "lezgo_events_participants")
public class EventParticipant {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "eventId")
    private int eventId;

    @Column(name = "username")
    private String username;
}
