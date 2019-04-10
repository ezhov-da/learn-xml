package ru.ezhov.xml;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.InputStream;

public class ContentParser {
    public static void main(String[] args) {
        validate("/template.xsd", "/template-3.xml");
        validate("/template.xsd", "/template-10000.xml");
        validate("/template.xsd", "/template-100000.xml");
    }

    private static void validate(String pathResourceXsd, String pathResourceXml) {
        try (InputStream xsd = ContentParser.class.getResourceAsStream(pathResourceXsd)) {
            try (InputStream xml = ContentParser.class.getResourceAsStream(pathResourceXml)) {
                long startValidation = System.currentTimeMillis();
                validateXMLSchema(xsd, xml);
                long endValidation = System.currentTimeMillis();
                System.out.println(
                        "Валидация '" + pathResourceXsd +
                                "':'" + pathResourceXml +
                                "' за время: " + (endValidation - startValidation) + " ms");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //https://www.journaldev.com/895/how-to-validate-xml-against-xsd-in-java
    private static void validateXMLSchema(InputStream xsd, InputStream xml) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new StreamSource(xsd));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(xml));
    }
}
