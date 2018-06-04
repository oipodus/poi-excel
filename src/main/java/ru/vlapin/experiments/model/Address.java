package ru.vlapin.experiments.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@JsonInclude(NON_NULL)
@FieldDefaults(level = PRIVATE)
public class Address {
    String addressType;
    String postalCode;
    long country;
    String region;
    String area;
    String place;
    String street;
    String house;
    Object letter;
    Object slash;
    String corpus;
    String building;
    String room;
    Object numAddress;
    Object hotel;
}
