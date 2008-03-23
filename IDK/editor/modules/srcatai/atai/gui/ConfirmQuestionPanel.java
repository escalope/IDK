package atai.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ConfirmQuestionPanel extends JPanel {
	private int state=0;

	JButton fillIn;
	JButton discard;
	public ConfirmQuestionPanel(){
		fillIn=new JButton("Fill in the question");
		discard=new JButton("Discard question");
		this.add(fillIn);
		fillIn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				remove(fillIn);
				add(discard);
				state=1;
			}		 
		});
		discard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				remove(discard);
				add(fillIn);
				state=0;
			}		 
		});
	}

	public void addConfirmActionListener(ActionListener listener){
		fillIn.addActionListener(listener);
	}
	public void addDiscardActionListener(ActionListener listener){
		discard.addActionListener(listener);
	}

	public boolean isConfirmed(){
		return state==1; 
	}
}
