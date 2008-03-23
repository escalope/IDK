package atai.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import atai.questions.ATQuestion;
import atai.questions.ATEntity;
import atai.questions.ATQuestionGraph;



public class QuestionPanel extends javax.swing.JPanel {
	private ATQuestion question=null;
	private JPanel selectedPane;
	private JPanel pbuiltPanel;
	private JPanel selfPanel;
	private ConfirmQuestionPanel confirmPanel;
	private JComboBox selector;

	public QuestionPanel(ATQuestion at) {
		this.question=at;
		javax.swing.BoxLayout layout=new javax.swing.BoxLayout(this,javax.swing.BoxLayout.Y_AXIS);
		confirmPanel=new ConfirmQuestionPanel();
		this.setLayout(layout);
		if (at.getPotentialAnswers().size()>0){
			selector=new JComboBox(new Object[]{"Assisted","Select yourself" });
		} else
			selector=new JComboBox(new Object[]{"Select yourself" });


		pbuiltPanel=new PrebuiltAnswerPanel(at);

		selfPanel=new JPanel(new BorderLayout());
		selfPanel.add(new SelfConfiguredAnswerPanel(at));



		selectedPane=pbuiltPanel;


		pbuiltPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		selfPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JPanel questionIntPanelMain=new JPanel(new BorderLayout());
		JPanel questionIntroductionPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		questionIntroductionPanel.add(new JLabel("<html><b>"+at.getId()+":</b></html>"));
		StringTokenizer st=new StringTokenizer(at.getDescription()," ",false);
		while (st.hasMoreTokens()){
			String token=st.nextToken();
			questionIntroductionPanel.add(new JLabel(token));
		}
		questionIntPanelMain.add(questionIntroductionPanel,BorderLayout.CENTER);
		this.add(questionIntPanelMain);
		this.add(confirmPanel);
		confirmPanel.addConfirmActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				add(selector);				
				add(selectedPane);
				selector.setSelectedIndex(0);
				repaint();				
			}
		});

		confirmPanel.addDiscardActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				remove(selector);				
				remove(selectedPane);
				repaint();	
			}});




		//this.add(new NewValuesPanel(at));		
		selector.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg) {
				if (selector.getSelectedItem().equals("Select yourself")){
					remove(selectedPane);
					selectedPane=selfPanel;
					add(selfPanel);
					revalidate();
					repaint();
				} else {
					remove(selectedPane);				
					selectedPane=pbuiltPanel;
					add(pbuiltPanel);
					revalidate();
					repaint();
				}
			}
		});

	}

	public boolean isFilledIn(){
		return confirmPanel.isConfirmed();
	}

	public void acceptSolution() {
		if (isFilledIn()){
			//List<Map<String,String>> answers=new Vector<Map<String,String>>(); 
			Map<String,String> answer = new HashMap<String,String>();
			Map<String,String> typesOfNewValues = new HashMap<String,String>();
			if (selector.getSelectedItem().equals("Select yourself")){
				// Self selection					
				SelfConfiguredAnswerPanel panel=(SelfConfiguredAnswerPanel)selectedPane.getComponent(0);
				// answers.add(panel.getAnswer());
				answer = panel.getAnswer();
				typesOfNewValues= panel.getAnswerTypes();
				this.question.setAnswerCreatedWithAssistance(false);
			} else {
				// Prebuilt solution
				PrebuiltAnswerPanel panel=(PrebuiltAnswerPanel)selectedPane;				
				// answers.add(panel.getAnswer());
				answer = panel.getAnswer();
				this.question.setAnswerCreatedWithAssistance(true);
			}	
			// this.question.setActualAnswers(answers);
			int index = this.question.getActualAnswers().size();
			if (answer.size()>0){
				// setActualAnswers(int index, String language, Map<String, String> oneAnswer, List<String> newElements)			
				this.question.setActualAnswers(index, ATQuestionGraph.GRAPH_LANGUAGE, answer, new Vector<String>());
				Vector<Map<String,String>> list=new Vector<Map<String,String>>();
				list.add(typesOfNewValues);
				this.question.setTypesOfNewElementsInActualAnswers(list);
			}
		}
	}
}
