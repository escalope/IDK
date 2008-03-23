package atai.gui;

import ingenias.editor.ProjectProperty;
import ingenias.editor.StatsManager;
import ingenias.generator.browser.BrowserImp;

import java.util.Vector;

import atai.questions.ATQuestionGraph;

/**
 * A module to integrate the REG in the IDK.
 * @author ruben
 *
 */
public class REGModule extends ingenias.editor.extension.BasicToolImp  {

	/**
	 * 
	 * @param file
	 * @throws Exception
	 */
	public REGModule(String file) throws Exception {
		super(file);

	}



	/**
	 * 
	 * @throws Exception
	 */
	public REGModule() throws Exception {
		super();
		atai.questions.ATView.initializeBrowser(BrowserImp.getInstance());
	}



	/**
	 * This module defines no properties
	 * @return    Empty properties
	 */
	protected Vector<ProjectProperty> defaultProperties() {
		Vector<ProjectProperty> result=new Vector<ProjectProperty>();
		return result;
	}



	/**
	 * Get the description of this module
	 * @return    The description
	 */
	public String getDescription() {
		return "This module helps to evolve the specifications usgin the Requiremnts Elicitation Guide.";
	}



	/**
	 * Get the name of this module
	 * @return    The name
	 */
	public String getName() {
		return "REG";
	}



	/**
	 *  It creates stats of usage by traversing diagrams of your specification.
	 *  Resulting report appears in standard output or in the IDE
	 */
	public void run() {
		new Thread(){
			public void run(){
				long initTime=System.currentTimeMillis();
				REGProcess process=new REGProcess();
				process.runProcess();
				if (!process.isCancelled()){
					Vector<ATQuestionGraph> questions = process.getQuestions();
					long cTime=System.currentTimeMillis();
					Vector<String> results=new Vector<String>(); 
					for (ATQuestionGraph question:questions){
						boolean filledIn=question.getActualAnswers().isEmpty();
						boolean assisted=question.getAnswerCreatedWithAssistance();
						results.add(question.getArea()+","+question.getAspect()+","+question.getId()+","+filledIn+","+assisted);			

					}
					StatsManager.saveSessionREG(cTime, initTime,results);
				}
			}}.start();

	}

}