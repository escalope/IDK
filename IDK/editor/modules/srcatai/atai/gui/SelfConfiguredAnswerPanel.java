package atai.gui;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import atai.questions.ATEntity;
import atai.questions.ATView;
import atai.questions.Question;

public class SelfConfiguredAnswerPanel extends JPanel {

	Hashtable<String,JButton> selectionButtons=new Hashtable<String,JButton>();
	Hashtable<String,String> storedValues=new Hashtable<String,String>();
	Hashtable<String,String> typesOfNewValues=new Hashtable<String,String>();
	Map<String, ATEntity> theMap=null;

	public SelfConfiguredAnswerPanel(Question at) {
		final JPanel mainFrame=this;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		theMap = new HashMap<String, ATEntity>();
		for (String entity : at.getQuestion().getVariables())
			theMap.put(entity, at.getQuestion().getATEntityTypes().get(entity));
		final Map<String, ATEntity> variables = theMap;
		for (final String section:at.getTextualQuestion()){
			if (variables.containsKey(section)){
				JButton selectionButton=null;
				if (selectionButtons.containsKey(section))
					selectionButton=selectionButtons.get(section);
				else {
					final ValueSelectionDialog dialog=new ValueSelectionDialog();
					selectionButton=new JButton(section);
					selectionButtons.put(section,selectionButton);

					selectionButton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
							Hashtable<String, String> values = atai.questions.ATView.getTheInstance().getCompleteEntitiesDescriptionForATRole(variables.get(section));
							List<String> validTypes=ATView.getEntityTypesForATRole(variables.get(section));
							dialog.setValues(values,new Vector<String>(validTypes));
							String oldValue=null;
							if (storedValues.containsKey(section)){
								oldValue=storedValues.get(section);
							}
							dialog.setVisible(true);
							if (dialog.getSelected()!=null){
								Collection<String> alredyDefined = storedValues.values();
								if (alredyDefined.contains(dialog.getSelected().toString())){
									JOptionPane.showMessageDialog(mainFrame, "Please, select another value because that has been already selected");
								}  else {
									((JButton)arg0.getSource()).setText(dialog.getSelected());
									storedValues.put(section,dialog.getSelected());
									if (oldValue!=null){
										if (typesOfNewValues.containsKey(oldValue)){
											typesOfNewValues.remove(oldValue);
										}
									}
									if ( dialog.getSelectedType()!=null)
									
										typesOfNewValues.put(dialog.getSelected(), dialog.getSelectedType());
									
								}
							}
						}
					});

				}				 
				this.add(selectionButton);
			} else {

				StringTokenizer st=new StringTokenizer(section," ",false);
				while (st.hasMoreTokens()){
					String token=st.nextToken();
					this.add(new JLabel(token));
				}
			}

		}
	}

	private Vector<String> getValues(String variable, List<Map<String, String>> potentialAnswers) {
		Vector<String> result=new Vector<String>();
		for (Map<String, String> varValues:potentialAnswers){
			result.add(varValues.get(variable));
		}
		return result;
	}

	public Map<String, String> getAnswer() {
		if (theMap.size()==storedValues.size())
			return storedValues;
		else 
			return new Hashtable<String, String>();
	}

	public Map<String, String> getAnswerTypes() {
		return typesOfNewValues;
	}

}
