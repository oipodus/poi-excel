package ru.vlapin.experiments.poi.xlsx;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.Consumer;

import static java.nio.charset.StandardCharsets.UTF_8;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SelectGeneration {
    String mailType = "47"; // 47 посылка 1 класса, 4 посылка нестандартная,
    String mailCtg = "3";  // 3- без оц и нп, 2- с оц, 4- с оц и нп

    @Language("PostgreSQL")
    //строка с селектом в базу данных
    static String SQL_PATTERN= "SELECT * FROM \"RpoRegistration\" WHERE \"ShipmentId\" = '%s';";

    // файл с сгенерированными ШПИ
    static final String FILE_NAME = "/SHPI.txt";

    @SneakyThrows
    private static void doWithFileLines(String fileName, Consumer<String> stringConsumer) {
        @Cleanup
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                SelectGeneration.class.getResourceAsStream(fileName), UTF_8));
        String lineWithShpi;
        while ((lineWithShpi = reader.readLine()) != null)
            stringConsumer.accept(lineWithShpi);
    }
    //генерируем строки для селектов в базу данных РПО для последующего тестирования выдачи
    @Test
    @DisplayName("doWithFileLines method works correctly")
    void testDoWithFileLines1() throws IOException {
        PrintWriter outFileWithSelects = new PrintWriter(new File("C://SelectFromRpoRegistration33.txt"));
        doWithFileLines(FILE_NAME, line -> {
            if (line.length() > 6)
                outFileWithSelects.printf(SQL_PATTERN, line);
        });
    }
}

