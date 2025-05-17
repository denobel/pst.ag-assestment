package ag.pst.assessment.christiandeleon.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class CarFromXml {

    private String type;
    private String model;
    @XmlElement(name = "price")
    private PriceFromXml usdPrice;
    @XmlElementWrapper(name = "prices")
    @XmlElement(name = "price")
    private List<PriceFromXml> prices;

    public String getType() { return type; }
    public String getModel() { return model; }
    public PriceFromXml getUsdPrice() { return usdPrice; }
    public List<PriceFromXml> getPrices() { return prices; }

    public double getPrice(String currency) {
        return prices.stream()
                .filter(p -> p.getCurrency().equals(currency))
                .findFirst()
                .map(PriceFromXml::getValue)
                .orElse(0.0);
    }
}
