package ag.pst.assessment.christiandeleon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ag.pst.assessment.christiandeleon.model.CarFromCsv;
import ag.pst.assessment.christiandeleon.model.CarFromXml;

public class CarFilterService {

    public static List<CarFromCsv> filterCsv(List<CarFromCsv> cars, String brand, LocalDate releaseDate) {
        return cars.stream()
                .filter(car -> brand == null || car.getBrand().equalsIgnoreCase(brand))
                .filter(car -> releaseDate == null || car.getReleaseDate().equals(releaseDate))
                .collect(Collectors.toList());
    }

    public static List<CarFromXml> filterXml(List<CarFromXml> cars, String type, double maxPrice) {
        return cars.stream()
                .filter(car -> type == null || car.getType().equalsIgnoreCase(type))
                .filter(car -> maxPrice <= 0 || car.getPreferredPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}
