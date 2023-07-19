package TextEditorUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

public class TextEditor extends TextEditorMenuBar implements ActionListener {

	final JFrame textEditorWindow = new JFrame("Untitled - Notepad");
	final JPanel textAreaPanel = new JPanel();
	final JTextArea textArea = new JTextArea("adasdas\nadasdasdf");
	final TextEditorStatusBar statusBar = new TextEditorStatusBar();
	final TextEditorMenuBar menuBar = new TextEditorMenuBar();

	private void initWindow() {
		textEditorWindow.setSize(400, 400);
		textEditorWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		textEditorWindow.setResizable(true);
		textEditorWindow.setMinimumSize(new Dimension(390, 238));
		textEditorWindow.setLayout(new BorderLayout());

		textEditorWindow.setJMenuBar(menuBar.createMenuBar());
	}

	void initTextAreaPanel() {
		textAreaPanel.setLayout(new BorderLayout());
		textAreaPanel.setBackground(new Color(249, 249, 249));
		textAreaPanel.setBorder(new EmptyBorder(15, 15, 10, 10));
	}

	void initTextArea() {
		textArea.setBackground(new Color(249, 249, 249));
		textArea.setBorder(null);
		textArea.setLineWrap(true);
		changeFontSize(2);

		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// try {
				// System.out.println(textArea.getLineOfOffset(textArea.getCaretPosition()) +
				// 1);
				// } catch (BadLocationException e1) {
				// e1.printStackTrace();
				// }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// try {
				// System.out.println(textArea.getLineOfOffset(textArea.getCaretPosition()) +
				// 1);
				// } catch (BadLocationException e1) {
				// e1.printStackTrace();
				// }
			}

		});
	}

	/**
	 * It is used to change the font size of the of texteditor.
	 * 
	 * @param fontSize Specify the font size to increase with.
	 */
	void changeFontSize(int fontSize) {
		Font font = textArea.getFont();
		textArea.setFont(font.deriveFont((float) (font.getSize() + fontSize)));
	}

	// : Constructor function
	public TextEditor() {

		initWindow();
		initTextAreaPanel();
		initTextArea();

		textAreaPanel.add(textArea, BorderLayout.CENTER);
		textEditorWindow.add(textAreaPanel, BorderLayout.CENTER);
		textEditorWindow.add(statusBar.getStatusBar(), BorderLayout.SOUTH);
	}

	public void visible() {
		textEditorWindow.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
