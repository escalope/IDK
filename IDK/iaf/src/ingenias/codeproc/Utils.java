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

import ingenias.exception.NotInitialised;
import ingenias.exception.NullEntity;
import ingenias.generator.browser.Browser;
import ingenias.generator.browser.BrowserImp;
import ingenias.generator.browser.Graph;
import ingenias.generator.browser.GraphEntity;
import ingenias.generator.browser.GraphRelationship;
import ingenias.generator.browser.GraphRole;
import ingenias.generator.datatemplate.Sequences;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

/**
 * This class encapsulates methods to traverse the specification. In general, it allows
 * to obtain, given an entity, query the specification to obtain connected entities.
 * It also provides transformation methods to arrays of GraphEntity and GraphRelationship.
 *  
 * @author jj
 *
 */
public class Utils {

	/**
	 * It replaces incorrect chars that may cause conflicts in the final instances
	 * @param string The string being converted, like white spaces
	 * @return A string without improper characters
	 */
	public static String replaceBadChars(String string){
		return string.replace(' ','_').replace(',','_').replace('.','_').replace('-', '_');
	}

	/**
	 * It obtains the elements in the specification linked with "element" that have an association of type
	 * "relationshipname" and they occupy the extreme labelled with the same string as "role"
	 * 
	 * @param element The element to be studied
	 * @param relationshipname The name of the relationship which will be studied
	 * @param role The name of the extreme of the relationship that has to be studied
	 * @return a list of elements placed in the association extreme
	 * @throws NullEntity
	 */
	public static GraphEntity[] getRelatedElements(GraphEntity element,
			String relationshipname, String role) throws NullEntity {
		Vector rels = element.getAllRelationships();
		Enumeration enumeration = rels.elements();
		Vector related = new Vector();
		while (enumeration.hasMoreElements()) {
			GraphRelationship gr = (GraphRelationship) enumeration.nextElement();
			if (gr.getType().toLowerCase().equals(relationshipname.toLowerCase())) {
				GraphRole[] roles = gr.getRoles();
				for (int k = 0; k < roles.length; k++) {
					//System.err.println(roles[k].getName());
					if (roles[k].getName().toLowerCase().equals(role.toLowerCase())) {
						//System.err.println("added"+roles[k].getName());
						related.add(roles[k].getPlayer());
					}
				}
			}
		}
		return toGEArray(new HashSet(related).toArray());
	}

	/**
	 * It obtains the elements in the specification linked with "element" that have an association of type
	 * "relationshipname" and they occupy the extreme labelled with the same string as "role". Elements
	 * returned cannot be equal to "element".
	 * 
	 * @param element The element to be studied
	 * @param relationshipname The name of the relationship which will be studied
	 * @param role The name of the extreme of the relationship that has to be studied
	 * @return a list of elements placed in the association extreme
	 * @throws NullEntity
	 */
	public static GraphEntity[] getRelatedElementsAux(GraphEntity element,
			String relationshipname,
			String role) throws NullEntity {
		Vector rels = element.getAllRelationships();
		Enumeration enumeration = rels.elements();
		Vector related = new Vector();
		while (enumeration.hasMoreElements()) {
			GraphRelationship gr = (GraphRelationship) enumeration.nextElement();
			if (gr.getType().toLowerCase().equals(relationshipname.toLowerCase())) {
				GraphRole[] roles = gr.getRoles();
				for (int k = 0; k < roles.length; k++) {
					if (roles[k].getName().toLowerCase().equals(role.toLowerCase()) &&
							!roles[k].getPlayer().equals(element)) {
						related.add(roles[k].getPlayer());
					}
				}
			}
		}
		return Utils.toGEArray(new HashSet(related).toArray());
	}

	/**
	 * Same as getRelatedElementsAux but returning the result as vectors
	 * 
	 * @param element The element to be studied
	 * @param relationshipname The name of the relationship which will be studied
	 * @param role The name of the extreme of the relationship that has to be studied
	 * @return a list of elements placed in the association extreme
	 * @throws NullEntity
	 */
	public static  Vector getRelatedElementsVectorAux(GraphEntity element,
			String relationshipname,
			String role) throws NullEntity {
		Vector rels = element.getAllRelationships();
		Enumeration enumeration = rels.elements();
		Vector related = new Vector();
		while (enumeration.hasMoreElements()) {
			GraphRelationship gr = (GraphRelationship) enumeration.nextElement();
			if (gr.getType().toLowerCase().equals(relationshipname.toLowerCase())) {
				GraphRole[] roles = gr.getRoles();
				for (int k = 0; k < roles.length; k++) {
					if (roles[k].getName().toLowerCase().equals(role.toLowerCase()) &&
							! (roles[k].getPlayer().equals(element))) {
						related.add(roles[k].getPlayer());
					}
				}
			}
		}
		return new Vector(new HashSet(related));
	}

	/**
	 * Same as getRelatedElements but returning the result as a vector
	 * 
	 * @param element The element to be studied
	 * @param relationshipname The name of the relationship which will be studied
	 * @param role The name of the extreme of the relationship that has to be studied
	 * @return a list of elements placed in the association extreme
	 */
	public static Vector getRelatedElementsVector(GraphEntity agent,
			String relationshipname, String role) throws NullEntity {
		Vector rels = agent.getAllRelationships();
		Enumeration enumeration = rels.elements();
		Vector related = new Vector();
		while (enumeration.hasMoreElements()) {
			GraphRelationship gr = (GraphRelationship) enumeration.nextElement();
			if (gr.getType().toLowerCase().equals(relationshipname.toLowerCase())) {
				GraphRole[] roles = gr.getRoles();
				for (int k = 0; k < roles.length; k++) {
					if (roles[k].getName().toLowerCase().equals(role.toLowerCase())) {
						related.add(roles[k].getPlayer());
					}
				}
			}
		}
		return new Vector(new HashSet(related));
	}

	/**
	 * It obtains all elements related with "element" with "relationshipname" and occupying the extreme "role.
	 * These elements are then allocated in a hashtable using the obtained entity as key and the relationship
	 * where this entity appears as value 
	 * 
	 * @param element The element to be studied
	 * @param relationshipname The name of the relationship which will be studied
	 * @param role The name of the extreme of the relationship that has to be studied
	 * @return a hashtable
	 * @throws NullEntity
	 */
	public static  Hashtable getRelatedElementsHashtable(GraphEntity element,
			String relationshipname, String role) throws NullEntity {
		Vector rels = element.getAllRelationships();
		Enumeration enumeration = rels.elements();
		Hashtable related = new Hashtable();
		while (enumeration.hasMoreElements()) {
			GraphRelationship gr = (GraphRelationship) enumeration.nextElement();
			if (gr.getType().toLowerCase().equals(relationshipname.toLowerCase())) {
				GraphRole[] roles = gr.getRoles();
				for (int k = 0; k < roles.length; k++) {
					if (roles[k].getName().toLowerCase().equals(role.toLowerCase())) {
						related.put(roles[k].getPlayer(),gr);
					}
				}
			}
		}
		return related;
	}

	/**
	 * It obtains all elements related with "element" with "relationshipname" and occupying the extreme "role.
	 * Also, the association where these elements appear must be allocated in the package whose pathname
	 * matches the "pathname" parameter
	 *  
	 * @param pathname Part of a path name. It will force located relationships to belong to concrete sets of diagrams
	 * allocated in concrete packages
	 * @param element The element to be studied
	 * @param relationshipname The name of the relationship which will be studied
	 * @param role The name of the extreme of the relationship that has to be studied
	 * @return A list of entities.
	 * @throws NullEntity
	 */
	public static  Vector getRelatedElementsVector(String pathname,GraphEntity element,
			String relationshipname, String role) throws NullEntity {
		Vector rels = element.getAllRelationships();
		Enumeration enumeration = rels.elements();
		Vector related = new Vector();
		while (enumeration.hasMoreElements()) {
			GraphRelationship gr = (GraphRelationship) enumeration.nextElement();
			String[] path=gr.getGraph().getPath();
			boolean found=false;
			for (int k=0;k<path.length && !found;k++){
				found=path[k].toLowerCase().indexOf(pathname)>=0;
			}
			if (found && gr.getType().toLowerCase().equals(relationshipname.toLowerCase())) {
				GraphRole[] roles = gr.getRoles();
				for (int k = 0; k < roles.length; k++) {
					if (roles[k].getName().toLowerCase().equals(role.toLowerCase())) {
						related.add(roles[k].getPlayer());
					}
				}
			}
		}
		return new Vector(new HashSet(related));
	}

	/**
	 * It casts an array of objets to an array of GraphEntity
	 *  
	 * @param o the array of objects
	 * @return
	 */
	public static GraphEntity[] toGEArray(Object[] o) {
		GraphEntity[] result = new GraphEntity[o.length];
		System.arraycopy(o, 0, result, 0, o.length);
		return result;
	}

	/**
	 * It casts an array of objets to an array of GraphRelationship
	 *  
	 * @param o the array of objects
	 * @return
	 */
	public static GraphRelationship[] toGRArray(Object[] o) {
		GraphRelationship[] result = new GraphRelationship[o.length];
		System.arraycopy(o, 0, result, 0, o.length);
		return result;
	}

	/**
	 * It casts an array of objets to an array of GraphRole
	 *  
	 * @param o the array of objects
	 * @return
	 */
	public static GraphRole[] toGRoArray(Object[] o) {
		GraphRole[] result = new GraphRole[o.length];
		System.arraycopy(o, 0, result, 0, o.length);
		return result;
	}

	/**
	 * It obtains all entities in the specification whose type represented as string
	 * is the same as the string passed as parameter
	 * 
	 * @param type The type the application is looking for
	 * @return
	 * @throws NotInitialised 
	 */
	public static  GraphEntity[] generateEntitiesOfType(String type, Browser browser) throws NotInitialised {
		Graph[] gs = browser.getGraphs();
		Sequences p = new Sequences();
		GraphEntity[] ges = browser.getAllEntities();
		HashSet actors = new HashSet();
		for (int k = 0; k < ges.length; k++) {
			if (ges[k].getType().equals(type)) {
				actors.add(ges[k]);
			}
		}
		return toGEArray(actors.toArray());
	}

	/**
	 * It obtains the extremes of the association of type 	"relationshipname", where one
	 * of their roles is "role", and originated in the "element"
	 * 
	 * @param element The element to be studied
	 * @param relationshipname The name of the relationship which will be studied
	 * @param role The name of the extreme of the relationship that has to be studied
	 * @return An array of roles
	 */
	public static  GraphRole[] getRelatedElementsRoles(GraphEntity element,
			String relationshipname,
			String role) {
		Vector rels = element.getAllRelationships();
		Enumeration enumeration = rels.elements();
		Vector related = new Vector();
		while (enumeration.hasMoreElements()) {
			GraphRelationship gr = (GraphRelationship) enumeration.nextElement();
			if (gr.getType().toLowerCase().equals(relationshipname.toLowerCase())) {
				GraphRole[] roles = gr.getRoles();
				for (int k = 0; k < roles.length; k++) {
					if (roles[k].getName().toLowerCase().equals(role.toLowerCase())) {
						related.add(roles[k]);
					}
				}
			}
		}
		return toGRoArray(related.toArray());
	}
        
        
        /**
	 * It obtains the extremes of the association of type 	"relationshipname", where one
	 * of their roles is "role", and originated in the "element"
	 * 
	 * @param element The element to be studied
	 * @param relationshipname The name of the relationship which will be studied
	 * @param role The name of the extreme of the relationship that has to be studied
	 * @return A vector of roles
	 */
	public static  Vector<GraphRole> getRelatedElementsRolesVector(GraphEntity element,
			String relationshipname,
			String role) {
		Vector rels = element.getAllRelationships();
		Enumeration enumeration = rels.elements();
		Vector<GraphRole> related = new Vector<GraphRole>();
		while (enumeration.hasMoreElements()) {
			GraphRelationship gr = (GraphRelationship) enumeration.nextElement();
			if (gr.getType().toLowerCase().equals(relationshipname.toLowerCase())) {
				GraphRole[] roles = gr.getRoles();
				for (int k = 0; k < roles.length; k++) {
					if (roles[k].getName().toLowerCase().equals(role.toLowerCase())) {
						related.add(roles[k]);
					}
				}
			}
		}
		return related;
	}

	/**
		 * It returns an array of the relationships whose name is "relationshipname" and 
		 * that are linked to "element" and there is an element occupiying the extreme
		 * labelled with "role"
		 * 
		 * @param element The element to be studied
		 * @param relationshipname The name of the relationship which will be studied
		 * @param role The name of the extreme of the relationship that has to be studied
		 * @return an array of relationships
		 */
	public static GraphRelationship[] getRelatedElementsRels(GraphEntity element,
				String relationshipname, String role) {
			Vector rels = element.getAllRelationships();
			Enumeration enumeration = rels.elements();
			Vector related = new Vector();
			while (enumeration.hasMoreElements()) {
				GraphRelationship gr = (GraphRelationship) enumeration.nextElement();
				if (gr.getType().toLowerCase().equals(relationshipname.toLowerCase())) {
					GraphRole[] roles = gr.getRoles();
					for (int k = 0; k < roles.length; k++) {
						if (roles[k].getName().toLowerCase().equals(role.toLowerCase())) {
							related.add(gr);
						}
					}
				}
			}
			return toGRArray(related.toArray());
		}

	/**
	 * It obtains the entities in the graph "g" whose type is the same as "typeName".
	 * 
	 * @param g The graph considered
	 * @param typeName The type being searched
	 * @return The list of entities
	 * @throws NullEntity
	 */
	public static Vector getEntities(Graph g, String typeName) throws NullEntity {
		GraphEntity[] ge = g.getEntities();
		Vector result = new Vector();
		for (int k = 0; k < ge.length; k++) {
			if (ge[k].getType().equals(typeName)) {
				result.add(ge[k]);
			}
		}
		return result;
	}

}
