package com.fr.clisson.berthelot.olivier.berthelottoolbox;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by olivier on 19/08/2017.
 */

public class XMLHelper {
    /**
     * Ecrit une nouvelle valeur pour un nom de dépense dans un fichier XML
     * @param path
     * @param name
     * @param valeur
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void WriteXML(String path, String name, double valeur) throws ParserConfigurationException, IOException, SAXException {
        File f = new File(BudgetMain.dataDirectory + "/" +path);
        if(!f.exists())
        {
            CreateXML(path);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(BudgetMain.dataDirectory + "/" +path);

    }

    /**
     * Crée le fichier XML s'il n'existe pas
     * @param path
     */
    public static void CreateXML(String path)
    {
        String aEcrire = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<Budget>\n" +
                "</Budget>";

        FileHelper.Write(path, aEcrire);

    }
}
