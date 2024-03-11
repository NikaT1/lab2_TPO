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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CosTest {
    private static final Sin sinMock = Mockito.mock(Sin.class);

    @BeforeAll
    public static void setUpAll() throws IOException {
        Path cosPath = Path.of("src/test/resources/testData/testCos.csv");

        Path dataPath = Path.of("src/test/resources/testData/testFuncPos.csv");
        CSVParser parser = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .build().parse(new FileReader(dataPath.toFile()));
        try (CSVPrinter printer5 = CSVFormat.DEFAULT.print(new FileWriter(cosPath.toFile()));
        ) {
            parser.getRecords().forEach(record -> {
                double x = Double.parseDouble(record.get(0));
                try {
                    printer5.printRecord(x, Math.cos(x));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Mockito.when(sinMock.calc(x, 0.00001)).thenReturn(Math.sin(x));

            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @ParameterizedTest
    @CsvFileSource(resources = {"/testData/testCos.csv"})
    public void integrationTestForCos(Double x, Double cos) {
        Double expected = cos;
        Double actual = new Cos(sinMock).calc(x);

        assertEquals(expected, actual, 0.001);
    }
}
