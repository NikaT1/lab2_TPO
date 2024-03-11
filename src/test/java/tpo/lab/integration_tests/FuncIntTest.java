package tpo.lab.integration_tests;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import tpo.lab.Func;
import tpo.lab.logarithms.Ln;
import tpo.lab.logarithms.Log;
import tpo.lab.trigonometry.Cos;
import tpo.lab.trigonometry.Sin;
import tpo.lab.trigonometry.Tg;

import java.io.IOException;
import java.nio.file.Path;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuncIntTest {
    private final Sin sin = new Sin();

    private final Ln ln = new Ln();

    private static CSVPrinter printer;

    private static final Path funcPath = Path.of("src/test/resources/outData/Func.csv");

    @BeforeAll
    public static void setUp(){
        try {
            printer = CSVFormat.DEFAULT.builder().setHeader(new String[]{"X", "Результаты модуля (X)"}).build().print(funcPath, java.nio.charset.StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/testCos.csv")
    public void integrationTestForCos(Double x, Double cosX) {
        Double expected = cosX;
        Double actual = new Cos(sin).calc(x);

        assertEquals(expected, actual, 0.2*abs(x));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/testTg.csv")
    public void integrationTestForTg(Double x, Double tgX) {
        Double expected = tgX;
        Double actual = new Tg(sin, new Cos(sin)).calc(x);

        assertEquals(expected, actual, 0.2*abs(x));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/testLog5.csv")
    public void integrationTestForLog5(Double x, Double logX) {
        Double expected = logX;
        Double actual = new Log(ln, 5).calc(x);

        assertEquals(expected, actual, 0.2*abs(x));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/testLog10.csv")
    public void integrationTestForLog10(Double x, Double logX) {
        Double expected = logX;
        Double actual = new Log(ln, 10).calc(x);

        assertEquals(expected, actual, 0.2*abs(x));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/testFunc.csv")
    public void integrationTestForCalc(Double x, Double func) {
        Double expected = func;
        Log log10 = new Log(ln, 10);
        Log log5 = new Log(ln, 5);
        Tg tg = new Tg(sin, new Cos(sin));
        Double actual = new Func(log5, log10, tg).calc(x, printer);
        assertEquals(expected, actual, 0.2*abs(x));
    }
}
