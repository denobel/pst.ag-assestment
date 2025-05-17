package ag.pst.assessment.christiandeleon.reader;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import ag.pst.assessment.christiandeleon.model.CarFromXml;

public class XmlReader {
    public static List<CarFromXml> parse(Path path) throws Exception {
        JAXBContext context = JAXBContext.newInstance(XmlCarList.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        XmlCarList list = (XmlCarList) unmarshaller.unmarshal(new File(path.toString()));
        return list.getCars();
    }
}

@XmlRootElement(name = "cars")
class XmlCarList {
    @XmlElement(name = "car")
    private List<CarFromXml> cars;
    public List<CarFromXml> getCars() { return cars; }
}
