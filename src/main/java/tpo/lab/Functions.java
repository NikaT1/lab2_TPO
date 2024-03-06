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

    default List<Double> calc(List<Double> x, Path path) {
        List<Double> res = x.stream().map(
                el -> calc(el, ACCURACY)
        ).toList();
        writeCsvFile(x, res, path);
        return res;
    }

    private void writeCsvFile(List<Double> x, List<Double> res, Path path) {
        try (CSVPrinter printer = CSVFormat.DEFAULT.builder().setHeader(new String[]{"X", "Результаты модуля (X)"}).build().print(path, java.nio.charset.StandardCharsets.UTF_8)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
    }

    double calc(double x, double accuracy);

    default List<Double> calc(List<Double> x, double accuracy, Path path) {
        List<Double> res = x.stream().map(
                el -> calc(el, accuracy)
        ).toList();
        writeCsvFile(x, res, path);
        return res;
    }
}
