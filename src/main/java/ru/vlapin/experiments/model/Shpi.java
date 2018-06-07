package ru.vlapin.experiments.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@JsonInclude(NON_NULL)
@FieldDefaults(level = PRIVATE)
public class Shpi {

    @Valid
    @Default
    ClientData clientData = ClientData.builder().build();

    @Default
    long requiredCount = 1;
}
