package adapters;

import domain.MessageDTO;
import domain.MessageException;
import domain.MessageFormatter;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

/**
 * A formatter that uses JDOM to transfer a {@link MessageDTO} into an XML string
 */
public class XMLMessageFormatter implements MessageFormatter {
    private Logger logger = Logger.getLogger(XMLMessageFormatter.class);

    @Override
    public String format(MessageDTO messageDTO) throws MessageException {
        Element rootElement = new Element("Message");
        Document doc = new Document(rootElement);

        Element cameraIdElement = new Element("camera-id");
        cameraIdElement.setText(String.valueOf(messageDTO.getCameraId()));
        rootElement.addContent(cameraIdElement);

        Element timeElement = new Element("timestamp");
        timeElement.setText(String.valueOf(messageDTO.getTimestamp()));
        rootElement.addContent(timeElement);

        Element licensePlateElement = new Element("license-plate");
        licensePlateElement.setText(messageDTO.getLicensePlate().getPlate());
        rootElement.addContent(licensePlateElement);

        XMLOutputter xmlOutputter = new XMLOutputter();

        String out = xmlOutputter.outputString(doc);
        logger.info("Changed MessageDTO to XML: '" + out + "'");

        return out;
    }
}
