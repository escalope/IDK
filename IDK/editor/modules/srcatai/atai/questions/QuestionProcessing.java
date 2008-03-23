package atai.questions;

import ingenias.editor.entities.Entity;
import ingenias.exception.InvalidEntity;
import ingenias.exception.InvalidGraph;
import ingenias.exception.InvalidPath;
import ingenias.exception.NotFound;
import ingenias.exception.NotInitialised;
import ingenias.exception.NullEntity;
import ingenias.generator.browser.BrowserImp;
import ingenias.generator.browser.Graph;
import ingenias.generator.browser.GraphAttributeFactory;
import ingenias.generator.browser.GraphEntity;
import ingenias.generator.browser.GraphEntityFactory;
import ingenias.generator.browser.GraphFactory;
import ingenias.generator.browser.GraphRelationshipFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class QuestionProcessing {
	private static int counter=0;
	private static int dcounter=0;
	
	public static void representAnswerInINGENIASDiagrams(Hashtable<String, HashSet<Vector<String>>> diagramRelationshipsDistribution, 
			Hashtable<String, Vector<String>> selectedAssignments) {		
		try {
			GraphFactory gf=null;
			GraphEntityFactory gef=null;
			GraphRelationshipFactory grf=null;
			GraphAttributeFactory gaf=null;

			gf = GraphFactory.createDefaultGraphFactory();
			gef= GraphEntityFactory.createDefaultGraphFactory();
			grf= GraphRelationshipFactory.createDefaultGraphFactory();
			gaf= GraphAttributeFactory.createDefaultGraphFactory();
			
			Set<String> diagramTypes = diagramRelationshipsDistribution.keySet();
			for (String dtype:diagramTypes){
				
				ingenias.generator.browser.Graph g = gf.createCompleteGraph(new String[]{"results","execution "+counter},dtype,"atai_result"+dcounter);
				dcounter++;
				HashSet<Vector<String> > relsTypeToCreate = diagramRelationshipsDistribution.get(dtype);
				for (Vector<String>  relType:relsTypeToCreate){
					Collection<Vector<String>> relationshipsToCreate = selectedAssignments.values();
					for (Vector<String> relSpec:relationshipsToCreate){
						if (relSpec.equals(relType)){
							
							// Obtain the INGENIAS id's of the elements to be inserted
							Vector<String> values = new Vector<String>(relSpec);
							values.remove(0); // the first string is the relationship type, therefore we remove it							
							Vector<Hashtable<String, String>> assignments;
							checkAndCreateEntities(values,g,gef);
							try {								
								assignments = grf.getPossibleRoleAssignment(relSpec.firstElement(),values.toArray(new String[values.size()]), g);
								System.err.println("checking "+relSpec+" in "+g.getType());
								grf.createRelationship(relSpec.firstElement(), g, assignments.firstElement());
								
							} catch (NotFound e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvalidEntity e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
				}
			}

		} catch (InvalidGraph e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotInitialised e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void checkAndCreateEntities(Vector<String> values, Graph g, GraphEntityFactory gef) {
		for (String value:values){
			try {				
				GraphEntity[] entities = g.getEntities();
				boolean found=false;
				for (GraphEntity ent:entities){
					if (ent.getID().equals(value)){
						found=true;
					}
				}
				if (!found)				
				 gef.reuseEntity(value, g);
			} catch (InvalidEntity e) {
				e.printStackTrace();
			} catch (NullEntity e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
	}

	public static void createOutputPackages(GraphFactory gf) {
		try {
			gf.createPackage(new String[0], "results");
		} catch (InvalidPath e) {
			// It may be already created
			//e.printStackTrace();
		}
		boolean wrongCount=true;
		while(wrongCount){
			try {
				gf.createPackage(new String[]{"results"}, "execution "+counter);
				wrongCount=false;
			} catch (InvalidPath e) {
				//e.printStackTrace();
				counter++;
				//System.out.println("Trying "+counter);
			}
		}
	}
	
	/** 
	 * It assumes that the AT question is filled in completely, i.e., if a relationship
	 * appears in the question template, this relationship actually exists in the instance 
	 * (relation name in AT, [{possible INGENIAS interpretation, value of extreme1,value of extreme2,... },
	 * {possible INGENIAS interpretation', value of extreme1',value of extreme2',... },...])
	 * @param question
	 * @param answer
	 * @return
	 */
	public static Hashtable<String,Vector<Vector<String>>> findINGENIASEquivalentRelationships(ATQuestionGraph question, Map<String, String> answer){
		Hashtable<String,Vector<Vector<String>>> result=new Hashtable<String,Vector<Vector<String>>>();
		ATGraph atQuestion = question.getQuestion();
		List<String> entities = atQuestion.getEntities();
		Map<String, String> players = atQuestion.getPlayers();
		List<String> relations = atQuestion.getRelations();
		Map<String, String> reltypes = atQuestion.getRelationTypes();
		Map<String, List<String>> roles = atQuestion.getRoles();
		GraphRelationshipFactory grf=null;
		try {
			grf=new GraphRelationshipFactory(BrowserImp.getInstance().getState());
		} catch (NotInitialised e) {
			
			e.printStackTrace();
		}
		for (String relName:relations){			 
			List<String> possibleIngeniasRel = 
				ATView.getRelationTypesForATRelation(ATRelation.valueOf(reltypes.get(relName)));
			List<String> relationRoles = roles.get(relName);
			Vector<String> connectedEntities=new Vector<String>(); 
			for (String relationRole:relationRoles){
				String ingeniasEntityName = answer.get(players.get(relationRole));
				connectedEntities.add(ingeniasEntityName);
			}			
			Vector< Vector<String>> alternativeMappingsForSameATRelationship=new Vector< Vector<String>>();
			for (String posmapping:possibleIngeniasRel){
				Vector<String> ingeniasRelationship = new Vector<String>(connectedEntities);				
				try {
					Vector<Hashtable<String, String>> mapping = grf.getPossibleRoleAssignment(posmapping, 
							ingeniasRelationship.toArray(new String[ingeniasRelationship.size()]));
					if (mapping.size()>0){
						ingeniasRelationship.add(0,posmapping);
						alternativeMappingsForSameATRelationship.add(ingeniasRelationship);						
					} 
				} catch (NotFound e) {
					e.printStackTrace();
				}
				
			}
			if (alternativeMappingsForSameATRelationship.size()==0){
				System.err.println("Relationship "+relName+" has no proper translation in INGENIAS for elemetns "+connectedEntities);
			}
			result.put(relName,alternativeMappingsForSameATRelationship);
		}
		return result;
	}

	/**
	 * It provides a set of diagram types with the relationships that could fit within each of them. The method tries to produce
	 * the lowest number of diagrams
	 * ( AT_relationship_name, {INGENIAS_relationship_name INGENIAS_value1 ... INGENIAS_valuen})
	 * 
	 * Diagrams={d1:t1,d2:t2,...,dn:tn} such as #Diagram is minimum of all sets D made of diagrams of valid
	 * INGENIAS types that for any relationship INGENIAS_relationship, 
	 * there exists d_i:t_i in the set where R is allowed in d_i
	 * 
	 * The algorithm consists in a greedy algorithm that selects first those diagrams that cover
	 * more relationships of those requested initially until all relationships are covered.
	 *  
	 * @param relationships
	 * @return
	 */
	public static Hashtable<String,HashSet<Vector<String>>> 
	findGraphConfigurationFromPossibleRelationships(Hashtable<String,Vector<String>> relationships){

		Vector<String> ingeniasValidDiagramTypes = GraphFactory.getDiagramTypes();
		Hashtable<String, HashSet<Vector<String>>> relsPerDiagram=new Hashtable<String, HashSet<Vector<String>>>();
		Hashtable<String,Integer> numberOfRequestedRelationshipsPerDiagram=new Hashtable<String,Integer>();	
		Set<String> relNamesInAT = relationships.keySet();
		Collection<Vector<String>> relNamesInINGENIAS = relationships.values();
		HashSet<Vector<String> > relNamesSet=new HashSet<Vector<String> >();
		for (Vector<String> relName:relNamesInINGENIAS){
			relNamesSet.add(relName);
		}

		for (String diagramType:ingeniasValidDiagramTypes){
			try {
				Vector<String> relsDiagram = GraphFactory.getSupportedRelationships(diagramType);
				Vector<String> entsDiagram = GraphFactory.getSupportedEntities(diagramType);
				HashSet<Vector<String>> validRelationshipsForDiagram=null;

				validRelationshipsForDiagram=new HashSet<Vector<String>>();
				relsPerDiagram.put(diagramType,validRelationshipsForDiagram);

				for (Vector<String> rel:relNamesInINGENIAS){
					Vector<String> ents=new Vector<String>(rel);
					ents.remove(0);
					boolean typesAdmitted=true;
					for (String ent:ents){
					 Entity entity = (Entity) BrowserImp.getInstance().getState().om.findUserObject(ent).firstElement();
					 typesAdmitted=typesAdmitted&&entsDiagram.contains(entity.getType());
					}
					if (relsDiagram.contains(rel.firstElement())&&typesAdmitted)
						validRelationshipsForDiagram.add(rel);						 
				}					
			} catch (InvalidGraph e) {
				e.printStackTrace();
			} catch (NotInitialised e) {
				e.printStackTrace();
			}			
		}

		Hashtable<String,HashSet<Vector<String>>> result=new Hashtable<String,HashSet<Vector<String>>>();
		while (!relNamesSet.isEmpty()){
			String chosenDiagramType="";
			int biggestSize=0;
			Set<String> diagramsConsidered = relsPerDiagram.keySet();
			for (String diagram:diagramsConsidered){
				if (relsPerDiagram.get(diagram).size()>biggestSize){
					chosenDiagramType=diagram;
					biggestSize=relsPerDiagram.get(diagram).size();
				}
			}			
			relNamesSet.removeAll(relsPerDiagram.get(chosenDiagramType));
			result.put(chosenDiagramType,relsPerDiagram.get(chosenDiagramType));
			relsPerDiagram.remove(chosenDiagramType);
		}

		return result;

	}
}
