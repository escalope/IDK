package ingenias.tests;

import static org.junit.Assert.assertTrue;
import ingenias.editor.IDEState;
import ingenias.editor.MarqueeHandler;
import ingenias.editor.Model;
import ingenias.editor.ModelJGraph;
import ingenias.editor.actions.diagram.AgentModelActionsFactory;
import ingenias.editor.editionmode.EmbeddedAndPopupCellEditor;
import ingenias.editor.entities.AgentModelDataEntity;
import ingenias.editor.entities.AgentModelModelEntity;
import ingenias.editor.models.AgentModelModelJGraph;
import ingenias.exception.InvalidEntity;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JWindow;

import org.apache.commons.io.IOUtils;
import org.gjt.sp.util.IOUtilities;
import org.jgraph.JGraph;
import org.jgraph.graph.BasicMarqueeHandler;
import org.jgraph.graph.DefaultGraphCell;
import org.junit.Test;


public class JGraphToSVG {
	@Test
	public void launchConversion(){
		IDEState ids=IDEState.emptyIDEState();
		
		AgentModelModelJGraph mjg =
			new AgentModelModelJGraph(new
					AgentModelDataEntity(
							"hello"), "hello", ids.om, new Model(ids),
							new BasicMarqueeHandler(), ids.prefs );
		
		
		BasicMarqueeHandler marquee=new BasicMarqueeHandler();
		mjg.setMarqueeHandler(marquee);
		ids.gm.addModel(new Object[]{}, "hello", mjg);
		ids.addNewDiagram(mjg);
		try {
			DefaultGraphCell cell = mjg.insert(new Point(0,0), "Agent");
		} catch (InvalidEntity e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		JWindow jw=new JWindow();
		jw.getContentPane().add(mjg);
		jw.pack();
		jw.setVisible(true);
		File output;
		try {
			output = File.createTempFile("idk", "svg");
			ingenias.editor.export.Diagram2SVG.diagram2SVG(mjg,output, "svg");			
			System.out.println("File:"+output.toString());
			String createdFile=IOUtils.toString(new FileInputStream(output), "UTF-8");
			System.out.println(createdFile);
			assertTrue("This file corresponds probably to a bitmap converstion of the whole graph, because" +
					"I could not detect font-family, path, stroke, rect, or style tags",
					createdFile.indexOf("font-family")>=0 &&
					createdFile.indexOf("path")>=0 &&
					createdFile.indexOf("style")>=0 && 
					createdFile.indexOf("stroke")>=0 &&
					createdFile.indexOf("rect")>=0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
		
	}
}
