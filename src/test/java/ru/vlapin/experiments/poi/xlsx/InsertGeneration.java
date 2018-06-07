package ru.alapina.experiments.poi.xlsx;

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
public class InsertGeneration {

    String mailType = "47"; // 47 посылка 1 класса, 4 посылка нестандартная,
    String mailCtg = "3";  // 3- без оц и нп, 2- с оц, 4- с оц и нп

    @Language("PostgreSQL")
    //строка с инсертом в базу данных
    static String SQL_PATTERN = "INSERT INTO \"RpoRegistration\" VALUES (643, '', 153013, %s, 643, 0, %s, 15, 2, 8, 100, null, 0, 'ЮЖНОБУТОВСКАЯ УЛ.,Д. 12,  157 КВ., МОСКВА Г., РЕГИОН МОСКВА Г., 117042', 'МАКСУДОВ', '', '', '', '', %s, 1, 1000, null, 'МАКСУДОВ', null, null, 'false', 'false', 'false', 'ZK-66710', 1485874240913, null, '117042', 'false', DEFAULT);%n";

    // файл с сгенерированными ШПИ
    static final String FILE_NAME = "/SHPI.txt";

    @SneakyThrows
    private static void doWithFileLines(String fileName, Consumer<String> stringConsumer) {
        @Cleanup
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        InsertGeneration.class.getResourceAsStream(fileName), UTF_8));
        String lineWithShpi;
        while ((lineWithShpi = reader.readLine()) != null)
            stringConsumer.accept(lineWithShpi);
    }

    //генерируем строки для инсертов в базу данных РПО для последующего тестирования выдачи
    @Test
    @DisplayName("doWithFileLines method works correctly")
    void testDoWithFileLines() throws IOException {
        PrintWriter outFileWithInserts = new PrintWriter(new File("C://insertToInRpoRegistration.txt"));
        doWithFileLines(FILE_NAME, line -> {
            if (line.length() > 6)
            // outFileWithInserts.printf(SQL_PATTERN, mailCtg, mailType, line);
             outFileWithInserts.printf(SQL_PATTERN, line);
        });
    }
}