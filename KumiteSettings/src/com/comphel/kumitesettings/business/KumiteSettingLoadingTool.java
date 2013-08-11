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
    private static ShobuIpponFinalRules finalRules;
    private static ShobuIpponNormalRules normalRules;
    private static String configPath = Environment.getExternalStorageDirectory() + "/Comphel/ShobuIppon/";
    private static String config = "config.xml";

    private static void load() throws ParserConfigurationException, ConfigNotCompleteException, IOException, SAXException {
        finalRules = new ShobuIpponFinalRules();
        normalRules = new ShobuIpponNormalRules();

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

        Node atenaiNode = eElement.getElementsByTagName("atenai").item(0);
        Node jogaiNode = eElement.getElementsByTagName("jogai").item(0);
        Node mubobiNode = eElement.getElementsByTagName("mubobi").item(0);
        Node timeNode = eElement.getElementsByTagName("time").item(0);
        Node wazariNode = eElement.getElementsByTagName("wazari").item(0);
        if(atenaiNode == null || jogaiNode == null || mubobiNode == null || timeNode == null || wazariNode == null){
            throw new ConfigNotCompleteException();
        }

        rules.setAtenaiToLose(Integer.parseInt(atenaiNode.getTextContent()));
        rules.setJogaiToLose(Integer.parseInt(jogaiNode.getTextContent()));
        rules.setMubobiToLose(Integer.parseInt(mubobiNode.getTextContent()));
        rules.setTimeleft(Integer.parseInt(timeNode.getTextContent()));
        rules.setWazariToWin(Integer.parseInt(wazariNode.getTextContent()));

    }

    private static void readValue(Node node, ShobuIpponNormalRules rules) throws ConfigNotCompleteException{
        if(node == null){
            throw new ConfigNotCompleteException();
        }

    }

    public static ShobuIpponFinalRules getFinalRules() {
        if(finalRules == null){
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
        }
        return finalRules;
    }

    public static ShobuIpponNormalRules getNormalRules() {
        if(normalRules == null){
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
        }
        return normalRules;
    }
}
