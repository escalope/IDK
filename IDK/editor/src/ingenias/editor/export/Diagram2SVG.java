package ingenias.editor.export;

/*
 *  Copyright (c) 2004 Jorge Gomez Sanz
 *
 *  This file is the result of the modification of original work extracted
 *  from swing forums from sun. It is part of IDK, a support tool for the INGENIAS
 *  methodology, availabe at http://grasia.fdi.ucm.es/ingenias or
 *  http://ingenias.sourceforge.net
 *
 *  INGENIAS IDE is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *  INGENIAS IDE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with INGENIAS IDE; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
import ingenias.exception.*;
import ingenias.editor.IDEState;
import java.lang.reflect.*;
import javax.swing.tree.*;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.CachedImageHandlerBase64Encoder;
import org.apache.batik.svggen.GenericImageHandler;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.apache.batik.svggen.SwingSVGPrettyPrint;

import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.InputSource;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.*;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;
import javax.xml.parsers.*;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.jibble.epsgraphics.EpsGraphics2D;
import org.swixml.XVBox;
import org.w3c.dom.*;
import ingenias.editor.entities.*;
import ingenias.exception.*;
import ingenias.editor.cell.*;
import ingenias.editor.*;
import javax.swing.*;
import javax.swing.tree.*;
import ingenias.exception.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;

import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;

/**
 * Export the current graph to an SVG file.
 */

public class Diagram2SVG {

	static {
		//UIManager.put("XVBoxUI", XVBox.class.getName());		 
	}
	public Diagram2SVG() {
	}


	public static BufferedImage createImage(JComponent component, Rectangle region)
	throws IOException
	{
		boolean opaqueValue = component.isOpaque();
		component.setOpaque( true );
		BufferedImage image = new BufferedImage(region.width, region.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(
				RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g.setClip( region );
		component.paint( g );
		g.dispose();
		component.setOpaque( opaqueValue );	
		return image;
	}

	public  static void createEPS(JComponent graph, File output) throws FileNotFoundException, SVGGraphics2DIOException{

		// Get a DOMImplementation


		FileOutputStream fos=new FileOutputStream(output);

		try {

			JWindow jw=new JWindow();
			RepaintManager currentManager =
				RepaintManager.currentManager(jw);
			boolean dBufferOn = currentManager.isDoubleBufferingEnabled();
			currentManager.setDoubleBufferingEnabled(false);
			
			jw.getContentPane().add(graph);
			jw.pack();
			EpsGraphics2D g2d=new EpsGraphics2D("prueba",fos,2,2,jw.getSize().width-2,jw.getSize().height-2);

			if (jw.getSize().width!=0 && jw.getSize().height!=0){

				//svgGenerator.setSVGCanvasSize(new Dimension(2000,2000));      
				graph.setDoubleBuffered(false);				
				jw.setVisible(true);
				jw.paint(g2d);
				jw.setVisible(false);

				jw.getContentPane().remove(graph);
				// Finally, stream out SVG to the standard output using UTF-8
				// character to byte encoding
				g2d.flush();
				g2d.close();

			}
			currentManager.setDoubleBufferingEnabled (dBufferOn);
		} catch (IOException e) {

			e.printStackTrace();
		}		



	}

	private  void createSVG(JComponent graph, File output) throws FileNotFoundException, SVGGraphics2DIOException{
		UIManager.getLookAndFeelDefaults().put( "ClassLoader",
				getClass().getClassLoader() );
		// Get a DOMImplementation
		DOMImplementation domImpl =
			GenericDOMImplementation.getDOMImplementation();

		// Create an instance of org.w3c.dom.Document
		String svgNS = "http://www.w3.org/2000/svg";

		Document document = domImpl.createDocument(svgNS, "svg", null);
		JWindow jw=new JWindow();
		RepaintManager currentManager =
			RepaintManager.currentManager(jw);
		boolean dBufferOn = currentManager.isDoubleBufferingEnabled();
		currentManager.setDoubleBufferingEnabled(false);

		// Create an instance of the SVG Generator
		SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(document);
		ctx.setEmbeddedFontsOn(true);


		GenericImageHandler ihandler = new CachedImageHandlerBase64Encoder();
		ctx.setGenericImageHandler(ihandler);
		SVGGraphics2D svgGenerator = new SVGGraphics2D(ctx,true);
		jw.getContentPane().add(graph);
		jw.pack();
		if (jw.getSize().width!=0 && jw.getSize().height!=0){

			//svgGenerator.setSVGCanvasSize(new Dimension(2000,2000));      
			graph.setDoubleBuffered(false);

			graph.paint(svgGenerator);
			//SwingSVGPrettyPrint.print(graph,svgGenerator);
			jw.getContentPane().remove(graph);
			// Finally, stream out SVG to the standard output using UTF-8
			// character to byte encoding
			boolean useCSS = true; // we want to use CSS style attribute
			Writer out = new OutputStreamWriter(new FileOutputStream(output));
			svgGenerator.stream(out, useCSS);
		}
		currentManager.setDoubleBufferingEnabled (dBufferOn);

	}

	public static void createPNG(JComponent graph, File output) throws IOException {

		if (graph.getPreferredSize().width!=0 && graph.getPreferredSize().height!=0){
			BufferedImage im = new BufferedImage(graph.getPreferredSize().width,
					graph.getPreferredSize().height,
					BufferedImage.TYPE_3BYTE_BGR);

			//Graphics2D g = im.createGraphics();


			JWindow fakeFrame=new javax.swing.JWindow();
			fakeFrame.getContentPane().setLayout(new java.awt.BorderLayout());

			fakeFrame.getContentPane().add(graph,BorderLayout.CENTER);
			fakeFrame.pack();
			if (fakeFrame.getSize().width!=0 && fakeFrame.getSize().height!=0){
				//fakeFrame.setVisible(true);
				//fakeFrame.paint(g);
				im=createImage(graph,graph.getBounds());

				ImageIO.write(im,"PNG",output);
			}
			//g.dispose();
		}



	}




	public static void diagram2SVG(JComponent graph, File output, String format) {
		try {
			Container container = graph.getParent();
			if (container!=null)
				container.remove(graph);
			if (format.toLowerCase().equals("svg")){
				new Diagram2SVG().createSVG(graph,output);
			} else
				if (format.toLowerCase().equals("eps"))
					createEPS(graph,output);
				else 
					createPNG(graph,output);
			if (container!=null){
				container.add(graph);
				container.repaint();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SVGGraphics2DIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	public static void main(String[] args) {
		Diagram2SVG diagram2SVG1 = new Diagram2SVG();
	}

}