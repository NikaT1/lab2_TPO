package tpo.lab.integration;

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
import tpo.lab.logarithms.Ln;
import tpo.lab.logarithms.Log;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogTest {
    private static final Ln lnMock = Mockito.mock(Ln.class);
    private static final Ln ln = new Ln();

    @BeforeAll
    public static void setUpAll() throws IOException {
        Path log5Path = Path.of("src/test/resources/testData/testLog5.csv");
        Path log10Path = Path.of("src/test/resources/testData/testLog10.csv");
        Path dataPath = Path.of("src/test/resources/testData/testFuncPos.csv");
        CSVParser parser = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .build().parse(new FileReader(dataPath.toFile()));
        try (
                CSVPrinter log5Printer = CSVFormat.DEFAULT.print(new FileWriter(log5Path.toFile()));
                CSVPrinter log10Printer = CSVFormat.DEFAULT.print(log10Path, java.nio.charset.StandardCharsets.UTF_8);
        ) {
            parser.getRecords().forEach(record -> {
                double x = Double.parseDouble(record.get(0));
                try {
                    log5Printer.printRecord(x, Math.log(x) / Math.log(5));
                    log10Printer.printRecord(x, Math.log10(x));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Mockito.when(lnMock.calc(x, 0.00001)).thenReturn(ln.calc(x));

            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Mockito.when(lnMock.calc(5, 0.00001)).thenReturn(ln.calc(5));
        Mockito.when(lnMock.calc(10, 0.00001)).thenReturn(ln.calc(10));


    }


    @ParameterizedTest
    @CsvFileSource(resources = {"/testData/testLog5.csv"})
    public void integrationTestForLog5(Double x, Double log5) {
        Double expected = log5;
        Double actual = new Log(lnMock, 5).calc(x);

        assertEquals(expected, actual, 0.001);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/testData/testLog10.csv"})
    public void integrationTestForLog10(Double x, Double log10) {
        Double expected = log10;
        Double actual = new Log(lnMock, 10).calc(x);

        assertEquals(expected, actual, 0.001);
    }
}
