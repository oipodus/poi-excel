
package ru.vlapin.experiments.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@JsonInclude(NON_NULL)
@FieldDefaults(level = PRIVATE)
public class AddressData {
    String category;
    String name;
    String phoneNumber;
    String inn;
    Object kpp;

    @Valid
    Address address;
}
