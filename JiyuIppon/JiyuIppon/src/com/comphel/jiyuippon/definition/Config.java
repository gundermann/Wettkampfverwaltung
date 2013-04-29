package com.comphel.jiyuippon.definition;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Config {

	private long timeleft;
	
	private int wazariToWin;
	
	private int jogaiToLose;
	
	private int muobiToLose;
	
	private int atenaiToLose;
	
	private String configFile = "/sdcrad/Comphel/ShobuIppon/config.xml";
	
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
				File fXmlFile = new File(configFile);
				if(!fXmlFile.exists()){
					createConfig();
					fXmlFile = new File(configFile);
				}
				
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
			 
				doc.getDocumentElement().normalize();
			 
				if(!isFinal){
					NodeList nList = doc.getElementsByTagName("normal");
					readElement((Element)  nList.item(0));
				}
				else{
					NodeList nList = doc.getElementsByTagName("finals");
					readElement((Element)  nList.item(0));
				}
				
			    } catch (Exception e) {
				e.printStackTrace();
			    }
		 
	}

	private void readElement(Element nNode) {
		Element eElement = (Element) nNode;
		
		setAtenaiToLose(Integer.parseInt(eElement.getElementsByTagName("atenai").item(0).getTextContent()));
		setJogaiToLose(Integer.parseInt(eElement.getElementsByTagName("jogai").item(0).getTextContent()));
		setMuobiToLose(Integer.parseInt(eElement.getElementsByTagName("muobi").item(0).getTextContent()));
		setTimeleft(Long.parseLong(eElement.getElementsByTagName("time").item(0).getTextContent()));
		setWazariToWin(Integer.parseInt(eElement.getElementsByTagName("wazari").item(0).getTextContent()));
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
		 
				Element time = doc.createElement("wazari");
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
				
				
				Element finals = doc.createElement("finals");
				rootElement.appendChild(finals);
		 
				wazari.appendChild(doc.createTextNode("6"));
				finals.appendChild(wazari);
		 
				jogai.appendChild(doc.createTextNode("3"));
				finals.appendChild(jogai);
		 
				atenai.appendChild(doc.createTextNode("3"));
				finals.appendChild(atenai);
		 
				muobi.appendChild(doc.createTextNode("3"));
				finals.appendChild(muobi);
		 
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(configFile));
		 
				transformer.transform(source, result);
			  } catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			  } catch (TransformerException tfe) {
				tfe.printStackTrace();
			  }
	}
	
}
