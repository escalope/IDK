/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz
 * 
 * This file is part of the INGENME tool. INGENME is an open source meta-editor
 * which produces customized editors for user-defined modeling languages
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3 of the License
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 **/
package ingenias.editor.events;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import ingenias.editor.cell.NAryEdge;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.VertexView;

public class LocationChange {
	public static NAryEdge getNAryEdgeExtreme(DefaultEdge de,
			ingenias.editor.Model m) {

		DefaultGraphCell target = (DefaultGraphCell) ((DefaultPort) de
				.getTarget()).getParent();
		DefaultGraphCell source = (DefaultGraphCell) ((DefaultPort) de
				.getSource()).getParent();
		NAryEdge nary = null;
		if (NAryEdge.class.isAssignableFrom(target.getClass())) {
			nary = (NAryEdge) target;
		}
		if (NAryEdge.class.isAssignableFrom(source.getClass())) {
			nary = (NAryEdge) source;
		}
		return nary;
	}

	public static DefaultGraphCell getCellExtreme(Object edge,
			ingenias.editor.Model m) {
		DefaultEdge de = (DefaultEdge) edge;

		DefaultGraphCell target = (DefaultGraphCell) ((DefaultPort) m
				.getTarget(de)).getParent();
		DefaultGraphCell source = (DefaultGraphCell) ((DefaultPort) m
				.getSource(de)).getParent();
		DefaultGraphCell nary = null;
		if (NAryEdge.class.isAssignableFrom(target.getClass())) {
			nary = source;
		}
		if (NAryEdge.class.isAssignableFrom(source.getClass())) {
			nary = target;
		}
		return nary;
	}

	public static Point getCenter(Vector<Rectangle> points) {
		Iterator<Rectangle> it = points.iterator();
		// First a generic central point is computed
		double x = 0;
		double y = 0;
		while (it.hasNext()) {
			Rectangle point =  it.next();
			x =  (x + point.getCenterX());
			y =  (y + point.getCenterY());
		}		
		if (points.size()==0)
			return new Point(0,0);

		double x2 = x / points.size();
		double y2= y / points.size();

		// Next, the position is recomputed taking into account
		// the size of the elements.

		it = points.iterator();
		double ncenterX=0;
		double ncenterY=0;
		while (it.hasNext()) {
			Rectangle point =  it.next();
			double x1=point.getCenterX();
			double y1=point.getCenterY();
			double x0=point.getX();
			double y0=point.getY();
			double m=0;
			double n=0;

			double x3=0;
			double y3=0;
			if (x2<x0 || x2> x0+point.getWidth()){
				if (Math.abs(x2-x0)<Math.abs(x2-x0-point.getWidth())){
					x3=x0;
				} else {
					x3=x0+point.getWidth();
				}
					m=(y2-y1)/(x2-x1);
					n=y2-((y2-y1)/(x2-x1))*x2;
					y3=m*x3+n;
				
			} else {
				if (Math.abs(y2-y0)<Math.abs(y2-y0-point.getHeight())){
					y3=y0;
				} else {
					y3=y0+point.getHeight();
				}
				
				if (Math.abs(x2-x1)<=2){
					if (y2>y0){
						y3=y0+point.getHeight();
					} else {
						y3=y0;
					x3=x0+point.getWidth()/2;
					}
				} else {
					m=(y2-y1)/(x2-x1);
					n=y2-((y2-y1)/(x2-x1))*x2;
					x3=(y3-n)/m;
				}
			}

			ncenterX=ncenterX+x3;
			ncenterY=ncenterY+y3;
		}
		return new Point((int)(ncenterX/points.size()),(int)(ncenterY/points.size()));
	}

	public static void centerNAryEdge(JGraph mjg, ingenias.editor.Model m,
			Hashtable changes, NAryEdge nary) {
		if (NAryEdge.class.isAssignableFrom(nary.getClass())){
			Iterator nedges = m.getEdges(m, new Object[] { nary }).iterator();
			Vector<Rectangle> points = new Vector();
			// First a gross estimation of the middle point is obtained
			while (nedges.hasNext()) {
				DefaultEdge edgeline = (DefaultEdge) nedges.next();
				DefaultGraphCell extreme = getCellExtreme(edgeline, m);
				if (GraphConstants.getBounds(m.getAttributes(extreme)) != null) {

					Rectangle rect = GraphConstants.getBounds(
							m.getAttributes(extreme)).getBounds().getBounds();
					points.add(rect);				
				}
			}
			Point p = getCenter(points);
			nedges = m.getEdges(m, new Object[] { nary }).iterator();
			points.clear();

			// Gross estimation is recomputed using the perimeter intersection
			// between
			// bounds on vertexes and the gross estimation middle point
			/*	while (nedges.hasNext()) {
				DefaultEdge edgeline = (DefaultEdge) nedges.next();

				EdgeView ev = (EdgeView) mjg.getGraphLayoutCache().getMapping(
						edgeline, false);

				DefaultGraphCell extreme = getCellExtreme(edgeline, m);
				VertexView extremeView = (VertexView) mjg.getGraphLayoutCache()
				.getMapping(extreme, false);
				if (GraphConstants.getBounds(m.getAttributes(extreme)) != null && extremeView!=null) {

					Rectangle rect = GraphConstants.getBounds(
							m.getAttributes(extreme)).getBounds().getBounds();
					if (rect!=null){
						Point2D p2d = extremeView.getPerimeterPoint(ev, new Point(
								(int) rect.getCenterX(), (int) rect.getCenterY()), p);
						points.add(new java.awt.Point((int) p2d.getX(), (int) p2d
								.getY()));
						System.err.println("agregando2 "+points+":"+rect+":"+p);
					}

				}
			}
			p = getCenter(points);*/
			Map edgem =null;
			if (changes.containsKey(nary))
				edgem=(Map) changes.get(nary);
			else
				edgem = m.getAttributes(nary);

			Rectangle boundsEdge = GraphConstants.getBounds(edgem).getBounds();			
			p.x = p.x - boundsEdge.width / 2;
			p.y = p.y - boundsEdge.height / 2;
			boundsEdge.setLocation(p);			
			GraphConstants.setBounds(edgem, boundsEdge);

			changes.put(nary, edgem);
		}
	}
}
