package ingenias.editor.rendererxml;

import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.text.StyledEditorKit;

public class MyEditorPane extends JEditorPane {

	public MyEditorPane() {
		super();
		setEditorKit(new StyledEditorKit());
		StyledEditorKit editor = new StyledEditorKit();		
		setBorder(BorderFactory.createEtchedBorder());
	}

	public MyEditorPane(String arg0, String arg1) {
		super(arg0, arg1);
		setEditorKit(new StyledEditorKit());
		setBorder(BorderFactory.createEtchedBorder());
	}

	public MyEditorPane(String arg0) throws IOException {
		super(arg0);
		setEditorKit(new StyledEditorKit());
		setBorder(BorderFactory.createEtchedBorder());
	}

	public MyEditorPane(URL arg0) throws IOException {
		super(arg0);
		setEditorKit(new StyledEditorKit());
		setBorder(BorderFactory.createEtchedBorder());
	}

}
