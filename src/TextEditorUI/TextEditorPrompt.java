package TextEditorUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TextEditorPrompt extends JOptionPane {

	public static int fileNotSavedDialog(JFrame parent) {
		return JOptionPane.showConfirmDialog(parent, "Do you want to save Untitled?", "Text Editor",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

}
