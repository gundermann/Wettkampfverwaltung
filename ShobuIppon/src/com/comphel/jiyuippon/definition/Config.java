package com.comphel.jiyuippon.definition;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.os.Environment;

public class Config {

	private long timeleft;
	
	private int wazariToWin;
	
	private int jogaiToLose;
	
	private int muobiToLose;
	
	private int atenaiToLose;
	
	private String configPath = Environment.getExternalStorageDirectory() + "/Comphel/ShobuIppon/";
	
	private String config = "config.xml";
	
	public long getTimeleft() {
		return timeleft;
	}

	public void setTimeleft(long timeleft) {
		this.timeleft = timeleft;
	}

	public int getWazariToWin() {
		return wazariToWin;
	}

	public void setWazariToWin(int wazariToWin) {
		this.wazariToWin = wazariToWin;
	}

	public int getJogaiToLose() {
		return jogaiToLose;
	}

	public void setJogaiToLose(int jogaiToLose) {
		this.jogaiToLose = jogaiToLose;
	}

	public int getMuobiToLose() {
		return muobiToLose;
	}

	public void setMuobiToLose(int muobiToLose) {
		this.muobiToLose = muobiToLose;
	}

	public int getAtenaiToLose() {
		return atenaiToLose;
	}

	public void setAtenaiToLose(int atenaiToLose) {
		this.atenaiToLose = atenaiToLose;
	}
	
	public void update(boolean isFinal){
		 try {
				File fXmlFile = new File(configPath+config);	
				if(!fXmlFile.exists()){
					createConfig();
					fXmlFile = new File(configPath+config);
				}
				
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
			 
				doc.getDocumentElement().normalize();
			 
				Element root = doc.getDocumentElement();
				
					NodeList nList = root.getElementsByTagName("normal");
					readElement((Element)  nList.item(0));

				if(!isFinal){
					NodeList nListFinale = root.getElementsByTagName("finals");
					readElement((Element)  nListFinale.item(0));
				}
				
			    } catch (Exception e) {
				e.printStackTrace();
			    }
		 
	}

	private void readElement(Element nNode) {
		Element eElement = (Element) nNode;
		
		try{
			readValue(eElement.getElementsByTagName("atenai").item(0));
			readValue(eElement.getElementsByTagName("jogai").item(0));
			readValue(eElement.getElementsByTagName("muobi").item(0));
			readValue(eElement.getElementsByTagName("time").item(0));
			readValue(eElement.getElementsByTagName("wazari").item(0));
		}catch(ConfigNotCompleteException cnce){
			cnce.printStackTrace();
		}
	}

	private void readValue(Node node) throws ConfigNotCompleteException{
		if(node == null){
			throw new ConfigNotCompleteException();
		}
		
		//finds the right setter and invokes the method with the right value
		for(Method setter : Config.class.getDeclaredMethods()){
			if(setter.getName().contains("set") && setter.getName().contains(node.getNodeName().substring(1))){
				try {
					setter.invoke(this, Integer.parseInt(node.getTextContent()));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (DOMException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void createConfig() {
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
				time.appendChild(doc.createTextNode("120000"));
				normal.appendChild(time);
				
				Element wazari = doc.createElement("wazari");
				wazari.appendChild(doc.createTextNode("2"));
				normal.appendChild(wazari);
		 
				Element jogai = doc.createElement("jogai");
				jogai.appendChild(doc.createTextNode("3"));
				normal.appendChild(jogai);
		 
				Element atenai = doc.createElement("atenai");
				atenai.appendChild(doc.createTextNode("3"));
				normal.appendChild(atenai);
		 
				Element muobi = doc.createElement("muobi");
				muobi.appendChild(doc.createTextNode("3"));
				normal.appendChild(muobi);
				
				//final
				Element finals = doc.createElement("finals");
				rootElement.appendChild(finals);
		 
				Element wazariForFinal = doc.createElement("wazari");
				wazariForFinal.appendChild(doc.createTextNode("6"));
				finals.appendChild(wazariForFinal);
				
				Element timeForFinal = doc.createElement("time");
				timeForFinal.appendChild(doc.createTextNode("120000"));
				normal.appendChild(timeForFinal);
				
				Element jogaiForFinal = doc.createElement("jogai");
				jogaiForFinal.appendChild(doc.createTextNode("3"));
				normal.appendChild(jogaiForFinal);
		 
				Element atenaiForFinal = doc.createElement("atenai");
				atenaiForFinal.appendChild(doc.createTextNode("3"));
				normal.appendChild(atenaiForFinal);
		 
				Element muobiForFinal = doc.createElement("muobi");
				muobiForFinal.appendChild(doc.createTextNode("3"));
				normal.appendChild(muobiForFinal);
		 
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
