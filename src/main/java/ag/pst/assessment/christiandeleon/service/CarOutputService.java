package ag.pst.assessment.christiandeleon.service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ag.pst.assessment.christiandeleon.model.CarFromCsv;
import ag.pst.assessment.christiandeleon.model.CarFromXml;

public class CarOutputService {

    public static void outputCsv(List<CarFromCsv> cars, String format) throws Exception {
    	ObjectMapper om = new ObjectMapper();
    	om.registerModule(new JavaTimeModule());
    	XmlMapper xm = new XmlMapper();
    	xm.registerModule(new JavaTimeModule());
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        switch (format) {
            case "json" -> System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(cars));
            case "xml" -> System.out.println(xm.writerWithDefaultPrettyPrinter().writeValueAsString(cars));
            default -> {
                System.out.println("Brand\tRelease Date");
                for (CarFromCsv car : cars) {
                    System.out.printf("%s\t%s", car.getBrand(), car.getReleaseDate().format(formatter));
                }
            }
        }
    }

    public static void outputXml(List<CarFromXml> cars, String format) throws Exception {
        switch (format) {
            case "json" -> System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(cars));
            case "xml" -> System.out.println(new XmlMapper().writerWithDefaultPrettyPrinter().writeValueAsString(cars));
            default -> {
                System.out.println("Type\tModel\tUSD\t\tEUR\t\tGBP\t\tJPY");
                for (CarFromXml car : cars) {
                    System.out.printf("%s\t%s\t%.2f\t%.2f\t%.2f\t%.2f\n",
                            car.getType(), car.getModel(),
                            car.getUsdPrice().getValue(), car.getPrice("EUR"), car.getPrice("GBP"), car.getPrice("JPY"));
                }
            }
        }
    }
}
