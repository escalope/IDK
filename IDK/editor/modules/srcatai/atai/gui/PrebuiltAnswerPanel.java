package atai.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import atai.questions.ATEntity;
import atai.questions.Question;

public class PrebuiltAnswerPanel extends JPanel {

	private Question question;
	private NewValuesPanel selectedAnswer;
	private Hashtable<String,NewValuesPanel> answerWidgets;

	public PrebuiltAnswerPanel(Question at) {
		this.question=at;
		List<Map<String,String>> potAnswers=at.getPotentialAnswers();
		Vector<String> values=new Vector<String>(); 		
		answerWidgets=new Hashtable<String,NewValuesPanel>(); 
		createAnswerWidgetsAndValuesRepresentation(at, potAnswers, values);

		final JComboBox answerSetCombo=new JComboBox(values);
		if (values.size() > 0) {
			answerSetCombo.setSelectedIndex(0);		 
			selectedAnswer=answerWidgets.get(answerSetCombo.getSelectedItem().toString());
		} else {
			selectedAnswer = new NewValuesPanel();
		}
		this.setLayout(new BorderLayout());

		JPanel box = new JPanel (new FlowLayout());

		JPanel answerSelectionPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		answerSelectionPanel.add(new JLabel("Options:"));
		answerSelectionPanel.add(answerSetCombo);

		final JPanel solutionPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		solutionPanel.add(new JLabel("Solution:"));
		for (JComponent jcomp:selectedAnswer.getComponents()){
			solutionPanel.add(jcomp);
			}
		//solutionPanel.add(selectedAnswer);		 
		this.add(answerSelectionPanel, BorderLayout.NORTH);

		this.add(solutionPanel,BorderLayout.CENTER);

		//this.add(box);
		answerSetCombo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				changeSelectedAnswer(answerSetCombo, solutionPanel);

			}			 
		});
	}

	private void createAnswerWidgetsAndValuesRepresentation(Question at, List<Map<String, String>> potAnswers, Vector<String> values) {
		for (Map<String,String> potential:potAnswers){
			String representation="";
			//for (String value:potential.values())
			//	representation=representation+value+"//";
			for (String key : potential.keySet())
				if (at.getQuestion().getVariables().contains(key))
					representation = representation + potential.get(key) + " # ";
			representation = representation.substring(0, representation.length() - 2);
				
			representation=representation.substring(0,representation.length()-1);
			values.add(representation);
//			NewValuesPanel answerWidget=representAnswer(potential, at.getTextualQuestion(),at.getQuestionEntities());
			Map<String, ATEntity> theMap = new HashMap<String, ATEntity>();
			for (String entity : at.getQuestion().getVariables())
				theMap.put(entity, at.getQuestion().getATEntityTypes().get(entity));
			NewValuesPanel answerWidget=representAnswer(potential, at.getTextualQuestion(),theMap);
			answerWidgets.put(representation,answerWidget);
		}
	}

	private NewValuesPanel representAnswer(Map<String, String> potential, List<String> textualQuestion, Map<String, ATEntity> variables) {
		NewValuesPanel answerP=new NewValuesPanel();		 		
		for (String section:textualQuestion){
			if (variables.containsKey(section)){
				if (!potential.containsKey(section)){
					JTextField userDefinedValue=new JTextField(10);
					answerP.addNewValue(section,userDefinedValue);
					answerP.addComponent(userDefinedValue); 
				} else{
					answerP.addComponent(new JLabel("<html><b><i>"+potential.get(section)+"</i></b></html>"));
					answerP.addFixedValue(section, potential.get(section));
				}
			} else 
				answerP.addComponent(new JLabel("<html>"+section+"</html>"));
		}
		return answerP;
	}

	private void changeSelectedAnswer(final JComboBox answerSetCombo, final JPanel solutionPanel) {
		String key=answerSetCombo.getSelectedItem().toString();
		for (JComponent jcomp:selectedAnswer.getComponents()){
			solutionPanel.remove(jcomp);
			}
		//solutionPanel.remove(selectedAnswer);
		selectedAnswer=answerWidgets.get(key);
		for (JComponent jcomp:selectedAnswer.getComponents()){
		solutionPanel.add(jcomp);
		}
		solutionPanel.revalidate();
	}

	public Map<String, String> getAnswer() {
		// TODO Auto-generated method stub
		return selectedAnswer.getAnswer();
	}

}
