package ingenias.editor.filters;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public class FilterManager {


	public static Vector<DiagramFilter> listAvailableConfigurations(){
		Vector<DiagramFilter>  list=new  Vector<DiagramFilter> ();
		File configs=new File("configs");
		if (!configs.exists())
			throw new RuntimeException("configs folder does not exist. Please, create one with that name");
		File[] containedConfigs=configs.listFiles();
		for (File config:containedConfigs){			
			if (config.isFile() && (config.getName().endsWith(".xml") || config.getName().endsWith(".XML"))){				 
				list.add(obtainDiagramFilter(config.getAbsolutePath()));				
			}
		
		}
		return list;
	}

	public static ingenias.editor.filters.DiagramFilter obtainDiagramFilter(String xmlConfiguration){		
		org.apache.xerces.parsers.DOMParser parser = new org.apache.xerces.parsers.DOMParser();		
		DiagramFilter df=new DiagramFilter();
		Hashtable<String,Vector<String>> tempAllowedEntities=new Hashtable<String,Vector<String>>();
		Hashtable<String,Vector<String>> tempAllowedRelationships=new Hashtable<String,Vector<String>>();
		try {
			parser.parse(new InputSource(new FileInputStream(xmlConfiguration)));
			Document doc = parser.getDocument();
			NodeList nl = doc.getElementsByTagName("name");
			String filterName=nl.item(0).getChildNodes().item(0).getNodeValue();
			df.setName(filterName);
			df.setCurrentAllowedEntities(tempAllowedEntities);
			df.setCurrentAllowedRelationships(tempAllowedRelationships);

			nl = doc.getElementsByTagName("diagram");
			for (int k=0;k<nl.getLength();k++){
				Node node=nl.item(k);
				String diagramName=node.getAttributes().getNamedItem("name").getNodeValue();
				NodeList entityNodes=node.getChildNodes();
				Vector<String> entities=new Vector<String>();
				Vector<String> relationships=new Vector<String>();
				for (int indexEnt=0;indexEnt<entityNodes.getLength();indexEnt++){
					if (entityNodes.item(indexEnt).getNodeName().equalsIgnoreCase("entity")){
						Node entity=entityNodes.item(indexEnt);
						entities.add(entity.getChildNodes().item(0).getNodeValue());		
					}
					if (entityNodes.item(indexEnt).getNodeName().equalsIgnoreCase("relationship")){
						Node relationship=entityNodes.item(indexEnt);
						relationships.add(relationship.getChildNodes().item(0).getNodeValue());		
					}
				}
				tempAllowedEntities.put(diagramName, entities);
				tempAllowedRelationships.put(diagramName,relationships);
			}			
							
			return df;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String args[]){
		System.err.println(listAvailableConfigurations());
	}


}
