package tpo.lab;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuncTest {
    public double log5(double x) {
        return Math.log(x) / Math.log(5);
    }

    public double func(double x) {
        if (x <= 0) {
            return Math.tan(x);
        }
        return ((((Math.pow(Math.log10(x), 3) / Math.log(x)) * log5(x)) + (Math.log10(x) / Math.pow(log5(x), 3)) + (log5(x) + log5(x))));
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/testData/testFuncPos.csv")
    public void calc_shouldCorrectlyCalcFunction(double x) {
        double expected = func(x);
        double actual = new Func().calc(x);

        assertEquals(expected, actual, 0.01);
    }
}
