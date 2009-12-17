package ingenias.editor.rendererxml;

import ingenias.editor.FontConfiguration;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JTextArea;

public class JWrappingTextArea extends JTextArea {
	
	public JWrappingTextArea() {
		//    DashedBorder db=new DashedBorder(Color.black);
		//    this.setBorder(db);
		this.setFont(FontConfiguration.getConfiguration().getStandardFont());
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
	}

	public JWrappingTextArea(String text) {
		//    DashedBorder db=new DashedBorder(Color.black);
		//    this.setBorder(db);
		this.setFont(FontConfiguration.getConfiguration().getStandardFont());
		setText(text);
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
	}

	@Override
	public Dimension getPreferredSize() {
		this.getParent().getSize();
		FontMetrics metrics = this.getFontMetrics(this.getFont());
		Dimension resDimension=new Dimension();
		int width=getParent().getWidth();
		int line=0;
		char[] characters = getText().toCharArray();
		int offset=0;
		int length=0;
		for (int k=0;k<getText().length();k++){			
			int segmentwidth=metrics.charsWidth(characters, offset, length);
			if (segmentwidth<width){
				length++;
			} else {
				offset=k;
				length=0;
				line++;
			}			
		}
		
		resDimension.setSize(width+10,line*metrics.getHeight()+10);
		if (getText().length()==0)
			return new Dimension(0,0);
		else
			return resDimension;
	}



	@Override
	public Dimension getMaximumSize() {
		if (getText().length()==0 )
			return new Dimension(0,0);
		else
			return super.getMaximumSize();
	}

	@Override
	public Dimension getMinimumSize() {
		if (getText().length()==0 )
			return new Dimension(0,0);
		else
			return super.getMinimumSize();
	}

	
	

}
