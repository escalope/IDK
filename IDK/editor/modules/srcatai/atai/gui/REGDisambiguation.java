package atai.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import atai.questions.ATQuestionGraph;

public class REGDisambiguation extends JFrame {

	Hashtable<ATQuestionGraph,Hashtable<String, Vector<Vector<String>>>> 
	questions=new Hashtable<ATQuestionGraph,Hashtable<String, Vector<Vector<String>>>>();
	REGWizardInterface process=null;
	Hashtable<javax.swing.JRadioButton,Vector<String>> 
	radioPerInterpretation=new Hashtable<javax.swing.JRadioButton,Vector<String>>();
	Hashtable<javax.swing.JRadioButton,Vector<Vector<String>>> 
	radioPerQuestion=new Hashtable<javax.swing.JRadioButton,Vector<Vector<String>>>();
	boolean cancelled=false;
	Dimension maxDimension=new Dimension(600,20000);

	public REGDisambiguation(String arg0, REGWizardInterface process) throws HeadlessException {
		super(arg0);
		this.process=process;
		

	}


	public Hashtable<ATQuestionGraph, Hashtable<String, Vector<String>>> getSelectedInterpretationPerQuestion() {
		Hashtable<ATQuestionGraph, Hashtable<String, Vector<String>>> result=
			new Hashtable<ATQuestionGraph, Hashtable<String, Vector<String>>>();
		Set<ATQuestionGraph> keys = questions.keySet();
		for (ATQuestionGraph key:keys){
			Set<String> rels = questions.get(key).keySet();
			Hashtable<String, Vector<String>> relInterp= new Hashtable<String, Vector<String>>();
			result.put(key,relInterp);
			for (String rel:rels){
				Vector<Vector<String>> interpretations = questions.get(key).get(rel);				
				if (!interpretations.isEmpty()){					
					if (interpretations.size()>1)
						JOptionPane.showMessageDialog(this,"Cuidadin "+interpretations);
					else 
						relInterp.put(rel,interpretations.firstElement());
				}
			}
		}
		return result;
	}

	public void add(ATQuestionGraph question, 
			Hashtable<String, Vector<Vector<String>>> relationshipAssignments) {
		questions.put(question,relationshipAssignments);
	}

	private void initGraph(){
		this.getContentPane().setLayout(new BorderLayout());
		JButton nextButton=new JButton("Finish");
		JButton cancelButton=new JButton("Cancel");
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Set<JRadioButton> keys = radioPerQuestion.keySet();
				for (JRadioButton radio:keys){
					if (radio.isSelected()){
						Vector<Vector<String>> toRetain=new Vector<Vector<String>>(); 
						toRetain.add(radioPerInterpretation.get(radio));
						radioPerQuestion.get(radio).retainAll(toRetain);						
					}
				}
				process.finishedStep2();			
			}
		});
		cancelButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				cancelled=true;
				process.finishedStep2();	
			}

		});
		REGDisamContent box=new REGDisamContent();

		
//		box.setMaximumSize(maxDimension);
		box.setAlignmentX(Box.LEFT_ALIGNMENT);
		
	
		JLabel jl=new JLabel("<html>For each AT relationship, you must indicate which INGENIAS relationship is more adequate.</b></HTML>");
		jl.setAlignmentX(Box.LEFT_ALIGNMENT);
		box.add(jl);
		//descPanel.setMaximumSize(maxDimension);
		
		Set<ATQuestionGraph> questionsToDisambiguate = questions.keySet();

		for (ATQuestionGraph question:questionsToDisambiguate){
			Box panel=createQuestionPanel(question,questions.get(question));
	//		panel.setMaximumSize(maxDimension);
			box.add(panel);
		}

		JScrollPane sp=new JScrollPane(box, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.getContentPane().add(sp,BorderLayout.CENTER);
		JPanel southPanel=new JPanel();
		southPanel.add(nextButton);
		southPanel.add(cancelButton);
		this.getContentPane().add(southPanel,BorderLayout.SOUTH);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				cancelled=true;
				setVisible(false);
				process.finishedStep2();
			}
			public void windowClosing(WindowEvent e) {
				cancelled=true;
				setVisible(false);
				process.finishedStep2();
			}
		});
		

	}
	


	private Box createQuestionPanel(ATQuestionGraph atquestion, Hashtable<String, Vector<Vector<String>>> question) {
		Box questionP=Box.createVerticalBox();
		questionP.setAlignmentX(Box.LEFT_ALIGNMENT);
		TitledBorder border = BorderFactory.createTitledBorder("Question:"+atquestion.getId());		
		border.setTitleColor(java.awt.Color.BLACK);
		JPanel descPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		StringTokenizer st=new StringTokenizer("<HTML>Description:"+atquestion.getDescription()+".<p>This question is represented with the following relationships</HTML>"," ",false);
		while (st.hasMoreTokens()){
			String token=st.nextToken();
			descPanel.add(new JLabel(token));
		}
		descPanel.setAlignmentX(Box.LEFT_ALIGNMENT);
		JLabel jlabel=new JLabel("<HTML>Description:"+atquestion.getDescription()+". This question is represented with the following relationships<p></HTML>");
		jlabel.setAlignmentX(Box.LEFT_ALIGNMENT);
		JPanel separator=new JPanel();
		separator.setAlignmentX(Box.LEFT_ALIGNMENT);
		questionP.add(jlabel);
		questionP.add(separator);
		questionP.setBorder(border);		
		Set<String> atrels = question.keySet();
		int counter=0;
		for (String atrel:atrels){
			Box relPanel=Box.createVerticalBox();
			relPanel.setAlignmentX(Box.LEFT_ALIGNMENT);
			questionP.add(relPanel);
			Vector<Vector<String>> interpretations = question.get(atrel);

			if (interpretations.size()>1){
				String actors="";
				for (int k=1;k<interpretations.firstElement().size()-1;k++){
					actors=actors+interpretations.firstElement().elementAt(k)+", ";
				}
				if (interpretations.firstElement().size()>1)
					actors=actors+" and "+interpretations.firstElement().lastElement();
				
				JLabel jl=new JLabel("<html><html><div style=\"margin-left: 20px;\">("+counter+")Interpretation of <i>"+atrel+"</i> with elements "+actors+". It can be expressed with the following INGENIAS relationships:</div></html>");				
				jl.setAlignmentX(Box.LEFT_ALIGNMENT);				
				relPanel.add(jl);				
				javax.swing.ButtonGroup bg=new javax.swing.ButtonGroup();
				Box optionP=Box.createVerticalBox();
				optionP.setAlignmentX(Box.LEFT_ALIGNMENT);
				relPanel.add(optionP);			

				for (Vector<String> interpretation:interpretations){
					String explanation=interpretation.firstElement()+" (";
					for (int k=1;k<interpretation.size()-1;k++){
						explanation=explanation+interpretation.elementAt(k)+", ";
					}
					if (interpretation.size()>1)
					explanation=explanation+interpretation.lastElement()+")";
					else
						explanation=explanation+")";
					
					javax.swing.JRadioButton jr=new javax.swing.JRadioButton(explanation);
					jr.setAlignmentX(Box.LEFT_ALIGNMENT);
					bg.add(jr);					
					optionP.add(jr);					
					jr.setSelected(true);
					radioPerInterpretation.put(jr,interpretation);
					radioPerQuestion.put(jr, interpretations);
				}
			} else{
				if (interpretations.size()==0){
					JLabel jl=new JLabel("<html><div style=\"margin-left: 20px;\">("+counter+")Interpretation of <i>"+atrel+"</i> could not be translated at all. FAILED</div></html>");
					jl.setAlignmentX(Box.LEFT_ALIGNMENT);
					relPanel.add(jl);
				} else {
				String actors="";
				for (int k=1;k<interpretations.firstElement().size()-1;k++){
					actors=actors+interpretations.firstElement().elementAt(k)+", ";
				}
				if (interpretations.firstElement().size()>1)
					actors=actors+" and "+interpretations.firstElement().lastElement();
				
				JLabel jl=new JLabel("<html><div style=\"margin-left: 20px;\">("+counter+")Interpretation of <i>"+atrel+"</i> with elements "+actors+". SOLVED</div></html>");
				jl.setAlignmentX(Box.LEFT_ALIGNMENT);
				relPanel.add(jl);
				}
			}
			JPanel line=new JPanel();
			//line.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
			relPanel.add(line);
			line.setAlignmentX(Box.LEFT_ALIGNMENT);
			counter++;
		}
		return questionP;
	}


	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			initGraph();
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}


	public boolean isCancelled() {
		return cancelled;
	}


}
