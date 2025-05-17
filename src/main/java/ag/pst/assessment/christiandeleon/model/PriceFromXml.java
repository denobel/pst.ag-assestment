package ag.pst.assessment.christiandeleon.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class PriceFromXml {

    @XmlAttribute
    private String currency;

    @XmlValue
    private double value;

    public String getCurrency() { return currency; }
    public double getValue() { return value; }
}
