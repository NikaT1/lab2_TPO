package tpo.lab;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public interface Functions {
     Double ACCURACY = 0.00001;

    default double calc(double x) {
        return calc(x, ACCURACY);
    }

    default double calc(double x, CSVPrinter printer) {
        double res = calc(x, ACCURACY);
        writeCsvFile(x, res, printer);
        return res;
    }

    private void writeCsvFile(double x, double res, CSVPrinter printer) {
        try {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Error during printing record");
        }
    }

    double calc(double x, double accuracy);

    default double calc(double x, double accuracy, CSVPrinter printer) {
        double res = calc(x, accuracy);
        writeCsvFile(x, res, printer);
        return res;
    }
}
