package ingenias.editor;

import ingenias.exception.InvalidProjectProperty;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Preferences {
	enum DefaultRelationshipView {INGENIAS,UML};
	enum EditPropertiesMode {POPUP,PANEL};
	enum ModelingLanguage {UML,INGENIAS};
	enum RelationshipLayout {AUTOMATIC,MANUAL};
	enum RelationshipsLookAndFeel {LABELS,NOTHING,FULL};
	

	private DefaultRelationshipView defaultRelationshipView=DefaultRelationshipView.INGENIAS;
	private EditPropertiesMode editPropertiesMode=EditPropertiesMode.PANEL;
	private RelationshipsLookAndFeel relationshipsLookAndFeel=RelationshipsLookAndFeel.NOTHING;
	private ModelingLanguage modelingLanguage=ModelingLanguage.INGENIAS;
	private RelationshipLayout relationshiplayout=RelationshipLayout.AUTOMATIC;
	

	public String toXML(){
		StringBuffer sb=new StringBuffer();
		sb.append("<defaultRelationshipView>");
		sb.append(defaultRelationshipView);
		sb.append("</defaultRelationshipView>\n");
		sb.append("<editPropertiesMode>".toLowerCase());
		sb.append(editPropertiesMode);
		sb.append("</editPropertiesMode>\n".toLowerCase());
		sb.append("<relationshipsLookAndFeel>");
		sb.append(relationshipsLookAndFeel);
		sb.append("</relationshipsLookAndFeel>\n");
		sb.append("<relationshiplayout>");
		sb.append(relationshiplayout);
		sb.append("</relationshiplayout>\n");
		sb.append("<modeling>");
		sb.append(modelingLanguage);
		sb.append("</modeling>\n");
		return sb.toString();
	}

	public static Preferences fromXML(Node n) {
		Preferences prefs=new Preferences();

		NodeList children = n.getChildNodes();
		for (int k=0;k<children.getLength();k++){
			if (children.item(k).getNodeName().equalsIgnoreCase("defaultRelationshipView")){
				prefs.defaultRelationshipView=DefaultRelationshipView.valueOf(children.item(k).getChildNodes().item(0).getNodeValue());
			}
			if (children.item(k).getNodeName().equalsIgnoreCase("editPropertiesMode")){
				prefs.editPropertiesMode=EditPropertiesMode.valueOf(children.item(k).getChildNodes().item(0).getNodeValue());
			}
			if (children.item(k).getNodeName().equalsIgnoreCase("relationshipsLookAndFeel")){
				prefs.relationshipsLookAndFeel=RelationshipsLookAndFeel.valueOf(children.item(k).getChildNodes().item(0).getNodeValue());
			}
			if (children.item(k).getNodeName().equalsIgnoreCase("relationshiplayout")){
				prefs.relationshiplayout=RelationshipLayout.valueOf(children.item(k).getChildNodes().item(0).getNodeValue());
			}
			if (children.item(k).getNodeName().equalsIgnoreCase("modeling")){
				prefs.modelingLanguage=ModelingLanguage.valueOf(children.item(k).getChildNodes().item(0).getNodeValue());
			}
		}
		return prefs;

	}

	public DefaultRelationshipView getDefaultRelationshipView() {
		return defaultRelationshipView;
	}

	public void setDefaultRelationshipView(
			DefaultRelationshipView defaultRelationshipView) {
		this.defaultRelationshipView = defaultRelationshipView;
	}

	public EditPropertiesMode getEditPropertiesMode() {
		return editPropertiesMode;
	}

	public void setEditPropertiesMode(EditPropertiesMode editPropertiesMode) {
		this.editPropertiesMode = editPropertiesMode;
	}

	public RelationshipLayout getRelationshiplayout() {
		return relationshiplayout;
	}

	public void setRelationshiplayout(RelationshipLayout relationshiplayout) {
		this.relationshiplayout = relationshiplayout;
	}

	public RelationshipsLookAndFeel getRelationshipsLookAndFeel() {
		return relationshipsLookAndFeel;
	}

	public void setRelationshipsLookAndFeel(
			RelationshipsLookAndFeel relationshipsLookAndFeel) {
		this.relationshipsLookAndFeel = relationshipsLookAndFeel;
	}

	public ModelingLanguage getModelingLanguage() {
		return modelingLanguage;
	}

	public void setModelingLanguage(ModelingLanguage modelingLanguage) {
		this.modelingLanguage = modelingLanguage;
	}

}
