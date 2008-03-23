package atai.gui;

import ingenias.editor.Log;
import ingenias.exception.CannotLoad;
import ingenias.exception.DamagedFormat;
import ingenias.exception.NotInitialised;
import ingenias.exception.UnknowFormat;
import ingenias.generator.browser.BrowserImp;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import atai.questions.ATQuestion;
import atai.questions.ATQuestionGraph;
import atai.questions.ATEntity;
import atai.questions.Graph;
import atai.questions.Question;
import atai.questions.regGraph.*;

public class REGFrameORI extends JFrame {
	private Box questionBoxes=null;
	private DefaultMutableTreeNode root=null;
	private Vector<ATQuestion> questions=new Vector<ATQuestion>();
	private Hashtable<String, DefaultMutableTreeNode> areasNodes=new Hashtable<String, DefaultMutableTreeNode>();
	private Hashtable<String, DefaultMutableTreeNode> aspectsNodes=new Hashtable<String, DefaultMutableTreeNode>();
	private Hashtable<DefaultMutableTreeNode, ATQuestion> questionNodes=new Hashtable<DefaultMutableTreeNode, ATQuestion>();
	private Hashtable<DefaultMutableTreeNode, QuestionPanel> questionPanels=new Hashtable<DefaultMutableTreeNode, QuestionPanel>();
	private JSplitPane mainPanel=null;
	private JTree areasAndAspectsTree=null;
	private DefaultTreeModel treeModel=null;

	public REGFrameORI() throws HeadlessException {
		initGUI();
		setTitle("REG Wizard");
	}
	

	private void initGUI(){
		root = new DefaultMutableTreeNode("REG");

		treeModel=new DefaultTreeModel(root);
		areasAndAspectsTree=new JTree(treeModel);

		mainPanel=new JSplitPane();		
		questionBoxes=Box.createVerticalBox();
		mainPanel.setRightComponent(new JPanel());
		mainPanel.setLeftComponent(new JScrollPane(areasAndAspectsTree));
		mainPanel.setDividerLocation(200);
		this.setSize(700, 500);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		JButton apply=new JButton("Apply solutions");
		JButton cancel=new JButton("Cancel");
		JPanel south=new JPanel();
		south.add(apply);
		south.add(cancel);
		this.getContentPane().add(south, BorderLayout.SOUTH);

		areasAndAspectsTree.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				if (areasAndAspectsTree.getSelectionPath()!=null){
					DefaultMutableTreeNode dtn=(DefaultMutableTreeNode)areasAndAspectsTree.getSelectionPath().getLastPathComponent();
					QuestionPanel qp=null;
					if (questionNodes.containsKey(dtn)){
						if (!questionPanels.containsKey(dtn)){
							qp=new QuestionPanel(questionNodes.get(dtn));
							questionPanels.put(dtn,qp);			 
						} else {
							qp=questionPanels.get(dtn);					 
						}
						mainPanel.setRightComponent(qp);
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
				for (Question question:questionNodes.values()){
					System.out.println(question.getId()+" :"+question.getActualAnswers());
				}
				setVisible(false);
			}

		});
		cancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}

		});



	}


	public REGFrameORI(String title) throws HeadlessException {
		super(title);
		initGUI();
	}

	private DefaultMutableTreeNode findNodeAspect(DefaultMutableTreeNode from, String area, String aspect){
		return aspectsNodes.get(area + "_" + aspect);
	}

	private DefaultMutableTreeNode findNodeArea(DefaultMutableTreeNode from, String area){		
		return areasNodes.get(area);
	}


	public void addQuestion(ATQuestion at){
		questionBoxes.add(new QuestionPanel(at));
		questions.add(at);
		DefaultMutableTreeNode areaInsertionNode=null;
		DefaultMutableTreeNode aspectInsertionNode=null;		
		if (areasNodes.containsKey(at.getArea())){
			areaInsertionNode=areasNodes.get(at.getArea());
		} else {
			areaInsertionNode=new DefaultMutableTreeNode("Area: " + at.getArea());
			treeModel.insertNodeInto(areaInsertionNode, root, 0);			
			areasNodes.put(at.getArea(),areaInsertionNode);
		}
		if (aspectsNodes.containsKey(at.getArea() + "_" + at.getAspect())){
			aspectInsertionNode=aspectsNodes.get(at.getArea() + "_" + at.getAspect());
		} else {
			aspectInsertionNode=new DefaultMutableTreeNode("Aspect: " + at.getAspect());			
			aspectsNodes.put(at.getArea() + "_" + at.getAspect(),aspectInsertionNode);
			treeModel.insertNodeInto(aspectInsertionNode, areaInsertionNode, 0);
		}				
		DefaultMutableTreeNode questionNode=new DefaultMutableTreeNode("Question: " + at.getName());
		treeModel.insertNodeInto(questionNode,aspectInsertionNode, 0);
		questionNodes.put(questionNode, at);			

		areasAndAspectsTree.expandPath(new TreePath(aspectInsertionNode.getPath()));
		/*treeModel.reload();
		areasAndAspectsTree.revalidate();*/
	}


	
	public void initializeFromBrowser() {
		// Load the specifications for tests.
	    List<ATQuestionGraph> allATQuestionGraph = new Vector<ATQuestionGraph>();
	    try {
	    	allATQuestionGraph.add(new ATQuestionGraph_1_01_07(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_02_01(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_06_01_A(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_06_02_A(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_11_02(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_13_06(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_16_02(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_1_16_03(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_2_01_04(BrowserImp.getInstance()));
	    	allATQuestionGraph.add(new ATQuestionGraph_2_02_01_A(BrowserImp.getInstance()));
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
	    Date d1 = new Date();
	    Date d2;
	    for (int i = 0; i < allATQuestionGraph.size(); i++) {
	    	allATQuestionGraph.get(i).solvePotentialAnswers();
			d2 = new Date();
			System.out.println("Elapsed milliseconds for " +
					allATQuestionGraph.get(i).getName() +
					"= " + (d2.getTime() - d1.getTime()));
			d1 = d2;
			this.addQuestion(allATQuestionGraph.get(i));
	    }
	    // Sacar las respuestas preconfiguradas.
	    for (int j = 0; j < allATQuestionGraph.size(); j++) {
	    	for (int k = 0; k < allATQuestionGraph.get(j).getPotentialAnswers().size(); k++) {
	    		Graph oneAnswer = allATQuestionGraph.get(j).getPotentialAnswers(k);
	    		// A�adirla como actual.
	    		int ultima = allATQuestionGraph.get(j).getActualAnswers().size();
	    		allATQuestionGraph.get(j).setActualAnswers(ultima, oneAnswer);
	    	}
	    }
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
		REGFrameORI qf = new REGFrameORI();
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


}
