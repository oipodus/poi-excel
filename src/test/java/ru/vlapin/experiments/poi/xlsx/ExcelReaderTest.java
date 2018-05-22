package ru.vlapin.experiments.poi.xlsx;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ExcelReaderTest {

    @Test
    @DisplayName("toMap method works correctly")
    void testToMap() {
        Map<String, Double> sheet = ExcelReader.toMap("/Countries-with-limits.xlsx", "Sheet2");
        assertThat(sheet.get("АБХАЗИЯ"), is(4_000d));
    }
}