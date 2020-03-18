package com.lezgo.events.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EventReportRequest {
    @NotBlank
    private String username;

    @NotNull
    private int eventId;

    @NotBlank
    private String reason;
}
