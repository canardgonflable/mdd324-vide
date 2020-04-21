package com.ipiecoles.java.mdd324.homepage.controller;

import com.ipiecoles.java.mdd324.homepage.model.Film;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static com.ipiecoles.java.mdd324.homepage.utils.Utils.getPageContents;


@RestController
@RequestMapping(value = "/films")
public class CinemaController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Film> films() throws IOException, ParserConfigurationException, SAXException {
        var url = "http://rss.allocine.fr/ac/cine/prochainement?format=json";
        String xmlContent = getPageContents(url);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xmlContent)));

        NodeList items = doc.getElementsByTagName("item");
        List<Film> filmsList = new ArrayList<>();

        for (int temp = 0; temp < items.getLength(); temp++) {
            Node nNode = items.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Film newFilm = new Film();
                String categorie = eElement.getElementsByTagName("description").item(0).getTextContent().split(" -")[0];
                newFilm.setTitre(eElement.getElementsByTagName("title").item(0).getTextContent());
                newFilm.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                newFilm.setCategorie(categorie);
                    filmsList.add(newFilm);
            }
        }
        return filmsList;
    }
}
