package ingenias.jade.mental;

import ingenias.editor.entities.RuntimeFact;
import ingenias.editor.entities.ViewPreferences.ViewType;
import java.util.*;

public class OrganizationDescription  extends RuntimeFact{

	private String orgID="";
	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	private Hashtable<String,Vector<String>> groupStructure= new Hashtable<String,Vector<String>>();
	private Hashtable<String,Vector<String>> membership= new Hashtable<String,Vector<String>>();
	private Hashtable<String,String> groupType= new Hashtable<String,String>();
	private String orgType="";

	public OrganizationDescription (){
		super(ingenias.jade.MentalStateManager.generateMentalEntityID());
		this.getPrefs(null).setView(ViewType.UML);
	}

	public void setOrgName(String orgID, String type){
		this.orgID=orgID;
		this.orgType=type;
	}

	public void addGroup(String rootGroup, String type){
		groupStructure.put(rootGroup,new Vector<String>());
		groupType.put(rootGroup,type);
	}
		
		
	
	public void addSubGroup(String rootGroup, String childGroup, String childGroupType){
		if (!groupStructure.containsKey(rootGroup)){
			throw new RuntimeException(rootGroup +" group was not added before. Please, use first addgroup");
		}
		groupType.put(childGroup,childGroupType);
		groupStructure.get(rootGroup).add(childGroup);
		groupStructure.put(childGroup,new Vector<String>());
	}

	public void addMember(String rootGroup, String memberid){
		if (!membership.containsKey(rootGroup))
			membership.put(rootGroup,new Vector<String>());
		membership.get(rootGroup).add(memberid);
	}

	public String toString(){
		return this.getId()+":"+this.getType();
	}

	public String getType(){
		return "OrganizationDescription";
	}

	public HashSet<String> getGroups() { // get groups in the organization
		HashSet<String> groups=new HashSet<String>();
		groups.addAll(this.groupStructure.keySet());
		for (String cg:this.groupStructure.keySet()){
		groups.addAll(this.groupStructure.get(cg));
		}
		return groups;

	}

	public HashSet<String> getMembers(String cgroups) {
		if (membership.get(cgroups)!=null)
			return new  HashSet<String> (membership.get(cgroups));
		else
			return new  HashSet<String> ();
	}

	public String getGroupType(String groupid) {
		return this.groupType.get(groupid);
	}

	public HashSet<String> getSubgroups(String group) {
		HashSet<String> result=new HashSet<String>();
		if (groupStructure.containsKey(group)){
			for (String subgroup:groupStructure.get(group)){
				result.add(subgroup);
				result.addAll(getSubgroups(subgroup));
			}			
		}
		return result;		
	}




}
