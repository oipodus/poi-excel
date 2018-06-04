package ru.vlapin.experiments.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@JsonInclude(NON_NULL)
@FieldDefaults(level = PRIVATE)
public class ShpiResponse {
    boolean success;
    String[] barcodes;
}
