package ru.vlapin.experiments.poi.xlsx;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

class ExcelReaderTest {

    static Map<String, Double> sheet;
    static Map<String, List<String>> sheetLists;

    @BeforeAll
    static void setUp() {
        sheet = ExcelReader.toMap("/Countries-with-limits.xlsx", "Sheet2");
        sheetLists = ExcelReader.toListMap("/Weights_Limits.xlsx", "Sheet2");
    }

    private static Stream<String> createWordsWithLength() {
        Map<String, Double> sheet = ExcelReader.toMap(
                "/Countries-with-limits.xlsx",
                "Sheet2");

        return sheet.entrySet().stream()
                .map(stringDoubleEntry ->
                        "\"" + stringDoubleEntry.getKey().trim() + ", "
                                + stringDoubleEntry.getValue() + "\"");
    }

    @ParameterizedTest
    @DisplayName("toMap method works correctly")
    @CsvSource({"ЧЕХИЯ, 4000.0", "ИСЛАНДИЯ, 4000.0", "БЕНИН, 512.0", "КАТАР, 1886.0",
            "ОБЪЕДИНЕННЫЕ АРАБСКИЕ ЭМИРАТЫ, 4000.0", "КИТАЙ, 838.0", "СВАЗИЛЕНД, 417.0",
            "БУРКИНА-ФАСО, 667.0", "ЕГИПЕТ, 1250.0", "ХОРВАТИЯ, 4000.0",
            "НИДЕРЛАНДСКИЕ АНТИЛЛЫ, 1826.79", "НИДЕРЛАНДЫ, 4000.0", "АВСТРАЛИЯ, 2500.0",
            "ИСПАНИЯ, 2668.0", "ТУНИС, 2275.0", "УЗБЕКИСТАН, 4000.0", "ФРАНЦУЗСКАЯ ПОЛИНЕЗИЯ, 3543.0",
            "БОСНИЯ и ГЕРЦЕГОВИНА, 4000.0", "МАКЕДОНИЯ, 1633.0", "БАРБАДОС, 163.3",
            "МАЛИ, 631.3367", "АВСТРИЯ, 4000.0", "ТУРКМЕНИСТАН, 500.0",
            "ЦЕНТРАЛЬНО-АФРИКАНСКАЯ РЕСПУБЛИКА, 130.0", "ГАБОН, 430.0", "ПОЛЬША, 4000.0",
            "ВЕНГРИЯ, 4000.0", "ГВИНЕЯ, 650.0", "КОРЕЯ  (РЕСПУБЛИКА), 4000.0", "БРАЗИЛИЯ, 6340.87",
            "АРГЕНТИНА, 1200.0", "РУМЫНИЯ, 4000.0", "ФИНЛЯНДИЯ, 4000.0",
            "ФОЛКЛЕНДСКИЕ (МАЛЬВИНСКИЕ) острова, 300.0", "ТОГО, 1625.0", "МОНГОЛИЯ, 326.0",
            "ГАНА, 2000.0", "БРУНЕЙ, 130.0", "КОНГО (РЕСПУБЛИКА), 400.0", "КОТ-Д`ИВУАР, 4375.0",
            "ЭСТОНИЯ, 4000.0", "МАКАО, 2.807", "ТРИНИДАД и ТОБАГО, 689.0", "ЗАМБИЯ, 400.0",
            "ДОМИНИКА, 40.0", "КЕНИЯ, 1886.0", "БОТСВАНА, 400.0", "КИПР, 4000.0", "СИНГАПУР, 2000.0",
            "САН-ТОМЕ  и  ПРИНСИПИ, 4000.0", "ТАНЗАНИЯ, 170.0", "БЕЛАРУСЬ, 900.0", "ЧАД, 678.42",
            "ТУВАЛУ, 3500.0", "КАЗАХСТАН, 4000.0", "ЯПОНИЯ, 14558.0",
            "ЮЖНО-АФРИКАНСКАЯ РЕСПУБЛИКА, 4000.0", "КУВЕЙТ, 1322.2", "АЗЕРБАЙДЖАН, 2000.0",
            "НОВАЯ КАЛЕДОНИЯ, 1379.0", "МОНАКО, 4000.0", "СЕНЕГАЛ, 500.0", "САН-МАРИНО, 1633.0",
            "БЕЛЬГИЯ, 4000.0", "ЧЕРНОГОРИЯ, 4000.0", "БЕЛИЗ, 1187.85", "АНТИГУА и БАРБУДА, 48.02",
            "ПАКИСТАН, 400.0", "ГОНКОНГ, 4000.0", "ДАНИЯ, 4000.0", "ИНДИЯ, 1000.0",
            "КЫРГЫЗСТАН, 1000.0", "ТАДЖИКИСТАН, 4000.0", "ТАИЛАНД, 1000.0", "ШВЕЦИЯ, 1000.0",
            "АЛЖИР, 80.0", "ЛИТВА, 1000.0", "АБХАЗИЯ, 4000.0", "ФИДЖИ, 263.0", "ГВАДЕЛУПА, 4000.0",
            "ИТАЛИЯ, 1633.0", "ГРЕЦИЯ, 4000.0", "СВЯТОЙ  ЕЛЕНЫ остров, 126.49", "ГРУЗИЯ, 1000.0",
            "МАЛАЙЗИЯ, 550.0", "ЛАТВИЯ, 1000.0", "БОЛГАРИЯ, 765.0", "НАМИБИЯ, 200.0",
            "ПАПУА - НОВАЯ  ГВИНЕЯ, 120.0", "ВАТИКАН, 1633.0", "НОВАЯ ЗЕЛАНДИЯ, 800.0",
            "РЕЮНЬОН, 4000.0", "СЛОВЕНИЯ, 4000.0", "КАБО-ВЕРДЕ, 327.0", "ГЕРМАНИЯ, 400.0",
            "ДОМИНИКАНСКАЯ РЕСПУБЛИКА, 30.0", "МАРТИНИКА, 4000.0", "ДЖИБУТИ, 1000.0",
            "КОЛУМБИЯ, 1307.0", "ШВЕЙЦАРИЯ, 1000.0", "МОЛДОВА, 2000.0", "КОМОРСКИЕ острова, 3615.0",
            "США, 3448.0", "СИРИЯ, 4000.0", "БАНГЛАДЕШ, 200.0", "МАДАГАСКАР, 136.23", "КНДР, 3266.91",
            "ШРИ-ЛАНКА, 12.0", "АНГИЛЬЯ, 466.0", "БУРУНДИ, 4000.0", "АРМЕНИЯ, 4000.0",
            "БАГАМСКИЕ острова, 1321.19", "ИРЛАНДИЯ, 83.0", "ФРАНЦИЯ, 3475.0", "СЕРБИЯ, 4000.0",
            "УКРАИНА, 4000.0", "МАРОККО, 3842.0", "ИРАН, 400.0", "ТУРЦИЯ, 653.0"})
    void testMap(String name, Double value) {
//        System.out.println(createWordsWithLength().collect(Collectors.joining(", ")));
        assertThat(sheet.get(name), is(value));
    }

    @ParameterizedTest
    @DisplayName("toListMap method works correctly")
    @ValueSource(strings = {"АБХАЗИЯ", "АВСТРАЛИЯ", "АЛБАНИЯ", "МАДАГАСКАР"})
    void testToListMap(String countryName) {
        assertThat(sheetLists.get("Country"), hasItem(countryName));
    }
}