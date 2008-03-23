import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class TestFrame extends JFrame {

	public TestFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
	}

	public TestFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public TestFrame(String arg0) throws HeadlessException {
		super(arg0);

		// TODO Auto-generated constructor stub
	}

	public TestFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestFrame tf=new TestFrame();
		
		JLabel l1=new JLabel("hola");
		JLabel l2=new JLabel("que");
		JLabel l3=new JLabel("tal");
		
		tf.getContentPane().setLayout(new FlowLayout());
		tf.getContentPane().add(l1);
		tf.getContentPane().add(l2);
		tf.getContentPane().add(l3);
		tf.pack();
		tf.setVisible(true);
	}

}
