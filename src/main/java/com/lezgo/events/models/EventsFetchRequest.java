package com.lezgo.events.models;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
public class EventsFetchRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String city;

    @NotNull
    private int lng;

    @NotNull
    private int lat;

    @NotNull
    private int diameter;
}
