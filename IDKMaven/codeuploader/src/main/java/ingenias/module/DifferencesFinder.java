package ingenias.module;

import ingenias.generator.util.FileUtils;
import ingenias.module.diff_match_patch.Diff;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class DifferencesFinder {

	public static void  main(String args[]) throws FileNotFoundException, IOException{
		// args[0] original folder
		// args[1] copy folder
		
		/*File dir0=new File(args[0]);
		File dir1=new File(args[1]);
		
		recurseDirAndCompare(dir0,dir1);*/
		
		diff_match_patch dmp=new diff_match_patch();
		StringBuffer one=FileUtils.readFile("target/javagensrc/ingenias/jade/components/Determine_availabilityTask.java");
		StringBuffer two=FileUtils.readFile("/home/jj/actualizar/trunknuevo/IDKMaven/IAFTestProjects/cinema/src/main/javagensrc/ingenias/jade/components/Determine_availabilityTask.java");
		LinkedList<Diff> result = dmp.diff_main(one.toString(), two.toString());
		String htmlstring=dmp.diff_prettyHtml(result);
		JEditorPane jedit=new JEditorPane();
		jedit.setContentType("text/html");
		jedit.setText(htmlstring);
		JFrame jf=new JFrame();
		jf.getContentPane().setLayout(new BorderLayout());
		jf.getContentPane().add(new JScrollPane(jedit));
		jf.pack();
		jf.setVisible(true);
		
		
	}

	private static void recurseDirAndCompare(File dir0, File dir1) {
		File[] files = dir0.listFiles();
		for (File file:files){			
			File sameFileInDir1=new File(dir1.getAbsolutePath()+"/"+file.getName());
			if (sameFileInDir1.exists()){
				if (sameFileInDir1.isDirectory()){
					if (file.isDirectory()){
						recurseDirAndCompare(file, sameFileInDir1);
					} else {
						// wrong comparisson, do nothing
					}
				} else {
					if (file.isDirectory()){
						// wrong comparisson, do nothing
					} else {
						//compare both file and sameFileInDir1						
					}
						
				}
			}
			
		}
		
	}
}
