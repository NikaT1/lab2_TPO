package tpo.lab.unit_tests;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import tpo.lab.trigonometry.Cos;
import tpo.lab.trigonometry.Sin;
import tpo.lab.trigonometry.Tg;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TgTest {
    private static final Sin sinMock = Mockito.mock(Sin.class);
    private static final Cos cosMock = Mockito.mock(Cos.class);

    @BeforeAll
    public static void setUpAll() throws IOException {
        Path tgFilePath = Path.of("src/test/resources/testData/testTg.csv");
        Path dataPath = Path.of("src/test/resources/testData/testFunNegative.csv");
        CSVParser parser = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .build().parse(new FileReader(dataPath.toFile()));
        try (
                CSVPrinter tgPrinter = CSVFormat.DEFAULT.print(new FileWriter(tgFilePath.toFile()));
        ) {
            parser.getRecords().forEach(record -> {
                double x = Double.parseDouble(record.get(0));
                try {
                    tgPrinter.printRecord(x, Math.tan(x));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Mockito.when(sinMock.calc(x, 0.00001)).thenReturn(Math.sin(x));
                Mockito.when(cosMock.calc(x, 0.00001)).thenReturn(Math.cos(x));

            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @ParameterizedTest
    @CsvFileSource(resources = {"/testData/testTg.csv"})
    public void integrationTestForTan(Double x, Double cos) {
        double expected = cos;
        double actual = new Tg(sinMock, cosMock).calc(x);

        assertEquals(expected, actual, 0.001);
    }
}
