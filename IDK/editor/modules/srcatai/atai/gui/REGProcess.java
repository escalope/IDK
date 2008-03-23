package atai.gui;

import ingenias.editor.Log;
import ingenias.exception.CannotLoad;
import ingenias.exception.DamagedFormat;
import ingenias.exception.InvalidEntity;
import ingenias.exception.NotInitialised;
import ingenias.exception.UnknowFormat;
import ingenias.generator.browser.AttributedElement;
import ingenias.generator.browser.BrowserImp;
import ingenias.generator.browser.GraphEntityFactory;
import ingenias.generator.browser.GraphFactory;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import atai.questions.ATQuestionGraph;
import atai.questions.QuestionProcessing;

public class REGProcess implements REGWizardInterface{
	private static final int CANCELLED = 3;
	private static final int FINISHED_STEP1=1;
	private static final int FINISHED_STEP2=2;
	private static final int INITIAL=0;
	private int state=INITIAL;
	private Vector<ATQuestionGraph> questions=null; 
	
	public REGProcess() {}

	public void runProcess(){
		REGFrame qf = new REGFrame("REG: step 1/2",this);
		REGInitializing ri=new REGInitializing(this);
		ri.setLocation(300, 100);
		ri.pack();
		ri.setVisible(true);
		qf.initializeFromBrowser();
		ri.setVisible(false);
		qf.setLocation(300,100);
		qf.setVisible(true);
		// Step 1: selecting and answering questions
		waitStep1();
		if (!qf.isCancelled()){
			REGDisambiguation disamb=new REGDisambiguation("REG: step 2/2",this);
			 questions = qf.getQuestions();
			createTemporallyINGENIASEntities(questions);
			Vector<String> wrongQuesstions=new Vector<String>();
			for (ATQuestionGraph question:questions){	
				List<Map<String, String>> answers = question.getActualAnswers();
				List<List<String>> newElements = question.getNewElementsInActualAnswers();
				List<Map<String, AttributedElement>> mappingATINGENIASElements = question.getActualAnswersElements();			

				for (int k=0;k<answers.size();k++){
					Map<String, String> answer=answers.get(k);
					List<String> newElement=newElements.get(k);			
					Map<String, AttributedElement> equivalentINGENIASEntities = mappingATINGENIASElements.get(k);
					Hashtable<String, Vector<Vector<String>>> relationshipAssignments = 
						QuestionProcessing.findINGENIASEquivalentRelationships(question,answer);
					disamb.add(question,relationshipAssignments);
				}			
			}		
			// Step 2: disambiguating
			disamb.setLocation(300,100);
			disamb.setVisible(true);			
			disamb.pack();
			waitStep2();
			if (!disamb.isCancelled()){
				// Step 3: inserting new information
				Hashtable<ATQuestionGraph,Hashtable<String, Vector<String>>> 
				interpretation=disamb.getSelectedInterpretationPerQuestion();
				Collection<Hashtable<String, Vector<String>>> values = interpretation.values();
				GraphFactory gf=null;
				try {
					gf = GraphFactory.createDefaultGraphFactory();
					QuestionProcessing.createOutputPackages(gf);
				} catch (NotInitialised e) {
					
					e.printStackTrace();
				}
				
				for (Hashtable<String, Vector<String>> value:values){
					Hashtable<String, HashSet<Vector<String> >> diagramRelationshipsDistribution = 
						QuestionProcessing.findGraphConfigurationFromPossibleRelationships(value);
					QuestionProcessing.representAnswerInINGENIASDiagrams(diagramRelationshipsDistribution,value);
				}				
			} else {
				removeTemporallyCreatedINGENIASEntities(questions);
				cancelled();
			}
		} else 
			cancelled();

	}

	private void removeTemporallyCreatedINGENIASEntities(Vector<ATQuestionGraph> questions) {
		
		try {
			GraphEntityFactory gef=new GraphEntityFactory(BrowserImp.getInstance().getState());
			Hashtable<String,String> entitiesToRemove=new Hashtable<String,String>();
			for (ATQuestionGraph question:questions){
				List<Map<String, String>> newTypeList = question.getTypesOfNewElementsInActualAnswers();
				for (Map<String, String> newType:newTypeList){
					Set<String> valueNames = newType.keySet();
					for (String valueName:valueNames){
						entitiesToRemove.put(valueName,newType.get(valueName));
					}
				}
			}
			Set<String> valueNames = entitiesToRemove.keySet();
			for (String valueName:valueNames){
				try {
					gef.removeEntity(valueName);
				} catch (InvalidEntity e) {
					
					e.printStackTrace();
				}
			}
		} catch (NotInitialised e) {
			e.printStackTrace();
		}
	}



	private void createTemporallyINGENIASEntities(Vector<ATQuestionGraph> questions) {
		Hashtable<String,String> entitiesToCreate=new Hashtable<String,String>();
		for (ATQuestionGraph question:questions){
			List<Map<String, String>> newTypeList = question.getTypesOfNewElementsInActualAnswers();
			for (Map<String, String> newType:newTypeList){
				Set<String> valueNames = newType.keySet();
				for (String valueName:valueNames){
					entitiesToCreate.put(valueName,newType.get(valueName));
				}
			}
		}
		try {
			GraphEntityFactory gef=new GraphEntityFactory(BrowserImp.getInstance().getState());
			Set<String> valueNames = entitiesToCreate.keySet();
			for (String valueName:valueNames){
				gef.createEntityWithoutDiagram(entitiesToCreate.get(valueName), valueName);
			}
		} catch (NotInitialised e) {
			e.printStackTrace();
		}
	}




	private synchronized void waitStep2() {
		while (state==FINISHED_STEP1){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void waitStep1() {
		while (state==INITIAL){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public  synchronized void finishedStep1(){
		state=FINISHED_STEP1;
		notifyAll();
	}

	public  synchronized void finishedStep2(){
		state=FINISHED_STEP2;
		notifyAll();
	}
	
	public  synchronized void cancelled(){
		state=CANCELLED;
		notifyAll();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		new REGProcess().runProcess();
		// Show and use the interfaz.


	}

	public Vector<ATQuestionGraph> getQuestions() {
		return questions;
	}
	
	public boolean isCancelled() {
		return state==REGProcess.CANCELLED;
	}

}
