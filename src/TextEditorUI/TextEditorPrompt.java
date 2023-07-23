package TextEditorUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TextEditorPrompt extends JOptionPane {

	public static int fileNotSavedDialog(JFrame parent) {
		return JOptionPane.showConfirmDialog(parent, "Do you want to save Untitled?", "Text Editor",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

}
