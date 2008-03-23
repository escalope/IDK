package atai.gui;

import ingenias.editor.Log;
import ingenias.exception.CannotLoad;
import ingenias.exception.DamagedFormat;
import ingenias.exception.InvalidGraph;
import ingenias.exception.InvalidPath;
import ingenias.exception.NotInitialised;
import ingenias.exception.UnknowFormat;
import ingenias.generator.browser.AttributedElement;
import ingenias.generator.browser.BrowserImp;
import ingenias.generator.browser.GraphAttribute;
import ingenias.generator.browser.GraphAttributeFactory;
import ingenias.generator.browser.GraphCollection;
import ingenias.generator.browser.GraphEntity;
import ingenias.generator.browser.GraphEntityFactory;
import ingenias.generator.browser.GraphFactory;
import ingenias.generator.browser.GraphRelationshipFactory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import atai.questions.ATGraph;
import atai.questions.ATQuestion;
import atai.questions.ATQuestionGraph;
import atai.questions.ATEntity;
import atai.questions.ATRelation;
import atai.questions.ATView;
import atai.questions.Graph;
import atai.questions.Question;
import atai.questions.QuestionProcessing;
import atai.questions.regGraph.*;

public class REGFrame extends JFrame {
	private Box questionBoxes=null;
	private DefaultMutableTreeNode root=null;
	private Vector<ATQuestionGraph> questions=new Vector<ATQuestionGraph>();
	private Hashtable<String, DefaultMutableTreeNode> areasNodes=new Hashtable<String, DefaultMutableTreeNode>();
	private Hashtable<String, DefaultMutableTreeNode> aspectsNodes=new Hashtable<String, DefaultMutableTreeNode>();
	private Hashtable<DefaultMutableTreeNode, ATQuestion> questionNodes=new Hashtable<DefaultMutableTreeNode, ATQuestion>();
	private Hashtable<DefaultMutableTreeNode, QuestionPanel> questionPanels=new Hashtable<DefaultMutableTreeNode, QuestionPanel>();
	private JSplitPane mainPanel=null;
	private JTree areasAndAspectsTree=null;
	private DefaultTreeModel treeModel=null;
	private int counter=0;
	private REGWizardInterface wizard;
	private boolean cancelled=false;

	public REGFrame() throws HeadlessException {
		initGUI();
		setTitle("REG Wizard");
		wizard=new REGWizardInterface(){
			public void finishedStep1() {
			}

			public void finishedStep2() {
			}
		};
	}


	private void initGUI(){
		root = new DefaultMutableTreeNode("REG");

		treeModel=new DefaultTreeModel(root);
		areasAndAspectsTree=new JTree(treeModel);
		
		JScrollPane areasAndAspectsTreeSP=new JScrollPane(areasAndAspectsTree);
		areasAndAspectsTreeSP.setMinimumSize(new Dimension(300,400));
		mainPanel=new JSplitPane();		
		questionBoxes=Box.createVerticalBox();
		mainPanel.setRightComponent(new JPanel());
		mainPanel.setLeftComponent(areasAndAspectsTreeSP);
		mainPanel.setDividerLocation(300);
		mainPanel.setDividerSize(10);
	
		this.setSize(900, 500);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		JButton apply=new JButton("Next");
		JButton cancel=new JButton("Cancel");
		JPanel south=new JPanel();
		south.add(apply);
		south.add(cancel);
		this.getContentPane().add(south, BorderLayout.SOUTH);
		final JFrame mainFrame=this;

		areasAndAspectsTree.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				if (areasAndAspectsTree.getSelectionPath()!=null){
					DefaultMutableTreeNode dtn=(DefaultMutableTreeNode)areasAndAspectsTree.getSelectionPath().getLastPathComponent();
					QuestionPanel qp=null;
					if (questionNodes.containsKey(dtn)){
						if (!questionPanels.containsKey(dtn)){
							qp=new QuestionPanel(questionNodes.get(dtn));
							qp.setMinimumSize(new Dimension(400,300));
							questionPanels.put(dtn,qp);			 
							qp.addContainerListener(new ContainerListener(){

								public void componentAdded(ContainerEvent e) {
									pack();
								}

								public void componentRemoved(ContainerEvent e) {
									pack();
								}
								
							});
						} else {
							qp=questionPanels.get(dtn);					 
						}
						
						
						mainPanel.setRightComponent(qp);
						pack();
						mainPanel.setDividerLocation(300);
						
						repaint();	
					}
				}
			}
			public void mouseEntered(MouseEvent arg0) {};
			public void mouseExited(MouseEvent arg0) {};
			public void mousePressed(MouseEvent arg0) {};
			public void mouseReleased(MouseEvent arg0) {};
		});

		apply.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				Collection<QuestionPanel> panels = questionPanels.values();				
				for (QuestionPanel qp:panels){
					qp.acceptSolution();
				}
				boolean allEmpty=true;
				Hashtable<String,String> valuesDefinedSoFar=new Hashtable<String,String>();
				String wrongQuestions="";
				for (ATQuestionGraph qp:questions){					
					allEmpty=allEmpty && qp.getActualAnswers().isEmpty();
					List<Map<String, String>> newElementsType = qp.getTypesOfNewElementsInActualAnswers();					
					for (Map<String, String> newAnswer:newElementsType){
						Set<String> keys = newAnswer.keySet();
						for (String varName:keys){
							if (valuesDefinedSoFar.containsKey(varName)){
								if (!valuesDefinedSoFar.get(varName).equals(newAnswer.get(varName))){
									wrongQuestions=wrongQuestions+"<br> Question "+qp.getArea()+"."+
									qp.getAspect()+"."+qp.getId()+" defines a new value "+varName+" of type "+
									newAnswer.get(varName)+ " but it was previously assigned a type "+
									valuesDefinedSoFar.get(varName);
								}
							} else {
								valuesDefinedSoFar.put(varName,newAnswer.get(varName));
							}
						}
					}
				}
				if (allEmpty){
					JOptionPane.showMessageDialog(mainFrame,"You have to choose at least one question and fill it. Otherwise, press cancel","Wrong action", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (!wrongQuestions.equals(""))
						JOptionPane.showMessageDialog(mainFrame,new JLabel("<HTML>Some new values have different types<br>"+wrongQuestions+" </HTML>"),"Wrong new values", JOptionPane.INFORMATION_MESSAGE);
						else {
				 setVisible(false);
				 wizard.finishedStep1();
						}
				}
			}

		});
		cancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				cancelled=true;
				setVisible(false);
				wizard.finishedStep1();
			}

		});
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				cancelled=true;
				setVisible(false);
				wizard.finishedStep1();
			}
			public void windowClosing(WindowEvent e) {
				cancelled=true;
				setVisible(false);
				wizard.finishedStep1();
			}
		});



	}

	public REGFrame(String title) throws HeadlessException {
		super(title);
		initGUI();
		wizard=new REGWizardInterface(){
			public void finishedStep1() {
			}

			public void finishedStep2() {
			}
		};

	}

	public REGFrame(String title, REGWizardInterface rw) throws HeadlessException {
		super(title);
		initGUI();
		this.wizard=rw;
	}
	
	

	private DefaultMutableTreeNode findNodeAspect(DefaultMutableTreeNode from, String area, String aspect){
		return aspectsNodes.get(area + "_" + aspect);
	}

	private DefaultMutableTreeNode findNodeArea(DefaultMutableTreeNode from, String area){		
		return areasNodes.get(area);
	}


	public void addQuestion(ATQuestionGraph at){
		questionBoxes.add(new QuestionPanel(at));
		questions.add(at);
		DefaultMutableTreeNode areaInsertionNode=null;
		DefaultMutableTreeNode aspectInsertionNode=null;		
		if (areasNodes.containsKey(at.getArea())){
			areaInsertionNode=areasNodes.get(at.getArea());
		} else {
			areaInsertionNode=new DefaultMutableTreeNode("Area: " + at.getArea());
			treeModel.insertNodeInto(areaInsertionNode, root,root.getChildCount());			
			areasNodes.put(at.getArea(),areaInsertionNode);
		}
		if (aspectsNodes.containsKey(at.getArea() + "_" + at.getAspect())){
			aspectInsertionNode=aspectsNodes.get(at.getArea() + "_" + at.getAspect());
		} else {
			aspectInsertionNode=new DefaultMutableTreeNode("Aspect: " + at.getAspect());			
			aspectsNodes.put(at.getArea() + "_" + at.getAspect(),aspectInsertionNode);
			treeModel.insertNodeInto(aspectInsertionNode, areaInsertionNode,areaInsertionNode.getChildCount());
		}				
		DefaultMutableTreeNode questionNode=new DefaultMutableTreeNode("Question: " + at.getName()+ "("+at.getPotentialAnswers().size()+")");
		treeModel.insertNodeInto(questionNode,aspectInsertionNode, aspectInsertionNode.getChildCount());
		questionNodes.put(questionNode, at);			

		areasAndAspectsTree.expandPath(new TreePath(aspectInsertionNode.getPath()));

	}
	
	public Vector<ATQuestionGraph> getQuestions(){
		return questions;
	}


	public void initializeFromBrowser() {
		List<ATQuestionGraph> allATQuestionGraph = new Vector<ATQuestionGraph>();
		try {
			allATQuestionGraph.add(new ATQuestionGraph_1_01_07(BrowserImp.getInstance()));
			allATQuestionGraph.add(new ATQuestionGraph_1_02_01(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_06_01_A(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_06_01_B(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_06_02_A(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_06_02_B(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_11_02(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_13_06(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_16_02(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_16_03(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_2_01_04(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_2_02_01_A(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_2_02_01_B(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_2_04_03(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_2_04_04(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_2_08_04(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_3_01_02(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_3_01_03(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_4_01_02(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_4_01_04(BrowserImp.getInstance()));
		} catch (NotInitialised e) {
			e.printStackTrace();
		}
		System.out.println("*** Initializing questions from specifications");
		Date d1 = new Date();
		Date d2;
		for (int i = 0; i < allATQuestionGraph.size(); i++) {
			allATQuestionGraph.get(i).solvePotentialAnswers();
			d2 = new Date();
			System.out.println("Elapsed milliseconds for " +
					allATQuestionGraph.get(i).getName() +
					" = " + (d2.getTime() - d1.getTime()) +
					", Potentials = " + allATQuestionGraph.get(i).getPotentialAnswers().size());
			d1 = d2;
			this.addQuestion(allATQuestionGraph.get(i));
		}
		System.out.println("*** Initialization has finished\n\n\n");
		// Sacar las respuestas preconfiguradas.
		/* for (int j = 0; j < allATQuestionGraph.size(); j++) {
	    	for (int k = 0; k < allATQuestionGraph.get(j).getPotentialAnswers().size(); k++) {	    		
	    		Graph oneAnswer = allATQuestionGraph.get(j).getPotentialAnswers(k);
	    		// A�adirla como actual.
	    		int ultima = allATQuestionGraph.get(j).getActualAnswers().size();
	    		allATQuestionGraph.get(j).setActualAnswers(ultima, oneAnswer);
	    	}
	    }*/
	}



	public static void main(String args[]){
		// Load the specifications for tests.
		try {
			Log.initInstance(new PrintWriter(System.out));
			BrowserImp.initialise("examples/cinema-IAF.xml");
			atai.questions.ATView.initializeBrowser(BrowserImp.getInstance());
		} catch (UnknowFormat e) {
			e.printStackTrace();
		} catch (DamagedFormat e) {
			e.printStackTrace();
		} catch (CannotLoad e) {
			e.printStackTrace();
		} catch (NotInitialised e) {
			e.printStackTrace();
		}
		// Show and use the interfaz.
		REGFrame qf = new REGFrame();
		/* Hashtable<String,ATEntity> varMap=new Hashtable<String,ATEntity>();
		varMap.put("var1", ATEntity.Activity);
		varMap.put("var2", ATEntity.Subject);
		List<String> textQuestion=new Vector<String>();
		textQuestion.add("una actividad");
		textQuestion.add("var1");
		textQuestion.add("es ejecutada por ");
		textQuestion.add("var2");
		List<Map<String,String>> potentialAnswers=new Vector<Map<String,String>>();
		List<Map<String,String>> actualAnswers=new Vector<Map<String,String>>();
		Hashtable<String,String> answer1=new Hashtable<String,String>();
		answer1.put("var1", "act1");
		answer1.put("var2", "paco");
		Hashtable<String,String> answer2=new Hashtable<String,String>();
		answer2.put("var1", "leches");
		answer2.put("var2", "pepe");
		potentialAnswers.add(answer1);
		potentialAnswers.add(answer2);
		ATQuestion atq=new ATQuestion("area 1", "aspect 1","q1","Esta es una descripción de prueba para ver qué tal funciona",varMap,textQuestion,potentialAnswers,actualAnswers);
		ATQuestion atq2=new ATQuestion("area 2", "aspect 2","q2","Esta es una descripción distinta que sirve para verificar todo",varMap,textQuestion,potentialAnswers,actualAnswers);
		qf.addQuestion(atq);
		qf.addQuestion(atq2); */
		qf.initializeFromBrowser();
		qf.setVisible(true);		
	}


	public boolean isCancelled() {
		return cancelled;
	}


}
