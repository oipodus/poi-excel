package ru.vlapin.experiments.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ShPI {
    // {
    //    "success": true,
    //    "barcodes": [
    //        "19734223000797"
    //    ]
    //}
    boolean success;
    String[] barcodes;
}
