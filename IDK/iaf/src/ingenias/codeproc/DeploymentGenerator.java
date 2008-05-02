/*
    Copyright (C) 2007 Jorge Gomez Sanz

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

import ingenias.exception.NotFound;
import ingenias.exception.NotInitialised;
import ingenias.exception.NullEntity;
import ingenias.generator.browser.GraphCollection;
import ingenias.generator.browser.GraphEntity;
import ingenias.generator.datatemplate.Repeat;
import ingenias.generator.datatemplate.Sequences;
import ingenias.generator.datatemplate.Var;

import java.util.HashSet;
import java.util.Vector;

public class DeploymentGenerator {

	/**
	 * It produces the data required to define each deployment package. It reads
	 * all DeploymentPackage entities and, for each one, it extracts the
	 * AgentsDeployed attribute to determine which types of agents are going to
	 * be created by the script. For each agentsdeployed unit, the user can
	 * determine the number of instances and the type of the agent
	 * 
	 * @param p
	 * @throws NotInitialised
	 */
	public static void generateDeployment(Sequences p) throws NotInitialised {

		GraphEntity[] deployPacks = Utils
		.generateEntitiesOfType("DeploymentPackage");

		if (deployPacks.length > 0) {
			for (int k = 0; k < deployPacks.length; k++) {
				GraphEntity deploymentPack = deployPacks[k];
				Repeat depl = new Repeat("deploynode");
				p.addRepeat(depl);

				int nagents = generateDeploymentPack(deploymentPack, depl);

			}
		}
		String port = "60000";
		// Default deployment: all in a single node
		Repeat depl = new Repeat("deploynode");
		p.addRepeat(depl);
		depl.add(new Var("port", "60000"));
		depl.add(new Var("fedport", "60001"));
		depl.add(new Var("memory", "128m"));
		depl.add(new Var("node", "")); // To generate a default node
		GraphEntity[] agents = Utils.generateEntitiesOfType("Agent");
		for (int k = 0; k < agents.length; k++) {
			Repeat agentsR = new Repeat("agents");
			depl.add(agentsR);
			agentsR.add(new Var("agentid", Utils.replaceBadChars(agents[k]
			                                                            .getID())));
			agentsR.add(new Var("agenttype", Utils.replaceBadChars(agents[k]
			                                                              .getID())));

		}

	}

	public static int generateDeploymentPack(GraphEntity deploymentPack, Repeat depl) {
		String port = "60000";
		String memory = "128m";
		int nagents=0;
		try {
			GraphCollection params = deploymentPack.getAttributeByName(
			"Parameters").getCollectionValue();
			for (int j = 0; j < params.size(); j++) {
				GraphEntity elem = params.getElementAt(j);
				try {
					if (elem.getAttributeByName("Name") != null
							&& elem.getAttributeByName("Name")
							.getSimpleValue().equalsIgnoreCase(
							"port")) {
						port = elem.getAttributeByName("Value")
						.getSimpleValue();
					}
					if (elem.getAttributeByName("Name") != null
							&& elem.getAttributeByName("Name")
							.getSimpleValue().equalsIgnoreCase(
							"memory")) {
						memory = elem.getAttributeByName("Value")
						.getSimpleValue();
					}

				} catch (NotFound e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (NullEntity e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		depl.add(new Var("port", port));
		depl.add(new Var("fedport", ""+(new Integer(port)+1)));
		depl.add(new Var("memory", memory));
		System.err.println("adding memory "+memory);

		depl.add(new Var("node", Utils.replaceBadChars(deploymentPack
				.getID())));
		try {


			HashSet<GraphEntity> depunits = new HashSet<GraphEntity> (Utils
			.getRelatedElementsVector(deploymentPack,
					"DefinesDeployment",
			"DefinesDeploymentsource"));
			GraphCollection dplPackCol = deploymentPack.getAttributeByName("AgentsDeployed").getCollectionValue();
			for (int k=0;k<dplPackCol.size();k++){
				depunits.add(dplPackCol.getElementAt(k));
			}
			System.err.println("Deployment units "+depunits);
			
			for (GraphEntity depunit : depunits) {
				if (depunit.getType().equals("DeploymentUnitByType")) {
					GraphEntity atype = depunit.getAttributeByName(
					"AgentTypeDeployed").getEntityValue();
					int ninstances = Integer.parseInt(depunit
							.getAttributeByName("NumberInstances")
							.getSimpleValue());
					for (int l = 0; l < ninstances; l++) {
						Repeat agentsR = new Repeat("agents");
						depl.add(agentsR);
						agentsR.add(new Var("agentid", Utils
								.replaceBadChars(atype.getID())
								+ "_" + l+Utils
								.replaceBadChars(depunit.getID())));
						agentsR.add(new Var("agenttype", Utils
								.replaceBadChars(atype.getID())));
					}
				}
				if (depunit.getType().equals(
				"DeploymentUnitByTypeEnumInitMS")) {
					System.err.println("by enum");
					GraphEntity atype = depunit.getAttributeByName(
					"AgentTypeDeployed").getEntityValue();
					int ninstances = Integer.parseInt(depunit
							.getAttributeByName("NumberInstances")
							.getSimpleValue());
					for (int l = 0; l < ninstances; l++) {
						Repeat agentsR = new Repeat("agents");
						depl.add(agentsR);
						agentsR.add(new Var("agentid", Utils
								.replaceBadChars(atype.getID())
								+ "_" + l+Utils
								.replaceBadChars(depunit.getID())));
						agentsR.add(new Var("agenttype", Utils
								.replaceBadChars(atype.getID())));

						GraphCollection mentalSpecCollection = depunit
						.getAttributeByName("InitialState")
						.getCollectionValue();
						for (int m = 0; m < mentalSpecCollection.size(); m++) {
							GraphEntity mInstance = mentalSpecCollection
							.getElementAt(m);
							GraphEntity mInstanceType = mInstance
							.getAttributeByName("InstanceType")
							.getEntityValue();
							GraphCollection slotValues = mInstance
							.getAttributeByName("SlotsValues")
							.getCollectionValue();
							Repeat mentalentitiesR = new Repeat(
							"initialentities");
							agentsR.add(mentalentitiesR);
							mentalentitiesR.add(new Var(
									"mentalentityname",
									Utils.replaceBadChars(mInstanceType
											.getID())));
							mentalentitiesR.add(new Var(
									"mentalentitytype",
									Utils.replaceBadChars(mInstanceType
											.getType())));
							for (int n = 0; n < slotValues.size(); n++) {
								Repeat slotvaluesR = new Repeat(
								"slotvalue");
								mentalentitiesR.add(slotvaluesR);

								GraphEntity slotValueSpec = slotValues
								.getElementAt(n);
								GraphEntity slot = slotValueSpec
								.getAttributeByName("Slot")
								.getEntityValue();
								String value = slotValueSpec
								.getAttributeByName(
								"Value")
								.getSimpleValue();
								slotvaluesR.add(new Var("slotname",
										Utils.replaceBadChars(slot
												.getAttributeByName(
												"Name")
												.getSimpleValue())));
								slotvaluesR.add(new Var("slotvalue",
										value));

							}

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
		return nagents;
	}

}
