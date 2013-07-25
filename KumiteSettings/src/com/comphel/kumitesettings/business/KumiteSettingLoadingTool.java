package com.comphel.kumitesettings.business;

import android.os.Environment;

import com.comphel.common.definition.ConfigNotCompleteException;
import com.comphel.common.definition.ShobuIpponFinalRules;
import com.comphel.common.definition.ShobuIpponNormalRules;
import com.comphel.common.definition.ShobuIpponRules;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Admin on 20.07.13.
 */
public class KumiteSettingLoadingTool  {
    private static ShobuIpponFinalRules finalRules = new ShobuIpponFinalRules();
    private static ShobuIpponNormalRules normalRules = new ShobuIpponNormalRules();
    private static String configPath = Environment.getExternalStorageDirectory() + "/Comphel/ShobuIppon/";
    private static String config = "config.xml";

    private static void load() throws ParserConfigurationException, ConfigNotCompleteException, IOException, SAXException {
            File fXmlFile = new File(configPath+config);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();

            NodeList nList = root.getElementsByTagName("normal");
            readElement((Element)  nList.item(0), normalRules);


            NodeList nListFinale = root.getElementsByTagName("finals");
            readElement((Element)  nListFinale.item(0), finalRules);

    }

    private static void readElement(Element nNode, ShobuIpponRules rules) throws ConfigNotCompleteException{
        Element eElement = (Element) nNode;

            rules.setAtenaiToLose(Integer.parseInt(eElement.getElementsByTagName("atenai").item(0).toString()));
            rules.setJogaiToLose(Integer.parseInt(eElement.getElementsByTagName("jogai").item(0).toString()));
            rules.setMubobiToLose(Integer.parseInt(eElement.getElementsByTagName("mubobi").item(0).toString()));
            rules.setTimeleft(Integer.parseInt(eElement.getElementsByTagName("time").item(0).toString()));
            rules.setWazariToWin(Integer.parseInt(eElement.getElementsByTagName("wazari").item(0).toString()));

    }

    private static void readValue(Node node, ShobuIpponNormalRules rules) throws ConfigNotCompleteException{
        if(node == null){
            throw new ConfigNotCompleteException();
        }

    }

    public static ShobuIpponFinalRules getFinalRules() {
        try {
            load();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (ConfigNotCompleteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return finalRules;
    }

    public static ShobuIpponNormalRules getNormalRules() {
        try {
            load();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (ConfigNotCompleteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return normalRules;
    }
}
