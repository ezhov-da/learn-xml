package ru.ezhov.xml;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ContentGenerator {
    public static void main(String[] args) {
        int countTemplate = 3;
        File fileTemplate = new File("src/main/resources/template-" + countTemplate + ".xml");
        try (OutputStream outputStream = new FileOutputStream(fileTemplate)) {
            XMLStreamWriter xsw = XMLOutputFactory.newInstance().createXMLStreamWriter(outputStream);
            String nsUri = "https://ezhov-da.github.io/content";
            xsw.writeStartDocument("UTF-8", "1.0");
            xsw.writeStartElement("ezh", "content", nsUri);
            xsw.writeAttribute("xsd", "template.xsd");
            xsw.setPrefix("ezh", nsUri);
            xsw.writeNamespace("ezh", nsUri);
            xsw.writeStartElement("ezh", "templates", nsUri);
            for (int i = 0; i < countTemplate; i++) {
                xsw.writeStartElement("ezh", "template", nsUri);
                xsw.writeAttribute("id", "10");
                xsw.writeStartElement("ezh", "rows", nsUri);
                xsw.writeEmptyElement("ezh", "row", nsUri);
                xsw.writeAttribute("id", "10");
                xsw.writeEmptyElement("ezh", "row", nsUri);
                xsw.writeAttribute("id", "12");
                xsw.writeEmptyElement("ezh", "row", nsUri);
                xsw.writeAttribute("id", "123");
                xsw.writeEndElement(); //rows
                xsw.writeEndElement(); //template
            }
            xsw.writeEndElement(); //templates
            xsw.writeEndElement(); //content
            xsw.writeEndDocument();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
