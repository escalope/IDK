package ingenias.editor;

import ingenias.editor.extension.BasicCodeGenerator;
import ingenias.editor.extension.BasicTool;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UpdateToolsAndCG
extends Thread {
	IDEAbs ide;
	Set ct = new HashSet();
	Set cg = new HashSet();
	public UpdateToolsAndCG(IDEAbs abs) {
		this.ide = abs;
	}
	
	public void readLibs(String folder) {
		Set ct1 = new HashSet();
		Set cg1 = new HashSet();
		ingenias.editor.extension.ModuleLoader.updateStatus(folder,ct1, cg1);
		Iterator it = ct1.iterator();
		while (it.hasNext()) {
			try {
				Class bt = (Class) it.next();  
				java.lang.reflect.Constructor cons = bt.getDeclaredConstructor(new
						Class[0]);
				BasicTool bti = (BasicTool) cons.newInstance(new Object[0]);
				
				if (ct.contains(bti.getName())) {
					ide.removeEntry(bti);
				}
				else {
					ct.add(bti.getName());
				}
				
				ide.addToolEntry(bti);
				
			}
			catch (Exception ex) {
				ex.printStackTrace();
				Log.getInstance().logERROR(ex.getMessage());
			}
		}
		it = cg1.iterator();
		while (it.hasNext()) {
			try {
				Class bt = (Class) it.next();
				java.lang.reflect.Constructor cons = bt.getDeclaredConstructor(new
						Class[0]);
				BasicCodeGenerator bcg = (BasicCodeGenerator) cons.newInstance(new
						Object[0]);
				if (cg.contains(bcg.getName())) {
					ide.removeEntry(bcg);
				}
				else {
					cg.add(bcg.getName());
					
				}
				ide.addCGEntry(bcg);
				
			}
			catch (Exception ex) {
				ex.printStackTrace();
				Log.getInstance().logERROR("Error loading module:"+ex.getMessage());
			}
		}
		if (ct1.size() != 0 || cg1.size() != 0) {
			System.gc();
			
		}
	}
	
	public void run() {
		String folder = null;
		while (true) {
			if (ide.ids.prop.get("extfolder") == null) {
				folder = "ext";
			}
			else {
				folder = ( (ProjectProperty) ide.ids.prop.get("extfolder")).value;
			}
			this.readLibs(folder);
			try {
				Thread.sleep(5000);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
	}
}