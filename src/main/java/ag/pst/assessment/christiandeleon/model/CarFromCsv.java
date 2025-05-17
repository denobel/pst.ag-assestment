package ag.pst.assessment.christiandeleon.model;

import java.time.LocalDate;

public class CarFromCsv {
    private String brand;
    private LocalDate releaseDate;

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
}
