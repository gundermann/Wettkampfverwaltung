package com.comphel.kumitesettings.business;

import android.os.Environment;

import com.comphel.common.definition.ShobuIpponFinalRules;
import com.comphel.common.definition.ShobuIpponNormalRules;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Admin on 16.07.13.
 */
public class KumiteSettingSavingTool {

    private static String configPath = Environment.getExternalStorageDirectory() + "/Comphel/ShobuIppon/";
    private static String config = "config.xml";


    public static void save(ShobuIpponNormalRules normalRules, ShobuIpponFinalRules finalRules) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("JiyuIppon");
            doc.appendChild(rootElement);

            //normal round
            Element normal = doc.createElement("normal");
            rootElement.appendChild(normal);

            Element time = doc.createElement("time");
            time.appendChild(doc.createTextNode(String.valueOf(normalRules.getTimeleft())));
            normal.appendChild(time);

            Element wazari = doc.createElement("wazari");
            wazari.appendChild(doc.createTextNode(String.valueOf(normalRules.getWazariToWin())));
            normal.appendChild(wazari);

            Element jogai = doc.createElement("jogai");
            jogai.appendChild(doc.createTextNode(String.valueOf(normalRules.getJogaiToLose())));
            normal.appendChild(jogai);

            Element atenai = doc.createElement("atenai");
            atenai.appendChild(doc.createTextNode(String.valueOf(normalRules.getAtenaiToLose())));
            normal.appendChild(atenai);

            Element muobi = doc.createElement("mubobi");
            muobi.appendChild(doc.createTextNode(String.valueOf(normalRules.getMubobiToLose())));
            normal.appendChild(muobi);

            //final
            Element finals = doc.createElement("finals");
            rootElement.appendChild(finals);

            Element wazariForFinal = doc.createElement("wazari");
            wazariForFinal.appendChild(doc.createTextNode(String.valueOf(finalRules.getWazariToWin())));
            finals.appendChild(wazariForFinal);

            Element timeForFinal = doc.createElement("time");
            timeForFinal.appendChild(doc.createTextNode(String.valueOf(finalRules.getTimeleft())));
            finals.appendChild(timeForFinal);

            Element jogaiForFinal = doc.createElement("jogai");
            jogaiForFinal.appendChild(doc.createTextNode(String.valueOf(finalRules.getJogaiToLose())));
            finals.appendChild(jogaiForFinal);

            Element atenaiForFinal = doc.createElement("atenai");
            atenaiForFinal.appendChild(doc.createTextNode(String.valueOf(finalRules.getAtenaiToLose())));
            finals.appendChild(atenaiForFinal);

            Element mubobiForFinal = doc.createElement("mubobi");
            mubobiForFinal.appendChild(doc.createTextNode(String.valueOf(finalRules.getMubobiToLose())));
            finals.appendChild(mubobiForFinal);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File file = new File(configPath);
            File configFile = new File(configPath+config);
            try {
                file.mkdirs();
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            StreamResult result = new StreamResult(configFile);

            transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
