package ru.ezhov.xml;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ContentGenerator {
    public static void main(String[] args) {
        int countTemplate = 100000;
        File fileTemplate = new File("src/main/resources/template-" + countTemplate + ".xml");
        try (OutputStream outputStream = new FileOutputStream(fileTemplate)) {
            XMLStreamWriter xsw = XMLOutputFactory.newInstance().createXMLStreamWriter(outputStream);
            xsw.writeStartDocument("UTF-8", "1.0");
            xsw.writeStartElement("ezh", "content", "https://ezhov-da.github.io/content");
            xsw.setPrefix("ezh", "https://ezhov-da.github.io/content");
            xsw.writeNamespace("ezh", "https://ezhov-da.github.io/content");
            xsw.writeStartElement("ezh", "templates", "https://ezhov-da.github.io/content");
            for (int i = 0; i < countTemplate; i++) {
                xsw.writeStartElement("ezh", "template", "https://ezhov-da.github.io/content");
                xsw.writeAttribute("id", "10");
                xsw.writeStartElement("ezh", "rows", "https://ezhov-da.github.io/content");
                xsw.writeStartElement("ezh", "row", "https://ezhov-da.github.io/content");
                xsw.writeAttribute("id", "10");
                xsw.writeEndElement(); //row
                xsw.writeStartElement("ezh", "row", "https://ezhov-da.github.io/content");
                xsw.writeAttribute("id", "12");
                xsw.writeEndElement(); //row
                xsw.writeStartElement("ezh", "row", "https://ezhov-da.github.io/content");
                xsw.writeAttribute("id", "123");
                xsw.writeEndElement(); //row
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
