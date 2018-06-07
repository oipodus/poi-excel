package ru.vlapin.experiments.poi.xlsx;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Consumer;

import static java.nio.charset.StandardCharsets.UTF_8;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class InsertGeneration {

    static String SQL_PATTERN = "INSERT INTO \"RpoRegistration\" VALUES (643, '',\";%s;\", \";C4;\" , 643, 0,\";B4;\" , 15, 2, 8, 0, null, 0, 'ЮЖНОБУТОВСКАЯ УЛ.,Д. 12,  157 КВ., МОСКВА Г., РЕГИОН МОСКВА Г., 117042', 'МАКСУДОВ', '', '', '', '', '\";D4;\"', 1, 0, null, 'МАКСУДОВ', null, null, 'false', 'false', 'false', 'ZK-66710', 1485874240913, null, '117042', 'false', DEFAULT);\"%n";
    static final String FILE_NAME = "/SHPI.txt";

    @SneakyThrows
    private static void doWithFileLines(String fileName, Consumer<String> stringConsumer) {
        @Cleanup
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        InsertGeneration.class.getResourceAsStream(fileName), UTF_8));
        String line;
        while ((line = reader.readLine()) != null)
            stringConsumer.accept(line);
    }

    @Test
    @DisplayName("doWithFileLines method works correctly")
    void testDoWithFileLines() {
        doWithFileLines(FILE_NAME, line -> {
            // Здесь пиши что надо делать с этой самой линией,
            // например что бы вывести на консоль как ты выводила:
            System.out.printf(SQL_PATTERN, line);
        });
    }
}
