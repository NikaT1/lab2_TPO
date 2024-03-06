package tpo.lab;

import java.io.IOException;
import java.nio.file.Path;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public interface Functions {
    final Double ACCURACY = 0.00001;

    default double calc(double x) {
        return calc(x, ACCURACY);
    }

    default double calc(double x, Path path) {
        double res = calc(x, ACCURACY);
        writeCsvFile(x, res, path);
        return res;
    }

    private void writeCsvFile(double x, double res, Path path) {
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(path, java.nio.charset.StandardCharsets.UTF_8)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
    }

    double calc(double x, double accuracy);

    default double calc(double x, double accuracy, Path path) {
        double res = calc(x, accuracy);
        writeCsvFile(x, res, path);
        return res;
    }
}
