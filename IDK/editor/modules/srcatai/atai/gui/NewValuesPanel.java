package atai.gui;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import atai.questions.ATQuestion;

public class NewValuesPanel {
	Hashtable<String,JTextField> values=new Hashtable<String,JTextField>(); 
	Hashtable<String,String> fixedValue=new Hashtable<String,String>();
	private Vector<JComponent> components=new Vector<JComponent> ();
	
	public NewValuesPanel() { 
	}

	public void addNewValue(String section, JTextField userDefinedValue) {
		values.put(section,userDefinedValue);		
	}
	
	public void addFixedValue(String section, String value) {
		fixedValue.put(section,value);		
	}
	

	public Map<String, String> getAnswer() {
		// TODO Auto-generated method stub
		Hashtable<String,String> result=new Hashtable<String,String>();
		for (String key:fixedValue.keySet()){
			result.put(key, fixedValue.get(key));
		}
		for (String key:values.keySet()){
			result.put(key, values.get(key).getText());
		}
		
		return result;
	}
	
	public void addComponent(JComponent jl){
		this.components.add(jl);
	}
	
	public Vector<JComponent> getComponents(){
	 return this.components;
	}
}
