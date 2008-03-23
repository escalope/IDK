package cinema.data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import com.thoughtworks.xstream.XStream;

public class MonthActivityDefinition {

	public static void main(String args[]){
		Vector<String>[] monthActivity=new Vector[1];
		monthActivity[0]=new Vector<String>();
		monthActivity[0].add("InterfaceAgent_0");		
		XStream xs=new XStream();
		try {
			FileOutputStream fos = new FileOutputStream("config/monthlyActivity.xml");
			xs.toXML(monthActivity, fos);
			fos.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
