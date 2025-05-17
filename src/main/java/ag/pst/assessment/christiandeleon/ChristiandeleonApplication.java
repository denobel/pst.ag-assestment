package ag.pst.assessment.christiandeleon;

import java.nio.file.Paths;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ag.pst.assessment.christiandeleon.model.CarFromCsv;
import ag.pst.assessment.christiandeleon.CliParams;
import ag.pst.assessment.christiandeleon.model.CarFromXml;
import ag.pst.assessment.christiandeleon.reader.CsvReader;
import ag.pst.assessment.christiandeleon.reader.XmlReader;
import ag.pst.assessment.christiandeleon.service.CarFilterService;
import ag.pst.assessment.christiandeleon.service.CarOutputService;

@SpringBootApplication
public class ChristiandeleonApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ChristiandeleonApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
        CliParams parsedArgs = CliParams.parse(args);

        if (parsedArgs.hasCsvInput()) {
            List<CarFromCsv> csvCars = CsvReader.parse(Paths.get("src/main/resources/CarsBrand.csv"));
            List<CarFromCsv> filtered = CarFilterService.filterCsv(csvCars, parsedArgs.getBrand(), parsedArgs.getReleaseDate());
            if (parsedArgs.isSortDate()) {
                filtered.sort((a, b) -> b.getReleaseDate().compareTo(a.getReleaseDate()));
            }
            CarOutputService.outputCsv(filtered, parsedArgs.getFormat());
        } else if (parsedArgs.hasXmlInput()) {
            List<CarFromXml> xmlCars = XmlReader.parse(Paths.get("src/main/resources/carsType.xml"));
            List<CarFromXml> filtered = CarFilterService.filterXml(xmlCars, parsedArgs.getType(), parsedArgs.getMaxPrice());
            if (parsedArgs.isSortPrice()) {
                filtered.sort((a, b) -> Double.compare(b.getPreferredPrice(), a.getPreferredPrice()));
            }
            CarOutputService.outputXml(filtered, parsedArgs.getFormat());
        } else {
            System.out.println("Invalid or missing parameters.");
        }
    }

}
