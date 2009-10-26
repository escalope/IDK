/*
    Copyright (C) 2005 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net.

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 */
package ingenias.codeproc;

import ingenias.codeproc.InteractionGeneration;
import ingenias.editor.Log;
import ingenias.editor.ProjectProperty;
import ingenias.exception.NotFound;
import ingenias.exception.NotInitialised;
import ingenias.exception.NullEntity;
import ingenias.generator.browser.Browser;
import ingenias.generator.browser.Graph;
import ingenias.generator.browser.GraphAttribute;
import ingenias.generator.browser.GraphCollection;
import ingenias.generator.browser.GraphEntity;
import ingenias.generator.browser.GraphRelationship;
import ingenias.generator.browser.GraphRole;
import ingenias.generator.datatemplate.Repeat;
import ingenias.generator.datatemplate.Sequences;
import ingenias.generator.datatemplate.Var;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

/**
 *  This class implements an IDK module for the production of MAS based on the JADE platform. The transformation
 *  is divided into three parts, those referring to agent, tasks, and applications (IAFGenerator), interactions
 *  and associated protocols (InteractionGeneration), and others related to mental state information (MentalStateGeneration)
 *
 *@author     Jorge Gomez
 *@created    29 de marzo de 2003
 */
public class IAFGenerator
extends ingenias.editor.extension.BasicCodeGeneratorImp {


	InteractionGeneration ig = null;
	MentalStateGeneration msg = new MentalStateGeneration(this);

	private boolean error = false;




	/**
	 *  Initialises code generation and prepares the module to receive data from an external file
	 *
	 *@param  file             Path to file containing INGENIAS specification
	 *@exception  Exception    Error accessing any file or malformed XML exception
	 */
	public IAFGenerator(String file) throws ingenias.exception.UnknowFormat,
	ingenias.exception.DamagedFormat,
	ingenias.exception.CannotLoad,
	java.io.FileNotFoundException {

		super(file);

		try {
			this.addTemplate("templates/agent.xml");
			this.addTemplate("templates/commcontrol.xml");
			this.addTemplate("templates/smachines.xml");
			this.addTemplate("templates/launcher.xml");
			this.addTemplate("templates/facts.xml");
			this.addTemplate("templates/task.xml");
			this.addTemplate("templates/application.xml");
			this.addTemplate("templates/builders.xml");
			this.addTemplate("templates/customlocks.xml");
			this.addTemplate("templates/agentprotocols.xml");
			this.addTemplate("templates/testinglauncher.xml");
			this.addTemplate("templates/test.xml");
			this.addTemplate("templates/events.xml");
			this.addTemplate("templates/launcherProd.xml");

		} catch (java.io.FileNotFoundException fne) {
			Log.getInstance().logERROR(fne.getMessage());
			this.fatalError();
		}
		ig = new InteractionGeneration(this,getBrowser());
	}

	/**
	 * Initialises code generation and prepares the module to receive data from the IDK
	 *
	 * @throws java.io.FileNotFoundException One of the assigned templates could not be found
	 */
	public IAFGenerator(Browser browser) throws java.io.FileNotFoundException {
		super(browser);
		try {
			this.addTemplate("templates/agent.xml");
			this.addTemplate("templates/commcontrol.xml");
			this.addTemplate("templates/smachines.xml");
			this.addTemplate("templates/launcher.xml");
			this.addTemplate("templates/facts.xml");
			this.addTemplate("templates/task.xml");
			this.addTemplate("templates/application.xml");
			this.addTemplate("templates/builders.xml");
			this.addTemplate("templates/customlocks.xml");
			this.addTemplate("templates/agentprotocols.xml");
			this.addTemplate("templates/testinglauncher.xml");
			this.addTemplate("templates/test.xml");
			this.addTemplate("templates/events.xml");
			this.addTemplate("templates/launcherProd.xml");
		} catch (java.io.FileNotFoundException fne) {
			Log.getInstance().logERROR(fne.getMessage());
			this.fatalError();
		}

		ig = new InteractionGeneration(this,getBrowser());
	}

	/**
	 * There is only one default property of this module. It is called "jadeout" and represents the path
	 * to the folder where the generated code is going to be stored
	 *
	 */
	public Vector<ProjectProperty> defaultProperties() {
		Vector<ProjectProperty> pp = new Vector<ProjectProperty>();

		pp.add(new ingenias.editor.ProjectProperty(this.getName(), "jadeproject", "JADE main project folder",
				"myproject", "The folder that will contain the project for this development"));

		pp.add(new ingenias.editor.ProjectProperty(this.getName(), "jadeout", "JADE generated output folder",
				"gensrc",
		"The folder that will hold generated JADE agents"));
		pp.add(new ingenias.editor.ProjectProperty(this.getName(), "jadeperm", "JADE generate only once folder",
				"permsrc",
		"The folder that will hold generated elements that should not be regenerated"));
		pp.add(new ingenias.editor.ProjectProperty(this.getName(), "proysrc", "Main source folder for the project",
				"src",
		"The folder containing the sources of the project"));

		/*pp.add(new ingenias.editor.ProjectProperty(this.getName(), "producecode", "Triggers the use of code components",
				"yes",
		"Write \"yes\" if you want to use code componentes, and \"no\" in any other case"));*/

		return pp;
	}

	/**
	 * It obtains the name associated to this module
	 *
	 */
	public String getName() {
		return "Ingenias Agent Framework generator";
	}

	public boolean doIGenerateComponentAndTaskCode() {
		ProjectProperty pcode = this.getProperty("producecode");
		return pcode != null && pcode.value.equalsIgnoreCase("yes");
	}

	/**
	 * It obtains a description of this module
	 */
	public String getDescription() {
		return "It generates communication and behavior infrastructure of MAS composed of JADE agents ";
	}

	/**
	 * It takes as input a graph containing a mental state representation. It looks for FrameFact entities
	 * and tries to locate which role do they play in the associateion that links them with the mental state
	 * entity.
	 * Located nodes are added to the repeat parameter as "mentity" var and "nameentity" var. The first contains
	 * the name of the framefact in the mental state. The second the label appearing in the association that links
	 * the framefact to the mental state entity.
	 *
	 */
	private void addInvolvedEntitiesIntoAMentalState(Graph g, Repeat r) throws NullEntity {
		GraphEntity[] ents = g.getEntities();
		for (int k = 0; k < ents.length; k++) {
			if (ents[k].getType().equals("FrameFact")) {
				Repeat hastoExist = new Repeat("hastoexist");
				r.add(hastoExist);
				hastoExist.add(new Var("mentity", Utils.replaceBadChars(ents[k].getID())));
				GraphRelationship[] gr = ents[k].getRelationships();
				if (gr.length == 1) {
					GraphRole[] rols = gr[0].getRoles();
					int j = 0;
					for (j = 0; j < rols.length && rols[j].getName().equals("AContainsMEsource"); j++) {
						;
					}
					if (j < rols.length) {
						try {
							hastoExist.add(new Var("namementity", Utils.replaceBadChars(rols[j].getAttributeByName("Label").getSimpleValue())));
						} catch (NotFound e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	/**
	 * For an interaction unit, it gathers information about which are the required mental state of the agent
	 * before starting the interaction unit, i.e., initiating the communication. These requirements appear as an agent diagram which is returned
	 * as result of the search.
	 *
	 * @param iu The interaction unit to be considered
	 * @return The agent diagram containing the required mental state
	 */
	private Graph getInitialMentalStateForInteraction(GraphEntity iu) {
		GraphRelationship[] gr = iu.getRelationships();
		int k = 0;
		for (k = 0; k < gr.length && !(gr[k].getType().equals("UIInitiates")); k++) {
			;
		}
		if (k < gr.length) {
			GraphAttribute at;
			try {
				at = gr[k].getAttributeByName("Condition");
				try {
					if (at != null && at.getEntityValue() != null) {
						if (at.getEntityValue().getAttributeByName("DescriptionWithAgentModel") == null) {
							// There is a condition but not a description
						} else {
							String dname = at.getEntityValue().getAttributeByName("DescriptionWithAgentModel").getSimpleValue();
							return this.browser.getGraph(dname);
						}
					}
				} catch (NullEntity e) {
					// Null entity is not relevant here
					//e.printStackTrace();
				}
			} catch (NotFound e) {
				e.printStackTrace();
			}

		}
		return null;
	}



	/**
	 * It produces the data structure needed to produce the templates. First, it generates the
	 * data structure for the definition of individual protocol per agents, then their actions, following their
	 * knowledge, next their resources (applications used by the agents to perform tasks).
	 *
	 */
	public Sequences generate() {
		this.error = false;
		Sequences p = new Sequences();
		try {
			p.addVar(new Var("jadeout",
					this.getProperty("jadeout").value));
			p.addVar(new Var("jadeperm",
					this.getProperty("jadeperm").value));
			p.addVar(new Var("proysrc",
					this.getProperty("proysrc").value));
			p.addVar(new Var("jadeproject",
					this.getProperty("jadeproject").value));
			Hashtable components = this.getComponents();
			Hashtable filesapp = this.getFileAppAssociation();
			this.generateAgentXML(p, filesapp, components);
			this.generateTaskXML(p);
			this.generateFactXML(p);
			this.generateEventXML(p);
			this.generateAppsXML(p);
			TestGenerator tg=new TestGenerator(getBrowser()); 
			DeploymentGenerator.generateDeployment(p,this,getBrowser());
			tg.generateTestingDeployment(p,this,getBrowser());
			tg.generateTests(p,this);
			if (!getError()) {
				this.ig.generateActorActions(p);
			}
			File outputFile=File.createTempFile("ing", "_output");
			FileOutputStream fos = new FileOutputStream(outputFile);
			fos.write(p.toString().getBytes());
			fos.close();

		} catch (Exception exp) {
			exp.printStackTrace();
		}

		return p;
	}

	/**
	 * It obtains information about the association between the entities and actual files. It is prepared to
	 * be used mainly with Application entities. This information is later used by
	 *
	 * @return
	 * @throws NotFound
	 * @throws NullEntity
	 * @throws NotInitialised
	 */
	private Hashtable getComponents() throws NotFound, NullEntity, NotInitialised {
		Hashtable components = new Hashtable();
		GraphEntity[] ge = Utils.generateEntitiesOfType("INGENIASComponent",browser);
		for (int k = 0; k < ge.length; k++) {
			GraphAttribute files;
			GraphEntity component = ge[k];
			try {
				files = component.getAttributeByName("Files");
			} catch (NotFound e) {
				Log.getInstance().logERROR("Entity " + component.getID() + " did not have attribute Files", "", component.getID());
				this.fatalError();
				throw e;
			}
			GraphCollection gc;
			try {
				gc = files.getCollectionValue();
			} catch (NullEntity e) {
				Log.getInstance().logERROR("Entity " + component.getID() + " do not have any collection of Application-File associated", "", component.getID());
				this.fatalError();
				throw e;
			}
			boolean itis = false;
			int j = 0;
			while (j < gc.size()) {
				GraphEntity filespec = null;
				try {
					filespec = gc.getElementAt(j);
					if (filespec.getType().equals("FileSpecPatternMapping")) {
						components.put(filespec.getAttributeByName("Entity").getEntityValue().getID(),
								component);

					}
					j++;
				} catch (NullEntity e) {
					Log.getInstance().logERROR("Entity " + component.getID() + " hash an entry with empty entity", "", component.getID());
					this.fatalError();
					throw e;
				}

			}

		}
		return components;
	}

	/**
	 * It obtains a table whose index are applications and the value an associated file. This method
	 * is applied to link implementation specific elements with the specification
	 *
	 * @return
	 * @throws NotFound
	 * @throws NullEntity
	 * @throws NotInitialised
	 */
	private Hashtable getFileAppAssociation() throws NotFound, NullEntity, NotInitialised {
		Hashtable resultfileapps = new Hashtable();
		GraphEntity[] ge = Utils.generateEntitiesOfType("INGENIASComponent",browser);
		for (int k = 0; k < ge.length; k++) {
			GraphAttribute files;
			GraphEntity component = ge[k];
			try {
				files = component.getAttributeByName("Files");
			} catch (NotFound e) {
				Log.getInstance().logERROR("Entity " + component.getID() + " did not have attribute Files", "", component.getID());
				this.fatalError();
				throw e;
			}
			GraphCollection gc;
			try {
				gc = files.getCollectionValue();
			} catch (NullEntity e) {
				Log.getInstance().logERROR("Entity " + component.getID() + " do not have any collection of Application-File associated", "", component.getID());
				this.fatalError();
				throw e;
			}
			boolean itis = false;
			int j = 0;
			while (j < gc.size()) {
				GraphEntity filespec = null;
				try {
					filespec = gc.getElementAt(j);
					if (filespec.getType().equals("FileSpecPatternMapping")) {
						resultfileapps.put(filespec.getAttributeByName("Entity").getEntityValue().getID(),
								filespec.getAttributeByName("File").getSimpleValue());
					}
					j++;
				} catch (NullEntity e) {
					Log.getInstance().logERROR("Entity " + component.getID() + " hash an entry with empty entity", "", component.getID());
					this.fatalError();
					throw e;
				}

			}

		}
		return resultfileapps;
	}



	/**
	 * Obtains the value of the error flag
	 *
	 * @return true if there was an error previously or false if there was none
	 */
	boolean getError() {
		return this.error;
	}

	/**
	 * It produces the data infrastructure required to produce tasks code
	 *
	 * @param p the data structure
	 * @throws Exception
	 */
	private void generateTaskXML(Sequences p) throws Exception {
		GraphEntity[] tasks = Utils.generateEntitiesOfType("Task",browser);
		for (int k = 0; k < tasks.length; k++) {
			Repeat r = new Repeat("tasks");			
			this.generateTaskCode(tasks[k], r);
			p.addRepeat(r);
		}
	}

	/**
	 * It collects information about slots contained in a framefact type. For each fact, it extracts
	 * all the contained slots (name and type).
	 *
	 * @param r The data structure
	 * @param fact The fact being studied
	 */
	private void generateFactSlots(Repeat r, GraphEntity fact) {
		try {
			GraphAttribute ga = fact.getAttributeByName("slots");
			if (ga != null) {
				GraphCollection gc = ga.getCollectionValue();
				if (gc != null) {
					for (int k = 0; k < gc.size(); k++) {
						GraphEntity slot = gc.getElementAt(k);
						if (slot.getType().equals("Slot")) {
							Repeat slotr = new Repeat("slot");
							r.add(slotr);
							slotr.add(new Var("slotname", Utils.replaceBadChars(slot.getAttributeByName("name").getSimpleValue())));
							slotr.add(new Var("slottype", slot.getAttributeByName("type").getSimpleValue()));
							slotr.add(new Var("slotid", Utils.replaceBadChars(slot.getID())));
						} else {
							Repeat slotr = new Repeat("slot");
							r.add(slotr);
							slotr.add(new Var("slotname", Utils.replaceBadChars(slot.getAttributeByName("name").getSimpleValue())));
							slotr.add(new Var("slottype", slot.getAttributeByName("type").getSimpleValue()));
							slotr.add(new Var("slotid", Utils.replaceBadChars(slot.getID())));
						}
						//						slotr.add(new Var("slotvalue",slot.getAttributeByName("Value").getSimpleValue()));


						//System.err.println("---------added "+slot);
					}
				}
			}
		} catch (ingenias.exception.NotFound nf) {
			Log.getInstance().logWARNING("Fact " + fact.getID() + " has no slots define. This may cause some mental state conditions to fail");
			nf.printStackTrace();
		} catch (NullEntity e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * It extracts information from all known framefact instances in the specification.
	 * Information is dumped in the data structure
	 *
	 * @param p The data structure
	 * @throws Exception
	 */
	private void generateFactXML(Sequences p) throws Exception {
		GraphEntity[] ffacts = Utils.generateEntitiesOfType("FrameFact",getBrowser());
		for (int k = 0; k < ffacts.length; k++) {
			if (!(ffacts[k].getID().equalsIgnoreCase("agent_data") || ffacts[k].getID().equalsIgnoreCase("agent data"))) {
				Repeat r = new Repeat("ffacts");
				r.add(new Var("fname", Utils.replaceBadChars(ffacts[k].getID())));
				generateFactSlots(r, ffacts[k]);
				p.addRepeat(r);
			}

		}
	}

	/**
	 * It extracts information from all known framefact instances in the specification.
	 * Information is dumped in the data structure
	 *
	 * @param p The data structure
	 * @throws Exception
	 */
	private void generateEventXML(Sequences p) throws Exception {
		GraphEntity[] ffacts = Utils.generateEntitiesOfType("ApplicationEventSlots",getBrowser());
		for (int k = 0; k < ffacts.length; k++) {
			Repeat r = new Repeat("events");
			r.add(new Var("fname", Utils.replaceBadChars(ffacts[k].getID())));
			generateFactSlots(r, ffacts[k]);
			p.addRepeat(r);
		}
		ffacts = Utils.generateEntitiesOfType("ApplicationEvent",getBrowser());
		for (int k = 0; k < ffacts.length; k++) {
			Repeat r = new Repeat("events");
			r.add(new Var("fname", Utils.replaceBadChars(ffacts[k].getID())));
			generateFactSlots(r, ffacts[k]);
			p.addRepeat(r);
		}
		ffacts = Utils.generateEntitiesOfType("GeneralEvent",getBrowser());
		for (int k = 0; k < ffacts.length; k++) {
			Repeat r = new Repeat("events");
			r.add(new Var("fname", Utils.replaceBadChars(ffacts[k].getID())));
			generateFactSlots(r, ffacts[k]);
			p.addRepeat(r);
		}

	}


	/**
	 * Initially, the agent mental state contains all the goals associated by means of "APursues" relationships
	 * to the agent "agent". Also, it finds all facts associated to a MentalState entity linked to "agent" by
	 * means of "AHasMS" relationship. Finally, for each goal, the code also inserts information about which
	 * tasks may satisfy the goal.
	 * @param r The data structure
	 * @param agent	The agent being studied
	 * @param agentApps 
	 * @param vectorPlayedRoles 
	 * @throws NullEntity
	 * @throws NotFound
	 * @throws NotInitialised
	 */
	private void generateInitialMentalState(Repeat r, GraphEntity agent, HashSet agentApps, Vector<GraphEntity> vectorPlayedRoles) throws NullEntity, NotFound, NotInitialised {
		Vector goals = this.getAgentGoals(agent);

		Enumeration enumeration = goals.elements();
		while (enumeration.hasMoreElements()) {
			Repeat initialGoals = new Repeat("initialGoals");
			GraphEntity goal = (GraphEntity) enumeration.nextElement();
			initialGoals.add(new Var("igoal", Utils.replaceBadChars(goal.getID())));
			r.add(initialGoals);
			Vector sattasks = this.solvingTask(goal, agent);
			Enumeration senumeration = sattasks.elements();
			//System.err.println("goals"+goals.size());
			while (senumeration.hasMoreElements()) {
				GraphEntity task = (GraphEntity) senumeration.nextElement();
				HashSet<GraphEntity> ints = this.getAssociatedInteractions(task);
				if (ints.size() == 0) {
					Repeat satgoal = new Repeat("satgoal");
					satgoal.add(new Var("tgoal", Utils.replaceBadChars(goal.getID())));
					this.generateAgentTask(satgoal, agent, task, agentApps, vectorPlayedRoles);
					r.add(satgoal);
				} else {
					Vector<GraphEntity> roles=getRolesPlayedByAgentWhenExecutingTheTask(agent,task);
					for (GraphEntity role:roles){
						System.err.println("generar " + agent.getID());
						Repeat convtask = new Repeat("convtask");
						insertDescedantsOfRoleWithDescendantRolesRepeat(role,
								convtask);
						insertAscedantsOfRoleWithAscendantsRolesRepeat(role,
								convtask);
						for (GraphEntity conv : ints) {
							Repeat conversations = new Repeat("conversations");
							conversations.add(new Var("arrangedconversations", Utils.replaceBadChars(conv.getID())));
							convtask.add(conversations);
						}
						Repeat satgoal = new Repeat("satgoal");
						satgoal.add(new Var("tgoal", Utils.replaceBadChars(goal.getID())));
						this.generateAgentConversationalTask(satgoal, agent, role,task, agentApps, vectorPlayedRoles);
						convtask.add(satgoal);
						r.add(convtask);
					}
					if (roles.isEmpty()){
						System.err.println("generar " + agent.getID());
						Repeat convtask = new Repeat("convtask");
						for (GraphEntity conv : ints) {
							Repeat conversations = new Repeat("conversations");
							conversations.add(new Var("arrangedconversations", Utils.replaceBadChars(conv.getID())));
							convtask.add(conversations);
						}
						Repeat satgoal = new Repeat("satgoal");
						satgoal.add(new Var("tgoal", Utils.replaceBadChars(goal.getID())));
						this.generateAgentConversationalDirectTask(satgoal, agent, task, agentApps, vectorPlayedRoles);
						convtask.add(satgoal);
						r.add(convtask);	
					}
				}

			}
		}

		Vector mentalstates = Utils.getRelatedElementsVector(agent, "AHasMS", "AHasMStarget");
		int k = 0;
		for (k = 0; k < mentalstates.size() && !((GraphEntity) mentalstates.elementAt(k)).getType().equals("MentalState"); k++) {
			;
		}

		if (k < mentalstates.size()) {
			// initial mental state found
			Vector elements = Utils.getRelatedElementsVector((GraphEntity) mentalstates.elementAt(k),
					"AContainsME", "AContainsMEtarget");
			for (int j = 0; j < elements.size(); j++) {
				GraphEntity current = (GraphEntity) elements.elementAt(j);
				if (current.getType().equals("FrameFact")) {
					//javax.swing.JOptionPane.showMessageDialog(null,agent+" is adding "+current);
					Repeat initialFacts = new Repeat("initialFacts");
					r.add(initialFacts);
					initialFacts.add(new Var("ifact", Utils.replaceBadChars(current.getID())));
					generateFactSlots(initialFacts, current);
				}
			}
		} /*else
                javax.swing.JOptionPane.showMessageDialog(null,agent+" has no initial ms");*/
	}



	private Vector<GraphEntity> getRolesPlayedByAgentWhenExecutingTheTask(
			GraphEntity agent, GraphEntity task) {
		try {
			Hashtable<GraphEntity, Vector<GraphEntity>> taskPerRole = getAgentTasksPerRole(agent);
			Set<GraphEntity> rolesPlayed = taskPerRole.keySet();
			Vector<GraphEntity> rolesToReturn=new Vector<GraphEntity>(); 
			for (GraphEntity rol:rolesPlayed){
				Vector<GraphEntity> tasks = taskPerRole.get(rol);
				if (tasks.contains(task)){
					rolesToReturn.add(rol);
				}
			}
			return rolesToReturn;
		} catch (NullEntity e) {			
			e.printStackTrace();
		}				
		return null;
	}

	private boolean isConversational(GraphEntity task) throws NullEntity {

		return getAssociatedInteractions(task).size() > 0;
	}

	private HashSet<GraphEntity> getAssociatedInteractions(GraphEntity task) throws NullEntity {
		HashSet<GraphEntity> initiatesTasks = new HashSet<GraphEntity>(Utils.getRelatedElementsVector(task,
				"UIInitiates", "UIInitiatessource"));
		HashSet<GraphEntity> result = new HashSet<GraphEntity>();
		for (GraphEntity ui : initiatesTasks) {
			result.add(getAssociatedInteraction(ui));
		}

		HashSet<GraphEntity> colaboratesTasks = new HashSet<GraphEntity>(Utils.getRelatedElementsVector(task,
				"UIColaborates", "UIColaboratessource"));

		for (GraphEntity ui : colaboratesTasks) {
			result.add(getAssociatedInteraction(ui));
		}

		HashSet<GraphEntity> accessesTasks = new HashSet<GraphEntity>(Utils.getRelatedElementsVector(task,
				"IAccesses", "IAccessestarget"));
		result.addAll(accessesTasks);
		//System.err.println("Para la tarea " + task.getID() + " hay las siguientes interacciones " + result);
		return result;
	}

	private GraphEntity getAssociatedInteraction(GraphEntity ui) {

		GraphEntity[] interactions;
		try {
			interactions = Utils.generateEntitiesOfType("Interaction",getBrowser());
			int j = 0;
			boolean found = false;
			GraphEntity result = null;
			while (!found && j < interactions.length) {
				GraphEntity interaction = interactions[j];
				GraphEntity[] ius = this.ig.getIUs(interaction);
				int k = 0;

				while (!found && k < ius.length) {
					found = ius[k].getID().equalsIgnoreCase(ui.getID());
					k++;
				}
				if (found) {
					result = interactions[j];
				}
				j++;
			}
			return result;
		} catch (NotInitialised e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private Vector getAgentGoals(GraphEntity agent) throws NullEntity {
		Vector directGoals = Utils.getRelatedElementsVector(agent,
				"GTPursues",
		"GTPursuestarget");		
		Vector<GraphEntity> rolesPlayed = Utils.getRelatedElementsVector(agent,
				"WFPlays",
		"WFPlaystarget");
		Vector<GraphEntity> additionalRoles=new Vector<GraphEntity>();
		for (GraphEntity ge:rolesPlayed){
			additionalRoles.addAll(Utils.getAscendantsOfRole(ge));
		}
		rolesPlayed.addAll(additionalRoles);
		Enumeration enumeration = rolesPlayed.elements();
		while (enumeration.hasMoreElements()) {
			GraphEntity role = (GraphEntity) enumeration.nextElement();
			Vector goalsPursuedByRoles = Utils.getRelatedElementsVector(role,
					"GTPursues",
			"GTPursuestarget");
			directGoals.addAll(goalsPursuedByRoles);
		}

		HashSet goals = new HashSet(directGoals);// To eliminate repeated elements
		return new Vector(goals);
	}

	/**
	 * It determines which of the tasks associated to the agent "agent" can actually help
	 * in solving the goal "goal". The filtered tasks list is returned as a list
	 *
	 * @param goal	The goal being studied
	 * @param agent The agent being studied
	 * @return
	 * @throws NullEntity
	 */
	private Vector solvingTask(GraphEntity goal, GraphEntity agent) throws NullEntity {
		Vector tasks = this.getAgentTasks(agent);
		Enumeration enumeration = tasks.elements();
		Vector v = goal.getAllRelationships();
		for (int k = 0; k < v.size(); k++) {
			//System.err.print(((GraphRelationship)v.elementAt(k)).getType()+",");
		}
		//System.err.println();

		Vector satisfyingTasks = Utils.getRelatedElementsVector(goal,
				"GTSatisfies",
		"GTSatisfiessource");
		//System.err.println("Testing "+goal +" for agent "+
		//		agent+ " satisfying"+satisfyingTasks.size());
		Vector solvingTasks = new Vector();
		enumeration = satisfyingTasks.elements();
		while (enumeration.hasMoreElements()) {
			GraphEntity task = (GraphEntity) enumeration.nextElement();
			//System.err.println("evaluating a:"+agent+" t:"+task+" g:"+goal);
			if (tasks.contains(task)) {
				solvingTasks.add(task);
			}
		}
		return solvingTasks;

	}


	/**
	 * For each task associated to the agent directly (with relationship "WFResponsible") or
	 * indirectly (by means of role playing), the
	 *
	 * @param r The structure where information will be added
	 * @param agent	The agent being studied
	 * @param task	The task whose data for code generation is supposed to be inserted
	 * @param roles 
	 * @throws NullEntity
	 * @throws NotFound
	 * @throws NotInitialised
	 */
	private void generateAgentTask(Repeat r, GraphEntity agent,
			GraphEntity task, HashSet agentApps, Vector<GraphEntity> roles) throws NullEntity, NotFound, NotInitialised {


		Repeat atask = new Repeat("agentTasks");
		r.add(atask);
		generateTaskCode(task, atask);

		verifyAgentTasks(agent, task, agentApps, roles);

	}

	private void generateAgentConversationalDirectTask(Repeat r, 
			GraphEntity agent, GraphEntity task, HashSet agentApps,
			Vector<GraphEntity> roles) throws NullEntity, NotFound, NotInitialised {


		Repeat atask = new Repeat("agentTasks");
		r.add(new Var("roleconvtask",""));		
		r.add(new Var("directtask","true"));	
		r.add(atask);
		generateTaskCode(task, atask);

		//		verifyAgentTasks(agent, task, agentApps, roles);

	}

	private void generateAgentConversationalTask(Repeat r, GraphEntity agent, GraphEntity role,
			GraphEntity task, HashSet agentApps, Vector<GraphEntity> roles) throws NullEntity, NotFound, NotInitialised {


		Repeat atask = new Repeat("agentTasks");
		r.add(new Var("roleconvtask",Utils.replaceBadChars(role.getID())));
		insertAscedantsOfRoleWithAscendantsRolesRepeat(role, atask);
		r.add(new Var("directtask","false"));

		r.add(atask);
		generateTaskCode(task, atask);

		verifyAgentTasks(agent, task, agentApps, roles);

	}

	private void verifyAgentTasks(GraphEntity agent, GraphEntity task, HashSet agentApps, Vector<GraphEntity> roles) {
		try {
			Vector<GraphEntity> usedApps = Utils.getRelatedElementsVector(task,
					"WFUses", "WFUsestarget");

			for (GraphEntity app : usedApps) {
				if (!agentApps.contains(app)) {
					Log.getInstance().logERROR("The task \"" + task.getID() + "\" assigned to agent \"" +
							agent.getID() + "\" uses application " + app.getID() +
							" but the agent but the agent does not own this application." +
							" Please, associate the agent to the application with an applicationbelongsto relationship", "", app.getID());
					this.fatalError();
				}
			}

			Vector<GraphEntity> producedConversations = Utils.getRelatedElementsVector(task,
					"GTCreates", "GTCreatestarget");
			for (GraphEntity conv : producedConversations) {
				if (conv.getType().equals("Conversation")){
					GraphEntity interaction = conv.getAttributeByName("Interaction").getEntityValue();
					Vector<GraphEntity> neededRolesInit = Utils.getRelatedElementsVector(interaction,
							"IInitiates", "IInitiatestarget");
					Vector<GraphEntity> neededRolesTarget = Utils.getRelatedElementsVector(interaction,
							"IColaborates", "IColaboratestarget");
					boolean found = false;
					String roleNames = "";
					for (GraphEntity current : neededRolesInit) {
						found = found || roles.contains(current);
						roleNames = roleNames + current.getID() + ", ";
					}
					for (GraphEntity current : neededRolesTarget) {
						found = found || roles.contains(current);
						roleNames = roleNames + current.getID() + ", ";
					}
					if (!found) {
						Log.getInstance().logERROR("The task \"" + task.getID() + "\" assigned to agent \"" +
								agent.getID() + "\" produces a conversation containing interaction \"" + interaction.getID() + "\" " +
								" but the agent does not play any of the required roles. Associate to the agent one of the " +
								" following roles with wfplays relationship: " + roleNames, "", task.getID());
						this.fatalError();
					}
				}
			}

			Vector<GraphEntity> deletedInput = this.getConsumedInputs(task);
			Vector<GraphEntity> eoutput = this.getExpectedOutput(task);
			Vector<GraphEntity> eoutputc = this.getExpectedOutputCreated(task);
			for (GraphEntity ent:deletedInput){
				if (eoutput.contains(ent) || eoutputc.contains(ent)){
					Log.getInstance().logERROR("The task \"" + task.getID() + "\" assigned to agent \"" +
							agent.getID() + "\" consumes information \""+ent.getID()+"\" and it produces an" +
							" information of the same kind. Review the task definition and ensure no consumed input " +
							" matches the type of a produced output", "", task.getID());
					this.fatalError();
				}
			}



		} catch (NullEntity e) {
			e.printStackTrace();
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * It produces the information required to produce a task. It locates the code supposed to be inserted
	 * in the task from a INGENIASCode entity. It also has to identify which inputs it requires and outputs.
	 * Inputs are consumed inputs and elements related with "GTModifies". Outputs are elements associated with
	 * "WFProduces". A task may produce an interaction. If this is the case, information about the kind of interaction
	 * is added as well. The task may be executed in the context of a conversation, i.e., may appear associated
	 * to a interaction unit. In that case, it will add information about the id of the interaction. Tasks
	 * are supposed to obtain their information in runtime either from the information exchanged in the interaction
	 * or from the mental state itself. External resources needed by the application will be application entities
	 * linked with the relationship "WFUses". Only these will be accesible from the task.
	 *
	 *
	 * @param task The task being studied
	 * @param taskRepeat The data structure
	 * @throws NullEntity
	 * @throws NotFound
	 * @throws NotInitialised
	 */
	private void generateTaskCode(GraphEntity task, Repeat taskRepeat) throws NullEntity, NotFound, NotInitialised {
		taskRepeat.add(new Var("tname", Utils.replaceBadChars(task.getID())));
		generateOutputAlternatives(task, taskRepeat);
		GraphEntity codeComponent = getCode(task);
		Vector<GraphEntity> roles = Utils.getRelatedElementsVector(task,
				"WFResponsable", "WFResponsablesource");

		Vector<GraphEntity> satisfiedGoals = Utils.getRelatedElementsVector(task,
				"GTSatisfies", "GTSatisfiestarget");
		if (roles.size() == 0) {
			this.fatalError();
			Log.getInstance().logERROR("Task " + task.getID() + " is not associated to any role or agent. Please " +
					"link this task with a WFResponsable relationship", "", task.getID());
		} else {
			if (satisfiedGoals.size() == 0) {
				this.fatalError();
				Log.getInstance().logERROR("Task " + task.getID() + " is not associated to any goal. Please " +
						"link this task with a goal with a GTSatisfies relationship", "", task.getID());
			} else {


				if (codeComponent == null) {
					Log.getInstance().logWARNING("Task " + task.getID() + " has no associated code components", "", task.getID());
				} else {
					//if (doIGenerateComponentAndTaskCode()) {
					String actualCode = codeComponent.getAttributeByName("Code").getSimpleValue();
					if (actualCode.length() == 0) {
						actualCode = "//REPLACE THIS COMMENT WITH YOUR CODE";
					}
					taskRepeat.add(new Var("code", actualCode, codeComponent.getID(), "Code"));
					taskRepeat.add(new Var("codeid", codeComponent.getID()));
					//	}
				}

				Set<GraphRole> inputs = new HashSet(this.getNonConsumableInput(task)); // To remove repeated items
				Vector<GraphEntity> cancelledConversations = Utils.getRelatedElementsVector(task,
						"WFCancels",
				"WFCancelstarget");
				Vector<GraphRole> consumedInput = this.getInputsToRemoveAfterRead(task);
				for (GraphEntity conv : cancelledConversations) {
					Repeat cancelledConv = new Repeat("cancelconversation");
					GraphEntity interaction = conv.getAttributeByName("Interaction").getEntityValue();
					Var conversation = new Var("interid", Utils.replaceBadChars(interaction.getID()));
					cancelledConv.add(conversation);
					taskRepeat.add(cancelledConv);
				}

				if (inputs.size() == 0 && consumedInput.size() == 0) {
					Log.getInstance().logERROR("Task " + task.getID() + " has no inputs. This is not allowed, please connect this entity with others with WFConsumes or WFModifies", "", task.getID());
					this.fatalError();
				} else {


					//					inputs.removeAll(consumedInput);

					Iterator<GraphRole> iterator = inputs.iterator();
					if (!this.getError()) {
						while (iterator.hasNext()) {
							Repeat expectedInput = new Repeat("expectedInput");
							GraphRole role = iterator.next();
							GraphEntity fact = role.getPlayer();
							if (fact.getType().equalsIgnoreCase("conversation")) {
								expectedInput.add(new Var("mentalentname", Utils.replaceBadChars(fact.getAttributeByName("Interaction").getEntityValue().getID())));
								expectedInput.add(new Var("mentalenttype", "RuntimeConversation"));
							} else {
								expectedInput.add(new Var("mentalentname", Utils.replaceBadChars(fact.getID())));

								expectedInput.add(new Var("mentalenttype", Utils.replaceBadChars(fact.getID())));
							}

							String cardinality = "1";
							String cardString = role.getAttributeByName("Cardinality").getSimpleValue();

							if (cardString.equals("1..*") || cardString.equals("n") || cardString.equals("*")) {
								cardinality = "n";
							}else{
								if ( cardString.equals("0..*") ) {
									cardinality="0..n";
								}
							}



							expectedInput.add(new Var("mentalentitycardinality", cardinality));
							//this.generateFactSlots(expectedInput, fact);
							taskRepeat.add(expectedInput);
						}
					}

					iterator = new HashSet(consumedInput).iterator(); // To remove repeated items
					if (!this.getError()) {
						while (iterator.hasNext()) {
							Repeat expectedInput = new Repeat("consumedInput");
							GraphRole role = iterator.next();
							GraphEntity fact = role.getPlayer();

							expectedInput.add(new Var("mentalentname", Utils.replaceBadChars(fact.getID())));
							expectedInput.add(new Var("mentalenttype", Utils.replaceBadChars(fact.getID())));

							String cardinality = "1";
							String cardString = role.getAttributeByName("Cardinality").getSimpleValue();
							//							System.err.println("Cardinality "+cardString+ " "+fact.getID());
							if (cardString.equals("1..*") || cardString.equals("n") || cardString.equals("*")) {
								cardinality = "n";
							}else{
								if ( cardString.equals("0..*") ) {
									cardinality="0..n";
								}
							}
							expectedInput.add(new Var("mentalentitycardinality", cardinality));
							this.generateFactSlots(expectedInput, fact);
							taskRepeat.add(expectedInput);
						}
					}



					if (!this.getError()) {
						for (GraphRole role : consumedInput) {

							GraphEntity fact = role.getPlayer();
							String cardinality = "1";
							String cardString = role.getAttributeByName("Cardinality").getSimpleValue();
							//							System.err.println("Cardinality "+cardString+ " "+fact.getID());
							Repeat expectedInput = null;

							if (cardString.equals("1..*") || cardString.equals("n") || cardString.equals("0..*") || cardString.equals("*")) {
								cardinality = "n";
								expectedInput = new Repeat("taskinputcardn");
							} else {
								if ( cardString.equals("0..*") ) {
									cardinality="0..n";
									expectedInput = new Repeat("taskinputcard0n");
								} else
									expectedInput = new Repeat("taskinputcard1");
							}


							expectedInput.add(new Var("mentalentname", Utils.replaceBadChars(fact.getID())));
							if (!fact.getType().equalsIgnoreCase("conversation")) {
								expectedInput.add(new Var("mentalenttype", Utils.replaceBadChars(fact.getID())));
							} else {
								expectedInput.add(new Var("mentalenttype", "RuntimeConversation"));
							}

							expectedInput.add(new Var("mentalentitycardinality", cardinality));
							taskRepeat.add(expectedInput);
						}
						Vector<GraphRole> inputsNC = this.getNonConsumableInput(task);
						for (GraphRole role : inputsNC) {


							GraphEntity fact = role.getPlayer();
							String cardinality = "1";
							String cardString = role.getAttributeByName("Cardinality").getSimpleValue();
							//							System.err.println("Cardinality "+cardString+ " "+fact.getID());
							Repeat expectedInput = null;

							if (cardString.equals("1..*") || cardString.equals("n") || cardString.equals("0..*") || cardString.equals("*")) {
								cardinality = "n";
								expectedInput = new Repeat("taskinputcardn");
							} else {
								if ( cardString.equals("0..*") ) {
									cardinality="0..n";
									expectedInput = new Repeat("taskinputcard0n");
								} else
									expectedInput = new Repeat("taskinputcard1");
							}

							if (fact.getType().equalsIgnoreCase("conversation")) {
								expectedInput.add(new Var("mentalentname", Utils.replaceBadChars(fact.getAttributeByName("Interaction").getEntityValue().getID())));
								expectedInput.add(new Var("mentalenttype", "RuntimeConversation"));
							} else {
								expectedInput.add(new Var("mentalentname", Utils.replaceBadChars(fact.getID())));

								expectedInput.add(new Var("mentalenttype", Utils.replaceBadChars(fact.getID())));
							}

							expectedInput.add(new Var("mentalentitycardinality", cardinality));
							taskRepeat.add(expectedInput);
						}
					}



					Vector outputs = this.getExpectedOutputCreated(task);
					iterator = new HashSet(outputs).iterator(); // To remove repeated items
					if (!this.getError()) {
						while (iterator.hasNext()) {
							GraphEntity fact = (GraphEntity) iterator.next();
							if (fact.getType().equalsIgnoreCase("Conversation")){
								Repeat interactions=new Repeat("interactions");
								try {
									GraphEntity interaction=fact.getAttributeByName("Interaction").getEntityValue();
									Var conversation=new Var("interid",Utils.replaceBadChars(interaction.getID()));
									interactions.add(conversation);
									taskRepeat.add(interactions);
									generateColaborators(interaction, interactions);
								} catch (NullEntity ne){
									Log.getInstance().logERROR("Conversation entity has not defined the \"Interaction\" attribute. Please, edit the conversation and choose an interaction","",fact.getID());
									this.fatalError();
								}
							}
							/*if (fact.getType().equalsIgnoreCase("FrameFact")){
								Repeat expectedOutput = new Repeat("expectedOutput");
								expectedOutput.add(new Var("factName", Utils.replaceBadChars(fact.getID())));
								taskRepeat.add(expectedOutput);
								this.generateFactSlots(expectedOutput,fact);
							}*/
						}
					}

					Vector expectedApps = this.getExpectedApps(task);
					iterator = new HashSet(expectedApps).iterator();
					if (!this.getError()) {
						while (iterator.hasNext()) {
							Repeat expectedApp = new Repeat("expectedApplication");
							GraphEntity app = (GraphEntity) iterator.next();
							expectedApp.add(new Var("appName", Utils.replaceBadChars(app.getID())));
							taskRepeat.add(expectedApp);
						}
					}

					Vector<GraphEntity> context = locateInteraction(task);
					if (context != null) {
						for (GraphEntity intcontext : context) {
							Repeat interactioncontext = new Repeat("interactioncontext");
							// It is enought to add the repeat to enable the conversation context
							Var conversation = new Var("interid", Utils.replaceBadChars(intcontext.getID()));
							interactioncontext.add(conversation);
							taskRepeat.add(interactioncontext);
							//System.err.println("generating conext "+intcontext.getID()+"  for task "+task.getID()+" \n"+r.toString());
						}
					}
				}
			}

		}

	}

	private void generateOutputAlternatives(GraphEntity task, Repeat taskRepeat) {

		Repeat expectedoutputalternatives = new Repeat("expectedoutputalternatives");
		taskRepeat.add(expectedoutputalternatives);
		taskRepeat.add(new Var("alternativeid", "default"));
		Vector outputs;
		try {
			outputs = this.getExpectedOutput(task);
			Iterator iterator = new HashSet(outputs).iterator(); // To remove repeated items
			if (!this.getError()) {
				while (iterator.hasNext()) {
					GraphEntity fact = (GraphEntity) iterator.next();
					if (fact.getType().equalsIgnoreCase("FrameFact") || fact.getType().equalsIgnoreCase("GeneralEvent") ||
							fact.getType().equalsIgnoreCase("ApplicationEventSlots")) {
						Repeat expectedOutput = new Repeat("expectedOutputWF");
						expectedOutput.add(new Var("outputtype", Utils.replaceBadChars(fact.getID())));
						expectedOutput.add(new Var("outputid", Utils.replaceBadChars(fact.getID())));
						expectedoutputalternatives.add(expectedOutput);
					} else {
						Log.getInstance().logERROR("Task "+task.getID()+" cannot wfproduce anything different from a framefact, generalevent or applicationeventslots", "", task.getID());
						this.fatalError();
					}
				}
			}
			outputs = getExpectedOutputCreated(task);
			iterator = new HashSet(outputs).iterator(); // To remove repeated items
			if (!this.getError()) {
				while (iterator.hasNext()) {
					GraphEntity fact = (GraphEntity) iterator.next();
					if (fact.getType().equalsIgnoreCase("Conversation")) {
						Repeat interactions = new Repeat("interactions");
						Repeat expectedOutput = new Repeat("expectedOutputMS");
						try {
							expectedOutput.add(new Var("outputtype", "RuntimeConversation"));
							expectedoutputalternatives.add(expectedOutput);
							GraphEntity interaction = fact.getAttributeByName("Interaction").getEntityValue();
							expectedOutput.add(new Var("outputid", Utils.replaceBadChars(interaction.getID())));
							Var conversation = new Var("interid", Utils.replaceBadChars(interaction.getID()));
							interactions.add(conversation);
							expectedOutput.add(conversation);
							generateColaborators(interaction, interactions);
						} catch (NullEntity ne) {
							Log.getInstance().logERROR("Conversation entity has not defined the \"Interaction\" attribute. Please, edit the conversation and choose an interaction", "", fact.getID());
							this.fatalError();
						} catch (NotFound e) {
							e.printStackTrace();
						}
					}
					if (fact.getType().equalsIgnoreCase("FrameFact") || fact.getType().equalsIgnoreCase("GeneralEvent") ||
							fact.getType().equalsIgnoreCase("ApplicationEventSlots")) {
						Repeat expectedOutput = new Repeat("expectedOutputMS");
						expectedOutput.add(new Var("outputtype", Utils.replaceBadChars(fact.getID())));
						expectedOutput.add(new Var("outputid", Utils.replaceBadChars(fact.getID())));
						expectedoutputalternatives.add(expectedOutput);
					}
				}
			}


		} catch (NullEntity e) {

			e.printStackTrace();
		}





	}

	/**
	 * It determines which interaction is affected by the execution of this task. This is known by
	 * inspecting relationships UIInitiates and UIColaborates. The name of the interaction will be
	 * the id of the interaction entity appearing in an interaction diagram and whose associated protocol
	 * specification, of type GRASIASpecification, contains the name of the interaction diagram where
	 * the interaction units linked to the task exist.
	 * @param task The task being studied
	 * @return An GraphEntity representing the interaction
	 * @throws NotInitialised
	 */
	private Vector<GraphEntity> locateInteraction(GraphEntity task) throws NotInitialised {
		Vector<GraphEntity> result = new Vector<GraphEntity>();
		Vector<GraphEntity> iunits;

		try {
			iunits = Utils.getRelatedElementsVector(task, "UIInitiates", "UIInitiatessource");
			iunits.addAll(Utils.getRelatedElementsVector(task, "UIColaborates", "UIColaboratessource"));
			if (iunits.size() > 0) {
				GraphEntity[] specs = Utils.generateEntitiesOfType("GRASIASpecification",getBrowser());
				for (int j = 0; j < iunits.size(); j++) {
					for (int k = 0; k < specs.length; k++) {
						GraphEntity model;
						try {
							model = specs[k].getAttributeByName("ModelThatContainsSpecification").getEntityValue();
							GraphEntity[] interaction = Utils.getRelatedElements(specs[k], "IHasSpec", "IHasSpecsource");
							if (interaction.length == 0) {
								Log.getInstance().logERROR("GRASIASpecification " +
										specs[k].getID() + " is not linked to any interaction", "", specs[k].getID());
								this.fatalError();
							} else {
								if (model != null) {

									String interactionID = model.getAttributeByName("ModelID").getSimpleValue();
									//System.err.println("looking for "+interactionID+" "+model.getType());
									Graph g = this.getBrowser().getGraph(interactionID);
									if (g == null) {
										Log.getInstance().logERROR("GRASIASpecification " + specs[k].getID() +
												" referred to a non existent protocol diagram " + interactionID, "", specs[k].getID());
										this.fatalError();
									} else {
										GraphEntity[] candidates = g.getEntities();
										for (int l = 0; l < candidates.length; l++) {
											if (candidates[l].getID().equals(iunits.elementAt(j).getID())) {
												result.add(interaction[0]);
											}
										}
									}
								}

							}
						} catch (NotFound e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NullEntity e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		} catch (NullEntity e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.err.println("Tarea "+task.getID()+"asociada a la interacción:"+result);
		return result;
	}

	/**
	 * It produces the code for an application shut down or start up. It distinguishes between the singleton pattern
	 * for handling the application or just conventional individual instances management. This information is stored
	 * into "system init" and "system shutdown" packages where there are component diagrams with
	 * applications appearing linked to INGENIASCode elements. These code elements will provide the code for initializing
	 * the application objects. With respect singleton or individual instances, this is
	 * related with how these applications are initialized: only once in the JVM or several times.
	 *
	 * @param p The data structure
	 * @param app The application being studied
	 * @param components The components identified in the system
	 * @throws NullEntity
	 * @throws NotFound
	 */
	private void generateApp(Sequences p, GraphEntity app, Hashtable components) throws NullEntity, NotFound {
		//System.err.println("           generating "+app);
		Repeat appsr = new Repeat("applications");
		p.addRepeat(appsr);
		//if (doIGenerateComponentAndTaskCode()) {
		appsr.add(new Var("initcode", getCode("system init", app)));
		appsr.add(new Var("shutdowncode", getCode("system shutdown", app)));
		//}

		if (isSingleton(app, components)) {
			Repeat internal = new Repeat("initialApplicationsSingleton");
			appsr.add(internal);
		} else {
			Repeat internal = new Repeat("initialApplicationsIndividual");
			appsr.add(internal);
		}		


		appsr.add(new Var("aname", Utils.replaceBadChars(app.getID())));
		GraphCollection methods = app.getAttributeByName("methods").getCollectionValue();
		for (int k = 0; k < methods.size(); k++) {
			GraphEntity method = methods.getElementAt(k);
			Repeat mr = new Repeat("methods");
			appsr.add(mr);
			mr.add(new Var("mname", Utils.replaceBadChars(method.getAttributeByName("Name").getSimpleValue())));
			mr.add(new Var("returntype", Utils.replaceBadChars(method.getAttributeByName("Result").getSimpleValue())));
			if (!method.getAttributeByName("Result").getSimpleValue().equals("void")) {
				mr.add(new Var("returnstatement", "return null;"));
			}
		}
		completeFileNameAssociation(app, components);
	}

	private void completeFileNameAssociation(GraphEntity appr,
			Hashtable components) throws NotFound, NullEntity {

		GraphEntity component = (GraphEntity) components.get(appr.getID());
		GraphAttribute files;

		if (component == null) {
			Log.getInstance().logWARNING("Entity " + appr.getID() +
					" was not associated to any compoment. I cannot guess " +
					"if the application should appear as a singletong pattern." +
					" I will go on with the singleton option by default", "", appr.getID());

		} else {
			files = component.getAttributeByName("Files");
			GraphCollection gc;
			try {
				gc = files.getCollectionValue();
				boolean itis = false;
				int k = 0;
				while (!itis && k < gc.size()) {
					GraphEntity ge = null;
					ge = gc.getElementAt(k);
					if (ge.getAttributeByName("Entity").getEntityValue().equals(appr)) {
						if (ge.getAttributeByName("File").getSimpleValue() == null ||
								ge.getAttributeByName("File").getSimpleValue().equals("")) {
							//							try {
							//							// this.getProperty("jadeproject").value+"/"+
							//							String path = getProperty("jadeperm").value + "/ingenias/jade/components/" + appr.getID() + "App.java";
							//							ge.setAttribute(new GraphAttributeImp("File", path, null));
							//							System.err.println("Fijado atributo .............." + appr.getID() + " a path " + path + " " + this.getProperties());
							//							} catch (InvalidAttribute e) {
							//							// TODO Auto-generated catch block
							//							e.printStackTrace();
							//							}
						}
					}
					k++;

				}

			} catch (NullEntity e) {
				Log.getInstance().logERROR("Entity " + component.getID() +
						" do not have any collection of Application-File associated", "", component.getID());
				this.fatalError();
				throw e;
			}





		}
	}

	/**
	 * It detects the perception relationships (EPerceives,EPerceivesNotification,EPerceivesPolling)
	 * created between an application and an agent. For each perception, it obtains the event supposed
	 * to be created when some information is available through that perception. Each event is processed
	 * as ApplicationEventSlots, i.e., it can have slots with additional information.
	 * slots
	 *
	 * @param app The application being studied
	 * @param agent The agent being studied
	 * @param appsr The data structure to be filled in
	 */
	private void getAppPerceptionRels(GraphEntity app, GraphEntity agent, Repeat appsr) {
		try {
			GraphRelationship[] componentsA = Utils.getRelatedElementsRels(app,
					"EPerceives", "EPerceivestarget");
			GraphRelationship[] componentsN = Utils.getRelatedElementsRels(app,
					"EPerceivesNotification", "EPerceivestarget");
			GraphRelationship[] componentsP = Utils.getRelatedElementsRels(app,
					"EPerceivesPolling", "EPerceivestarget");
			GraphRelationship[] components = new GraphRelationship[componentsA.length +
			                                                       componentsN.length +
			                                                       componentsP.length];
			System.arraycopy(componentsA, 0, components, 0, componentsA.length);
			System.arraycopy(componentsN, 0, components, componentsA.length, componentsN.length);
			System.arraycopy(componentsP, 0, components, componentsN.length + componentsA.length, componentsP.length);

			for (int k = 0; k < components.length; k++) {
				GraphRelationship gr = components[k];
				boolean valid = false;
				GraphRole[] groles = gr.getRoles();
				for (int i = 0; i < groles.length && !valid; i++) {
					valid = groles[i].getPlayer().getID().equals(agent.getID());
				}
				if (valid) {
					if (gr.getAttributeByName("Event") == null) {
						Log.getInstance().logERROR("Perception relationship does not define the listened event", gr.getGraph().getName(), gr.getID());
						this.fatalError();
					} else {
						try {

							GraphEntity event = gr.getAttributeByName("Event").getEntityValue();
							Repeat eventr = new Repeat("appevent");
							appsr.add(eventr);
							eventr.add(new Var("eventid", Utils.replaceBadChars(event.getID())));
							eventr.add(new Var("eventtype", Utils.replaceBadChars(event.getType())));
							this.generateFactSlots(eventr, event);
						} catch (NullEntity e) {
							Log.getInstance().logERROR("Perception relationship does not define the listened event", gr.getGraph().getName(), gr.getID());
							this.fatalError();

						}
					}
				}

			}
		} catch (NullEntity e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	/**
	 * It detects all defined applications (of type Application, InternalApplication, and EnvironmentApplication).
	 * For each one, it proceeds to extract their data from the specification
	 *
	 * @param p The data structuree
	 * @throws NullEntity
	 * @throws NotFound
	 * @throws NotInitialised
	 */
	private void generateAppsXML(Sequences p) throws NullEntity, NotFound, NotInitialised {
		GraphEntity[] apps = Utils.generateEntitiesOfType("Application",getBrowser());
		Hashtable appcomponents = this.getComponents();
		for (int k = 0; k < apps.length; k++) {
			this.generateApp(p, apps[k], appcomponents);
		}
		apps = Utils.generateEntitiesOfType("InternalApplication",getBrowser());
		for (int k = 0; k < apps.length; k++) {
			this.generateApp(p, apps[k], appcomponents);
		}
		apps = Utils.generateEntitiesOfType("EnvironmentApplication",getBrowser());
		for (int k = 0; k < apps.length; k++) {
			this.generateApp(p, apps[k], appcomponents);			
		}
	}

	/**
	 * It obtains the code associated to a task. Code is inserted inside of a INGENIASCodeComponent entity.
	 * These entities are linked with other specification elements by means of "CDUsesCode" relationships.
	 *
	 * @param task The task being studied
	 * @return A string contiaining the code inserted inside the INGENIASCodeComponent
	 * @throws NullEntity
	 * @throws NotFound
	 */
	private GraphEntity getCode(GraphEntity task) throws NullEntity, NotFound {
		// TODO Auto-generated method stub
		Vector components = Utils.getRelatedElementsVector(task,
				"CDUsesCode", "CDUsesCodetarget");
		if (components.size() > 1) {
			String componentsname = "";
			for (int k = 0; k < components.size(); k++) {
				componentsname = componentsname + components.elementAt(k).toString();
			}
			Log.getInstance().logERROR("Task " + task.getID() +
					" is associated to more than one code component: " + componentsname, "", task.getID());
			this.fatalError();
		} else if (components.size() == 0) {
			Log.getInstance().logWARNING("Task " + task.getID() +
					" is not associated to any code code component ", "", task.getID());
		} else {

			Enumeration enumeration = components.elements();
			GraphEntity component = (GraphEntity) components.firstElement();
			return component;
		}
		return null;
	}

	/**
	 * It works like getCode(GraphEntity task), but restricting the code components to those contained
	 * into diagrams within the path "pathname".
	 *
	 * @param pathname The pathname substring that has to exist in the path to the diagram
	 * @param element The element being studied
	 * @return A string with the code
	 *
	 * @throws NullEntity
	 * @throws NotFound
	 */
	private String getCode(String pathname, GraphEntity element) throws NullEntity, NotFound {
		String result = "";
		// TODO Auto-generated method stub
		Vector components = Utils.getRelatedElementsVector(pathname, element,
				"CDUsesCode", "CDUsesCodetarget");
		if (components.size() > 1) {
			System.err.println("-----------Assigning code to "+element.getID());
			String componentsname = "";
			for (int k = 0; k < components.size(); k++) {
				componentsname = componentsname + components.elementAt(k).toString();
			}
			Log.getInstance().logERROR("Entity " + element.getID() + " of type " +
					element.getType() + " is associated to more than one code component: " + componentsname, "", element.getID());
			this.fatalError();
		} else if (components.size() == 0) {
			Log.getInstance().logWARNING("Task " + element.getID() +
					" is not associated to any code code component", "", element.getID());
		} else {

			Enumeration enumeration = components.elements();
			GraphEntity component = (GraphEntity) components.firstElement();
			return component.getAttributeByName("Code").getSimpleValue();
		}
		return "";
	}

	private Vector getExpectedApps(GraphEntity task) throws NullEntity {
		Vector inputs = Utils.getRelatedElementsVector(task,
				"WFUses",
		"WFUsestarget");
		Enumeration enumeration = inputs.elements();
		Vector apps = new Vector();
		boolean validType = true;
		while (enumeration.hasMoreElements()) {
			GraphEntity gr = (GraphEntity) enumeration.nextElement();
			validType = (gr.getType().equals("Application") ||
					gr.getType().equals("EnvironmentApplication") ||
					gr.getType().equals("InternalApplication"));
			if (validType) {
				apps.add(gr);
				//System.err.println(gr.getID());
			}
		}

		return new Vector(new HashSet(apps));
	}

	/**
	 * It extracts the data for all tasks associated to an agent "Agent". Available applications
	 * for these tasks are contained in "apps"
	 *
	 * @param r The data structure where data has to be inserted
	 * @param agent The agent being studied
	 * @param apps The applications that can be used by the agent tasks
	 * @param roles 
	 * @throws NullEntity
	 * @throws NotFound
	 * @throws NotInitialised
	 */
	private void generateAgentTasks(Repeat r, GraphEntity agent, HashSet apps, Vector roles) throws NullEntity, NotFound, NotInitialised {
		Vector tasks = this.getAgentTasks(agent);
		if (tasks.size() == 0) {
			Log.getInstance().logWARNING("Agent " + agent.getID() +
			" has no any associated task");
		} else {
			Enumeration enumeration = tasks.elements();
			HashSet<GraphEntity> produced = new HashSet<GraphEntity>();
			HashSet<GraphEntity> consumed = new HashSet<GraphEntity>();
			while (enumeration.hasMoreElements()) {
				GraphEntity task = (GraphEntity) enumeration.nextElement();
				this.generateAgentTask(r, agent, task, apps, roles);
				produced.addAll(new HashSet(getExpectedOutput(task)));
				produced.addAll(new HashSet(getExpectedOutputCreated(task)));
				consumed.addAll(new HashSet(this.getCompleteExpectedInput(task)));
				System.err.println(task.getID()+" "+this.getCompleteExpectedInput(task));
				//consumed.addAll(this.getConsumedInputs(task));
			}
			produced.removeAll(consumed);
			HashSet<GraphEntity> removeConversations = new HashSet<GraphEntity>();
			for (GraphEntity conv : produced) {
				if (conv.getType().equals("Conversation")) {
					removeConversations.add(conv);
				}
			}
			produced.removeAll(removeConversations);
			if (produced.size() != 0) {
				Log.getInstance().logWARNING("Agent " + agent.getID() + " does not use as input of any task the following entities :" + produced +
				". This may end in a generation of garbage. To prevent this, a default task for the deletion of these entities has been produced.");
				for (GraphEntity conv : produced) {
					Repeat defaultDeleteTask = new Repeat("deleteentity");
					defaultDeleteTask.add(new Var("entityidtodelete", Utils.replaceBadChars(conv.getID())));
					defaultDeleteTask.add(new Var("entitytypetodelete", Utils.replaceBadChars(conv.getType())));
					r.add(defaultDeleteTask);
				}

			} else		{
				Repeat defaultDeleteTask = new Repeat("deleteentity");
				defaultDeleteTask.add(new Var("entityidtodelete", "NONSENSEENTITY"));
				defaultDeleteTask.add(new Var("entitytypetodelete", "NONSENSEENTITY"));
				r.add(defaultDeleteTask);
			}

		}
	}

	/**
	 * It obtains all outputs to be produced by a task. These are entities connected with a task with
	 * a WFProduces relationship. Two outputs are recognised so far: entities of type FrameFact and
	 * Conversation
	 *
	 * @param task The task being studied
	 * @return a list of produced entities
	 * @throws NullEntity
	 */
	Vector getExpectedOutput(GraphEntity task) throws NullEntity {
		Hashtable inputs = Utils.getRelatedElementsHashtable(task,
				"WFProduces",
		"WFProducestarget");

		Enumeration enumeration = inputs.keys();
		boolean allfacts = true;
		while (enumeration.hasMoreElements() && allfacts) {
			GraphEntity gr = (GraphEntity) enumeration.nextElement();
			allfacts = allfacts && (gr.getType().equals("FrameFact") ||
					gr.getType().equals("Conversation"));
			if (!allfacts) {
				Log.getInstance().logERROR("Please, review task " + task.getID() +
						". It produces non-framefacts and non-conversation elements." +
						" A task should produce only FrameFacts and conversations" +
						"  in this module", ((GraphRelationship) inputs.get(gr)).getGraph().getName(), ((GraphRelationship) inputs.get(gr)).getID());
				this.fatalError();
			}

		}

		return new Vector(inputs.keySet());

	}

	Vector getExpectedOutputCreated(GraphEntity task) throws NullEntity {
		Hashtable inputs = Utils.getRelatedElementsHashtable(task,
				"GTCreates",
		"GTCreatestarget");

		Enumeration enumeration = inputs.keys();
		boolean allfacts = true;
		while (enumeration.hasMoreElements() && allfacts) {
			GraphEntity gr = (GraphEntity) enumeration.nextElement();
			allfacts = allfacts && (gr.getType().equals("FrameFact") ||
					gr.getType().equals("Conversation"));
			if (!allfacts) {
				Log.getInstance().logERROR("Please, review task " + task.getID() +
						". It produces non-framefacts and non-conversation elements." +
						" A task should produce only FrameFacts and conversations" +
						"  in this module", ((GraphRelationship) inputs.get(gr)).getGraph().getName(), ((GraphRelationship) inputs.get(gr)).getID());
				this.fatalError();
			}

		}

		return new Vector(inputs.keySet());

	}

	/**
	 * It obtains the list of elements consumed by the task. Consumed items are those connected with
	 * the task by means of WFConsumes relationships.
	 *
	 * @param task The task being studied
	 * @return a list of consumed entities
	 * @throws NullEntity
	 */
	Vector getConsumedInputsInWorkflow(GraphEntity task) throws NullEntity {
		Vector inputs = Utils.getRelatedElementsVector(task,
				"WFConsumes",
		"WFConsumestarget");
		Enumeration enumeration = inputs.elements();
		boolean allfacts = true;
		/*while (enumeration.hasMoreElements() && allfacts) {
                 GraphEntity gr = (GraphEntity) enumeration.nextElement();
                 allfacts = allfacts & gr.getType().equals("FrameFact");
                 }
                 if (!allfacts) {
                 Log.getInstance().logERROR("Please, review task " + task.getID() +
                 ". It consumes non-framefacts elements." +
                 " A task should consume only FrameFacts" +
                 "  in this module");
                 this.fatalError();
                 }*/
		return new Vector(new HashSet(inputs));

	}

	Vector<GraphRole> getInputsToRemoveAfterRead(GraphEntity task) throws NullEntity {
		Vector<GraphRole> inputs = Utils.getRelatedElementsRolesVector(task,
				"WFConsumes",
		"WFConsumestarget");
		Vector<GraphRole> inputs1 = Utils.getRelatedElementsRolesVector(task,
				"Consumes",
		"WFConsumestarget");

		HashSet<GraphRole> result = new HashSet<GraphRole>(inputs);
		result.addAll(inputs1);

		/*while (enumeration.hasMoreElements() && allfacts) {
                 GraphEntity gr = (GraphEntity) enumeration.nextElement();
                 allfacts = allfacts & gr.getType().equals("FrameFact");
                 }
                 if (!allfacts) {
                 Log.getInstance().logERROR("Please, review task " + task.getID() +
                 ". It consumes non-framefacts elements." +
                 " A task should consume only FrameFacts" +
                 "  in this module");
                 this.fatalError();
                 }*/
		return new Vector<GraphRole>(result);

	}

	Vector<GraphEntity> getConsumedInputs(GraphEntity task) throws NullEntity {
		Vector<GraphEntity> inputs = Utils.getRelatedElementsVector(task,
				"WFConsumes",
		"WFConsumestarget");
		Vector<GraphEntity> inputs1 = Utils.getRelatedElementsVector(task,
				"Consumes",
		"WFConsumestarget");

		HashSet<GraphEntity> result = new HashSet<GraphEntity>(inputs);
		result.addAll(inputs1);

		/*while (enumeration.hasMoreElements() && allfacts) {
                 GraphEntity gr = (GraphEntity) enumeration.nextElement();
                 allfacts = allfacts & gr.getType().equals("FrameFact");
                 }
                 if (!allfacts) {
                 Log.getInstance().logERROR("Please, review task " + task.getID() +
                 ". It consumes non-framefacts elements." +
                 " A task should consume only FrameFacts" +
                 "  in this module");
                 this.fatalError();
                 }*/
		return new Vector<GraphEntity>(result);

	}

	/**
	 * It combines consumed elements with those only accessed. Elements just accessed are
	 * those connected with the task by means of the "GTModifies" relationship
	 * @param task The studied task
	 * @return A list of entities that can act as input of the task
	 * @throws NullEntity
	 */
	private Vector<GraphEntity> getCompleteExpectedInput(GraphEntity task) throws NullEntity {
		Vector<GraphEntity> inputs = Utils.getRelatedElementsVector(task,
				"WFConsumes",
		"WFConsumestarget");

		Vector<GraphEntity> inputs2 = Utils.getRelatedElementsVector(task,
				"GTModifies",
		"WFConsumestarget");

		Vector<GraphEntity> inputs3 = Utils.getRelatedElementsVector(task,
				"Consumes",
		"WFConsumestarget");

		inputs.addAll(inputs2);
		inputs.addAll(inputs3);
		return inputs;
	}

	private Vector<GraphRole> getNonConsumableInput(GraphEntity task) throws NullEntity {

		Vector<GraphRole> inputs2 = Utils.getRelatedElementsRolesVector(task,
				"GTModifies",
		"WFConsumestarget");


		return inputs2;
	}

	/**
	 * It obtains those tasks associated to a role "role". These are those connected with
	 * an entity of type role when the association is "WFResponsible".
	 *
	 * @param role The studied role
	 * @return A list of tasks
	 * @throws NullEntity
	 */
	private Vector getRoleTasks(GraphEntity role) throws NullEntity {

		Vector tasks = Utils.getRelatedElementsVector(role,
				"WFResponsable",
		"WFResponsabletarget");

		return tasks;
	}

	/**
	 * It obtains all tasks associated to an agent. Direct associated tasks are
	 * linked with "agent" with "WFResponsable" relationships. Here we add as well,
	 * those linked with played roles by the agent.
	 *
	 * @param agent The studied agent
	 * @return A list of tasks
	 * @throws NullEntity
	 */
	private Vector getAgentTasks(GraphEntity agent) throws NullEntity {
		Vector directTasks = Utils.getRelatedElementsVector(agent,
				"WFResponsable",
		"WFResponsabletarget");
		Vector<GraphEntity> rolesPlayed = Utils.getRelatedElementsVector(agent,
				"WFPlays",
		"WFPlaystarget");
		HashSet tasks = new HashSet(directTasks);
		Vector<GraphEntity> generalizedRoles=new Vector<GraphEntity>();
		for (GraphEntity role:rolesPlayed){
			generalizedRoles.addAll(Utils.getAscendantsOfRole(role));
		}
		generalizedRoles.addAll(rolesPlayed);
		generalizedRoles=new Vector<GraphEntity>(new HashSet<GraphEntity>(generalizedRoles));
		Iterator enumeration = generalizedRoles.iterator();
		while (enumeration.hasNext()) {
			GraphEntity role = (GraphEntity) enumeration.next();
			tasks.addAll(this.getRoleTasks(role));
		}
		return new Vector(tasks);
	}

	private Vector getAgentDirectTasks(GraphEntity agent) throws NullEntity {
		Vector directTasks = Utils.getRelatedElementsVector(agent,
				"WFResponsable",
		"WFResponsabletarget");

		HashSet tasks = new HashSet(directTasks);

		return new Vector(tasks);
	}

	private Hashtable<GraphEntity, Vector<GraphEntity>> getAgentTasksPerRole(GraphEntity agent) throws NullEntity {
		Hashtable<GraphEntity, Vector<GraphEntity>>  taskPerRole=new Hashtable<GraphEntity, Vector<GraphEntity>>(); 
		Vector<GraphEntity> rolesPlayed = Utils.getRelatedElementsVector(agent,
				"WFPlays",
		"WFPlaystarget");
		HashSet<GraphEntity> expandedHierarchy=new HashSet<GraphEntity>();
		for (GraphEntity ge:rolesPlayed){
			expandedHierarchy.addAll(Utils.getAscendantsOfRole(ge));	
		}
		expandedHierarchy.addAll(rolesPlayed);
		HashSet tasks = new HashSet();
		Iterator<GraphEntity> enumeration = expandedHierarchy.iterator();
		while (enumeration.hasNext()) {
			GraphEntity role = (GraphEntity) enumeration.next();			
			taskPerRole.put(role,this.getRoleTasks(role));			
		}
		System.err.println(taskPerRole);
		return taskPerRole;
	}




	/**
	 * It obtains all ascendants of a role through the ARoleInheritance relationship. 
	 * @param ge
	 * @return
	 */
	private Collection<? extends GraphEntity> getDescendantsOfRole(GraphEntity ge) {
		Vector<GraphEntity> allDescendants=new Vector<GraphEntity> ();
		try {
			Vector<GraphEntity> descendants = Utils.getRelatedElementsVector(ge,
					"ARoleInheritance",
			"ARoleInheritancesource");
			while (!descendants.isEmpty()){
				allDescendants.addAll(descendants);
				descendants.clear();
				for (GraphEntity descendant:allDescendants){

					descendants.addAll(Utils.getRelatedElementsVector(descendant,
							"ARoleInheritance",
					"ARoleInheritancesource"));

				}
				descendants.removeAll(allDescendants);
			}
		} catch (NullEntity e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return allDescendants;


	}
	/**
	 * It extracts all the information required to define an agent. It takes into
	 *  account the roles played by the agent to incorporate all the interactions
	 *  where the role appears. For each role, it determines if the agent will act
	 *  as initiator of the interaction or just as collaborator.
	 * @param p
	 * @param fileapps
	 * @param components
	 * @throws Exception
	 */
	private void generateAgentXML(Sequences p, Hashtable fileapps, Hashtable components) throws Exception {
		// Obtain agents to generate
		GraphEntity[] agents = Utils.generateEntitiesOfType("Agent",getBrowser());
		for (int k = 0; k < agents.length; k++) {
			HashSet agentApps = this.getAgentApplications(agents[k]);
			Repeat r1 = new Repeat("agents");

			p.addRepeat(r1);
			r1.add(new Var("agentid", Utils.replaceBadChars(agents[k].getID())));



			this.generateAgentApps(r1, agents[k], agentApps, components);

			// Obtain roles played by the agent
			HashSet<GraphEntity> playedRoles = Utils.getPlayedRoles(agents[k]);
			//Vector vectorPlayedRoles = Utils.getRelatedElementsVector(actors[k], "WFPlays", "WFPlaystarget");
			this.generateAgentTasks(r1, agents[k], agentApps, new Vector(playedRoles));
			generateInitialMentalState(r1, agents[k], agentApps, new Vector(playedRoles));

			if (playedRoles.size() == 0) {
				Log.getInstance().logWARNING("Agent " + agents[k].getID() +
						" does not play" +
				" any role. This agent will not participate in any interaction");
				//					this.fatalError();
			} else {
				for (GraphEntity prole:playedRoles){
					Repeat rroles = new Repeat("roles");
					r1.add(rroles);
					rroles.add(new Var("roleid", Utils.replaceBadChars(prole.getID())));
					insertDescedantsOfRoleWithDescendantRolesRepeat(prole,
							rroles);
					insertAscedantsOfRoleWithAscendantsRolesRepeat(prole,
							rroles);
					rroles.add(new Var("roleid", Utils.replaceBadChars(prole.getID())));
					// Obtain interaction entities associated with this agent
					GraphEntity[] interactions = getRoleInheritedRels(prole,
							"IInitiates", "IInitiatessource");
					if (interactions.length == 0) {
						// The role does not start any interaction. This means it
						//  can be a collaborator
						interactions = getRoleInheritedRels(prole, "IColaborates",
						"IColaboratessource");
						/*this.getRelatedElements(playedRoles[j],
                                                 "IColaborates", "IColaboratessource");*/
						if (interactions.length == 0) {
							// A role must participate at least in one interaction
							Log.getInstance().logWARNING("Role  " + prole.getID() +
									" does not participate in any interaction. No interaction code will be generated for this role." +
							"Please, associate this role with an interaction as colaborator or as initiator");

						} else {
							for (int i = 0; i < interactions.length; i++) {
								Repeat r2 = new Repeat("interactionsColaborated");
								r1.add(r2);
								r2.add(new Var("interactionid", Utils.replaceBadChars(interactions[i].getID())));
								r2.add(new Var("roleid", Utils.replaceBadChars(prole.getID())));
							}
						}
					} else {
						for (int i = 0; i < interactions.length; i++) {
							Repeat r2 = new Repeat("interactions");
							GraphEntity firstIU = ig.getFirstIU(interactions[i]);
							if (!this.getError()) {
								Graph g = this.getInitialMentalStateForInteraction(firstIU);
								if (g != null) {
									this.addInvolvedEntitiesIntoAMentalState(g, r2);
								}
								r1.add(r2);
								r2.add(new Var("interactionid", Utils.replaceBadChars(interactions[i].getID())));
								r2.add(new Var("roleid", Utils.replaceBadChars(prole.getID())));
								// Obtain other roles involved in this interaction
								generateColaborators(interactions[i], r2);
								//									//System.err.println(r2);
							}
						}
						interactions = Utils.getRelatedElements(prole,
								"IColaborates", "IColaboratessource");

						for (int i = 0; i < interactions.length; i++) {
							Repeat r2 = new Repeat("interactionsColaborated");
							r1.add(r2);
							r2.add(new Var("interactionid", Utils.replaceBadChars(interactions[i].getID())));
							r2.add(new Var("roleid", Utils.replaceBadChars(prole.getID())));
						}

					}
				}
			}
		}
	}



	private void insertAscedantsOfRoleWithAscendantsRolesRepeat(
			GraphEntity prole, Repeat rroles) {
		Repeat ascendantRolesRepeat = new Repeat("ascendantroles");
		rroles.add(ascendantRolesRepeat);
		Collection<? extends GraphEntity> ascendantRoles = Utils.getAscendantsOfRole(prole);
		for (GraphEntity descendant:ascendantRoles){
			rroles.add(new Var("ascendant", Utils.replaceBadChars(descendant.getID())));
		} // Descendants are included to account for polimorphism
	}


	private void insertDescedantsOfRoleWithDescendantRolesRepeat(
			GraphEntity prole, Repeat rroles) {
		Repeat ascendantRolesRepeat = new Repeat("descendantroles");
		rroles.add(ascendantRolesRepeat);
		Collection<? extends GraphEntity> descendantRoles = getDescendantsOfRole(prole);
		for (GraphEntity descendant:descendantRoles){
			rroles.add(new Var("descendant", Utils.replaceBadChars(descendant.getID())));
		} // Descendants are included to account for polimorphism
	}

	/**
	 * Given an interaction, it identifies all the collaborators of the interaction. These
	 * are added into the r structure
	 * @param interaction The studied interaction.
	 * @param r The data structure
	 * @throws NullEntity
	 */
	private void generateColaborators(GraphEntity interaction, Repeat r) throws NullEntity {
		GraphRole[] colaboratorRoles = Utils.getRelatedElementsRoles(interaction, "IColaborates", "IColaboratestarget");
		if (colaboratorRoles.length == 0) {
			Log.getInstance().logERROR("Interaction " +
					interaction.getID() +
					" has no colaborators", "", interaction.getID());
			this.fatalError();
		}
		for (int l = 0; l < colaboratorRoles.length; l++) {
			Repeat r3 = new Repeat("interactionroles");
			r.add(r3);
			r3.add(new Var("irolename", Utils.replaceBadChars(colaboratorRoles[l].getPlayer().getID())));
			try {
				r3.add(new Var("irolenamecardinality", Utils.replaceBadChars(colaboratorRoles[l].getAttributeByName("Cardinality").getSimpleValue())));
			} catch (NotFound e) {
				// TODO Auto-generated catch block
				r3.add(new Var("irolenamecardinality", "1"));
			}
		}
	}

	/**
	 * Given an agent, it locates all the applications belonging to it. These are those linked
	 * with the ApplicationBelongsTo relationship
	 *
	 * @param entity The agent being studied
	 * @return A list of applications belonging to the agent
	 * @throws NullEntity
	 */
	private HashSet getAgentApplications(GraphEntity entity) throws NullEntity {
		HashSet result = new HashSet();
		//System.err.println("---------------------rels"+entity+":"+entity.getAllRelationships());
		GraphEntity[] agentapps = Utils.getRelatedElements(entity,
				"ApplicationBelongsTo", "ApplicationBelongsTotarget");
		for (int k = 0; k < agentapps.length; k++) {
			//System.err.println(agentapps[k]);
			result.add(agentapps[k]);
		}
		//System.err.println(entity+":"+result);
		return result;
	}

	/**
	 * It extracts all the information required for the applications embedded into an agent.
	 *
	 * @param r The data structure
	 * @param agent The agent being studied
	 * @param agentApps The applications identified for this agent
	 * @param components The components identified at the beginning
	 * @throws NotFound
	 * @throws NullEntity
	 */
	private void generateAgentApps(Repeat r, GraphEntity agent, HashSet agentApps, Hashtable components) throws NotFound, NullEntity {
		Iterator it = agentApps.iterator();
		while (it.hasNext()) {
			GraphEntity app = (GraphEntity) it.next();
			Repeat appr = new Repeat("initialApplications");
			this.getAppPerceptionRels(app, agent, appr);
			appr.add(new Var("idapp", Utils.replaceBadChars(app.getID())));
			r.add(appr);
			if (components.containsKey(app.getID())) {

				if (isSingleton(app, components)) {
					Repeat internal = new Repeat("initialApplicationsSingleton");
					appr.add(internal);
				} else {
					Repeat internal = new Repeat("initialApplicationsIndividual");
					appr.add(internal);
				}

			} else {
				this.fatalError();
				Log.getInstance().logERROR("Application " + app.getID() +
						" has not any associated file. Create an ingenias component, link it with the " +
						"application in a component diagram, and create a filespecmapping instance in the ingenias component", "", app.getID());

			}

		}



	}

	/**
	 * It tells if for a given application, its creation and maintenance follows a singleton pattern or not.
	 * This is checked out by inspecting the component associated to the application. Concretely, we pay attention
	 * to slots of type "FileSpecPatternMapping" contained in the component where the pattern is specificied  in
	 * the field "Pattern".
	 *
	 * @param appr The application being studied
	 * @param components A table containing the applications as value and their id as key
	 * @return true if it the user wants a singletong pattern.
	 * @throws NotFound
	 * @throws NullEntity
	 */
	private boolean isSingleton(GraphEntity appr, Hashtable components) throws NotFound, NullEntity {
		GraphEntity component = (GraphEntity) components.get(appr.getID());
		GraphAttribute files;
		try {
			if (component == null) {
				Log.getInstance().logWARNING("Entity " + appr.getID() +
						" was not associated to any compoment. I cannot guess " +
						"if the application should appear as a singletong pattern." +
						" I will go on with the singleton option by default", "", appr.getID());
				return true;
			}
			files = component.getAttributeByName("Files");
		} catch (NotFound e) {
			Log.getInstance().logERROR("Entity " + component.getID() +
					" did not have attribute Files", "", component.getID());
			this.fatalError();
			throw e;
		}
		GraphCollection gc;
		try {
			gc = files.getCollectionValue();
		} catch (NullEntity e) {
			Log.getInstance().logERROR("Entity " + component.getID() +
					" do not have any collection of Application-File associated", "", component.getID());
			this.fatalError();
			throw e;
		}
		boolean itis = false;
		int k = 0;
		while (!itis && k < gc.size()) {
			GraphEntity ge = null;
			try {
				ge = gc.getElementAt(k);
				if (ge.getType().equals("FileSpecPatternMapping")) {
					itis = ge.getAttributeByName("Pattern").getSimpleValue().equals("Singleton") &&
					ge.getAttributeByName("Entity").getEntityValue().equals(appr);

				}
				k++;
			} catch (NullEntity e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return itis;
	}

	/**
	 * It obtains functionality associated to a role or any of its ascendants. It looks for
	 * entities connected to "role" by means of relationships of type "rel" in the extreme
	 * "relext". This operation is repeated for each role being superclass of the current one.
	 * The result is those entities appearing in the selected extreme of the association.
	 *
	 * @param role The initial role being studied
	 * @param rel The relationship type
	 * @param relext The name of the extreme
	 * @return An array of the identified elements
	 * @throws NullEntity
	 */
	private GraphEntity[] getRoleInheritedRels(GraphEntity role, String rel,
			String relext) throws NullEntity {

		Collection<? extends GraphEntity> inheritedRoles = Utils.getAscendantsOfRole(role);
		Vector inheritedInteractions = Utils.getRelatedElementsVectorAux(role,
				rel, relext);

		Iterator<? extends GraphEntity> enumerationRoles = inheritedRoles.iterator();
		while (enumerationRoles.hasNext()) {
			GraphEntity parentRole = (GraphEntity) enumerationRoles.next();
			Vector inheritedInteractionsTemp = Utils.getRelatedElementsVectorAux(role,
					rel, relext);
			inheritedInteractions.addAll(inheritedInteractionsTemp);
		}
		GraphEntity[] result = null;
		HashSet totalinter = new HashSet(inheritedInteractions);

		result = new GraphEntity[totalinter.size()];
		System.arraycopy(totalinter.toArray(), 0, result, 0,
				totalinter.size());
		return result;
	}

	public void fatalError(){
		error = true;
	}

	/**
	 *  Generates JADE code from a INGENIAS specification file (1st param)
	 *
	 *@param  args           Description of Parameter
	 *@exception  Exception  Description of Exception
	 */
	public static void main(String args[]) throws Exception {
		ingenias.editor.Log.initInstance(new PrintWriter(System.out));
		IAFGenerator jadegen = new IAFGenerator(args[0]);
		Properties props = jadegen.getBrowser().getState().prop;

		if (args.length!=0){
			if (args.length==2){
				jadegen.setProperty("jadeproject", args[1]);        		
			} else 
				if (args.length!=1){
					System.err.println("The first argument (mandatory) has to be the specification file and the second (optional) the project folder");
					System.exit(0);
				} 
		} else {
			System.err.println("The first argument (mandatory) has to be the specification file and the second (optional) the project folder");
			System.exit(0);
		}

		for (Object key: props.keySet()){
			System.err.println(((ProjectProperty)props.get(key.toString())).key+":"+((ProjectProperty)props.get(key.toString())).value);
		};

		jadegen.run();

		System.exit(0);

	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return true;
	}
}