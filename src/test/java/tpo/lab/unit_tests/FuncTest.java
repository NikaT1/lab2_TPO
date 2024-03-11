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
import tpo.lab.Func;
import tpo.lab.logarithms.Ln;
import tpo.lab.logarithms.Log;
import tpo.lab.trigonometry.Tg;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuncTest {
    private static final Log log5Mock = Mockito.mock(Log.class);
    private static final Log log10Mock = Mockito.mock(Log.class);
    private static final Tg tgMock = Mockito.mock(Tg.class);

    public static double log5(double x) {
        return Math.log(x) / Math.log(5);
    }

    public static double func(double x) {
        if (x <= 0) {
            return Math.tan(x);
        }
        return ((((Math.pow(Math.log10(x), 3) / Math.log(x)) * log5(x)) + (Math.log10(x) / Math.pow(log5(x), 3)) + (log5(x) + log5(x))));
    }

    @BeforeAll
    public static void setUp() throws IOException {
        Path funcPath = Path.of("src/test/resources/testData/testFunc.csv");
        Path negDataPath = Path.of("src/test/resources/testData/testFunNegative.csv");
        Path posDataPath = Path.of("src/test/resources/testData/testFuncPos.csv");
        CSVParser negParser = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .build().parse(new FileReader(negDataPath.toFile()));
        CSVParser posParser = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .build().parse(new FileReader(posDataPath.toFile()));
        try (CSVPrinter printer5 = CSVFormat.DEFAULT.print(new FileWriter(funcPath.toFile()));
        ) {
            negParser.getRecords().forEach(record -> {
                double x = Double.parseDouble(record.get(0));
                Mockito.when(tgMock.calc(x, 0.00001)).thenReturn(Math.tan(x));
                try {
                    printer5.printRecord(x, Math.tan(x));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            posParser.getRecords().forEach(record -> {
                double x = Double.parseDouble(record.get(0));
                Mockito.when(log5Mock.calc(x, 0.00001)).thenReturn(log5(x));
                Mockito.when(log10Mock.calc(x, 0.00001)).thenReturn(Math.log10(x));
                try {
                    printer5.printRecord(x, func(x));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/testData/testFunc.csv")
    public void integrationTestForCalc(Double x, Double func) {
        Double expected = func;
        Double actual = new Func(log5Mock, log10Mock, tgMock).calc(x);

        assertEquals(expected, actual, 0.001);
    }
}
