package ag.pst.assessment.christiandeleon.reader;

import java.io.FileReader;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;

import ag.pst.assessment.christiandeleon.model.CarFromCsv;

public class CsvReader {
    public static List<CarFromCsv> parse(Path path) throws Exception {
        try (CSVReader reader = new CSVReader(new FileReader(path.toFile()))) {
            List<String[]> lines = reader.readAll();
            return lines.stream().skip(1).map(line -> {
                CarFromCsv car = new CarFromCsv();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                car.setBrand(line[0]);
                car.setReleaseDate(LocalDate.parse(line[1], formatter));
                return car;
            }).collect(Collectors.toList());
        }
    }
}
