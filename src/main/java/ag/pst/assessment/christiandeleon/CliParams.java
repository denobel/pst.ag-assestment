package ag.pst.assessment.christiandeleon;

import java.time.LocalDate;

public class CliParams {
    private String brand;
    private LocalDate releaseDate;
    private String type;
    private double maxPrice;
    private boolean sortDate;
    private boolean sortPrice;
    private String format;

    public static CliParams parse(String[] args) {
        CliParams parsed = new CliParams();
        for (String arg : args) {
            if (arg.startsWith("--brand=")) {
                parsed.brand = arg.split("=")[1];
            } else if (arg.startsWith("--releasedate=")) {
                String[] parts = arg.split("=")[1].split(",");
                parsed.releaseDate = LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[2]), Integer.parseInt(parts[1]));
            } else if (arg.startsWith("--type=")) {
                parsed.type = arg.split("=")[1];
            } else if (arg.startsWith("--price=")) {
                parsed.maxPrice = Double.parseDouble(arg.split("=")[1]);
            } else if (arg.equals("--sortDate=true")) {
                parsed.sortDate = true;
            } else if (arg.equals("--sortPrice=true")) {
                parsed.sortPrice = true;
            } else if (arg.startsWith("--format=")) {
                parsed.format = arg.split("=")[1];
            }
        }
        return parsed;
    }

    public boolean hasCsvInput() {
        return brand != null && releaseDate != null;
    }

    public boolean hasXmlInput() {
        return type != null && maxPrice > 0;
    }

    public String getBrand() {
        return brand;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getType() {
        return type;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public boolean isSortDate() {
        return sortDate;
    }

    public boolean isSortPrice() {
        return sortPrice;
    }

    public String getFormat() {
        return format == null ? "table" : format;
    }
}

