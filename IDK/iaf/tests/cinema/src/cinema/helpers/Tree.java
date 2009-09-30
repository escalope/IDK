package cinema.helpers;

import java.util.Vector;



public class Tree {
	Object root;
	Vector<Tree> children=new Vector<Tree>(); 
	public Tree(){};
	
	
public String toString(int indentation){
		
		String childRepres="";
		for (Tree child:children){
			childRepres=childRepres+child.toString(indentation+1);
		}
		String indent="";
		for (int k=0;k<indentation;k++)
			indent=indent+" ";
		return "\n"+indent+root+" "+childRepres;
	}
		
	public String toString(){
		
		return toString(0);
	}

}