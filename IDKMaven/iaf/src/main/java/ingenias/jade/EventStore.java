/*
Copyright (C) 2012 Jorge Gomez Sanz

This file is part of INGENIAS Agent Framework, an agent infrastructure linked
to the INGENIAS Development Kit, and availabe at http://ingenias.sourceforge.net. 

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
package ingenias.jade;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class EventStore extends ApplicationFrame implements EventListener {

	JFrame seriesDefinition=new JFrame();
	TimeSeriesCollection dataset = new TimeSeriesCollection();
	Hashtable<String, TimeSeries> series=new Hashtable<String, TimeSeries>();
	DefaultListModel dlm=new DefaultListModel();
	DefaultListModel targetModel=new DefaultListModel();
	JList targetList=null;
	private JList list;
	

	public EventStore(String title) {
		super(title);
		ChartPanel chartPanel = (ChartPanel) createDemoPanel();
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);

		JButton addList=new JButton("add series");

		JButton removeList=new JButton("remove series");

		list=new JList(dlm);
		targetList=new JList(targetModel);
		seriesDefinition.getContentPane().setLayout(new GridLayout(2,1));
		seriesDefinition.getContentPane().add(list);
		seriesDefinition.getContentPane().add(addList);
		
		seriesDefinition.getContentPane().add(targetList);
		addList.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Runnable run=new Runnable(){
					public void run(){
						if (list.getSelectedIndex()>=0){
							TimeSeries ts=series.get(list.getSelectedValue());   
							dataset.addSeries(ts);
							targetModel.add(0,list.getSelectedValue());
							dlm.remove(list.getSelectedIndex());
						}	
					}
				};

				SwingUtilities.invokeLater(run);
				
			}
		});
		
		removeList.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Runnable run=new Runnable(){
					public void run(){
						
						if (targetList.getSelectedIndex()>=0){
							TimeSeries ts=series.get(targetList.getSelectedValue());   
							dataset.removeSeries(ts);
							dlm.add(0,targetList.getSelectedValue());
							targetModel.remove(targetList.getSelectedIndex());
							System.err.println("Quitado");
						}		
					}
				};
				SwingUtilities.invokeLater(run);
			}
		});
		seriesDefinition.getContentPane().add(removeList);
		
		seriesDefinition.setTitle("Series selection");
		seriesDefinition.pack();
		seriesDefinition.setVisible(true);
	}


	public void addEvent(final String evid, final long timestamp, final Integer nvalue){
		Runnable run=new Runnable(){
			public void run(){
				Integer value=nvalue;
				TimeSeries current=null;
				if (series.containsKey(evid)){
					current=series.get(evid);
				} else {
					current =new TimeSeries(evid,Millisecond.class);
					series.put(evid,current);
					dlm.add(0,evid);
					list.invalidate();
					System.err.println("added "+evid);
					seriesDefinition.invalidate();
					seriesDefinition.pack();
				}
				if (current.getItemCount()>0){
					value=value+current.getValue(current.getItemCount()-1).intValue();
					}
				current.add(new Millisecond(),value);
				//current.add(new TimeSeriesDataItem())
			}
		};
		SwingUtilities.invokeLater(run);
		
	}

	@Override
	public void abortedTask(String agentid, String agentType, String taskid,
			String taskType, String[] missingInputType) {
		addEvent(agentid+":"+agentType+",aborted "+taskType,new Date().getTime(),1);
		addEvent(agentid+":"+agentType+",aborted tasks",new Date().getTime(),1);
		addEvent(taskType+", aborted "+taskType,new Date().getTime(),1);
		addEvent("Aborted tasks",new Date().getTime(),1);
	}

	@Override
	public void addedNewEntityToConversation(String agentid, String agentType,
			String entityId, String entityType, String convId,
			String interactionType) {
		addEvent(agentid+":"+agentType+",added "+entityType,new Date().getTime(),1);
		addEvent(agentid+":"+agentType+",added entities",new Date().getTime(),1);
		addEvent("Added entities",new Date().getTime(),1);
		addEvent("Added entity "+entityType,new Date().getTime(),1);
			
	}

	@Override
	public void addedNewEntityToMS(String agentid, String agentType,
			String entityId, String entityType) {

		addEvent(agentid+":"+agentType+",added "+entityType,new Date().getTime(),1);
		addEvent(agentid+":"+agentType+",added entities",new Date().getTime(),1);
		addEvent("Added entities",new Date().getTime(),1);
		addEvent("Added entity "+entityType,new Date().getTime(),1);
	}

	@Override
	public void executedTask(String agentid, String agentType, String taskid,
			String taskType, String[] inputEntityIds, String[] inputEntityType,
			String[] outputEntityIds, String[] outputEntityType) {
		addEvent(agentid+":"+agentType+",executed "+taskType,new Date().getTime(),1);
		addEvent(agentid+":"+agentType+",executed tasks",new Date().getTime(),1);
		addEvent("Executed tasks",new Date().getTime(),1);
		addEvent("Executed task "+taskType,new Date().getTime(),1);
	}

	@Override
	public void removeEntityFromConversation(String agentid, String agentType,
			String entityId, String entityType, String convId,
			String interactionType) {
		addEvent(agentid+":"+agentType+",removed "+entityType,new Date().getTime(),1);
		addEvent(agentid+":"+agentType+",removed entities",new Date().getTime(),1);
		addEvent("Removed entities",new Date().getTime(),1);
		addEvent("Removed entity "+entityType,new Date().getTime(),1);	

	}

	@Override
	public void removeEntityFromMS(String agentid, String agentType,
			String entityId, String entityType) {
		addEvent(agentid+":"+agentType+",removed "+entityType,new Date().getTime(),1);
		addEvent(agentid+":"+agentType+",removed entities",new Date().getTime(),1);
		addEvent("Removed entities",new Date().getTime(),1);
		addEvent("Removed entity "+entityType,new Date().getTime(),1);	
	}

	@Override
	public void scheduledTask(String agentid, String agentType, String taskid,
			String taskType, String[] inputEntityIds, String[] inputEntityType) {
		addEvent(agentid+":"+agentType+",scheduled "+taskType,new Date().getTime(),1);
		addEvent(agentid+":"+agentType+",scheduled tasks",new Date().getTime(),1);
		addEvent("Scheduled tasks",new Date().getTime(),1);
		addEvent("Scheduled task "+taskType,new Date().getTime(),1);
	}

	/**
	 * Creates a chart.
	 *
	 * @param dataset  a dataset.
	 *
	 * @return A chart.
	 */
	private static JFreeChart createChart(XYDataset dataset) {

		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				"IAF charts",  // title
				"Simulation time (millis)",             // x-axis label
				"Amount",   // y-axis label
				dataset,            // data
				true,               // create legend?
				true,               // generate tooltips?
				false               // generate URLs?
		);

		chart.setBackgroundPaint(Color.white);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		plot.setForegroundAlpha(0.6F);
		XYItemRenderer r = plot.getRenderer();
		if (r instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
			renderer.setBaseShapesVisible(true);
			renderer.setBaseShapesFilled(true);
			renderer.setDrawSeriesLineAsPath(true);
		}

		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setAutoRange(true);
		
		//axis.setDateFormatOverride(new SimpleDateFormat("S"));

		return chart;

	}

	/**
	 * Creates a panel for the demo (used by SuperDemo.java).
	 *
	 * @return A panel.
	 */
	public  JPanel createDemoPanel() {
		JFreeChart chart = createChart(dataset);
		ChartPanel panel = new ChartPanel(chart);
		panel.setFillZoomRectangle(true);
		panel.setMouseWheelEnabled(true);
		return panel;
	}

	/**
	 * Starting point for the demonstration application.
	 *
	 * @param args  ignored.
	 */
	public static void main(String[] args) {

		final EventStore demo = new EventStore(
		"Time Series Chart Demo 1");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

		Thread t=new Thread(){
			public void run(){
				for (int k=0;k<10;k++){
					demo.abortedTask("ag1", "agT", "tid1","TID", null);
					try {
						Thread.currentThread().sleep((long)(2000*Math.random()));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int k=0;k<10;k++){
					demo.abortedTask("ag2", "agT", "tid1","TID", null);
					try {
						Thread.currentThread().sleep((long)(1000*Math.random()));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		};
		t.start();
	}


}
