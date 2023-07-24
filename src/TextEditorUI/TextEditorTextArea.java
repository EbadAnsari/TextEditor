package TextEditorUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import TextEditorEvents.TextEditorTextAreaEvent;

public class TextEditorTextArea extends JTextArea {

	JScrollPane textAreaScrollBar = new JScrollPane(this);
	boolean isDefaultFontSize = true;
	int fontSizeInPercentage = 100;
	int baseFontSize = 16;

	private transient TextEditorTextAreaEvent event = new TextEditorTextAreaEvent() {
		@Override
		public void onZoom(int zoomLevel) {
		}
	};;

	private int currentLineNumber = 1;

	/**
	 * @return the currentLineNumber
	 */
	public int getCurrentLineNumber() {
		return currentLineNumber;
	}

	/**
	 * @param currentLineNumber the currentLineNumber to set
	 * @throws BadLocationException
	 */
	public void setCurrentLineNumber(int totalOffset) throws BadLocationException {
		currentLineNumber = this.getLineOfOffset(totalOffset) + 1;
	}

	private int currentColumnNumber = 1;

	/**
	 * @return the currentColumnNumber
	 */
	public int getCurrentColumnNumber() {
		return currentColumnNumber;
	}

	/**
	 * @param currentColumnNumber the currentColumnNumber to set
	 * @throws BadLocationException
	 */
	public void setCurrentColumnNumber(int lineNumber) throws BadLocationException {
		currentColumnNumber = this.getCaretPosition() - this.getLineStartOffset(lineNumber) + 1;

	}

	int changeFontSize(int fontSizeToIncrement) {
		Font font = this.getFont();
		int fontSize = font.getSize() + fontSizeToIncrement;

		if (fontSize < 2 || fontSize > 96)
			return fontSize - fontSizeToIncrement;

		this.event.onZoom(fontSize * 100 / baseFontSize);
		this.setFont(new Font("Calibri", 0, fontSize));
		return fontSize;
	}

	public void defaultFontSize() {
		this.setFont(new Font("Calibri", 0, baseFontSize));
		this.event.onZoom(baseFontSize * 100 / baseFontSize);
		isDefaultFontSize = true;
		fontSizeInPercentage = 100;
	}

	public void incrementFontSize() {
		int currentFontSize = changeFontSize(1);
		fontSizeInPercentage = currentFontSize * 100 / baseFontSize;
	}

	public void decrementFontSize() {
		int currentFontSize = changeFontSize(-1);
		fontSizeInPercentage = currentFontSize * 100 / baseFontSize;
	}

	void initTextArea() {
		this.setBackground(new Color(249, 249, 249));
		this.setBorder(null);
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		this.defaultFontSize();

		this.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent event) {
				try {
					setCurrentLineNumber(event.getOffset());
					setCurrentColumnNumber(currentLineNumber - 1);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent event) {
				try {
					setCurrentLineNumber(event.getOffset());
					setCurrentColumnNumber(currentLineNumber - 1);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void changedUpdate(DocumentEvent event) {
			}
		});

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent event) {
				if (event.isControlDown() && event.getKeyChar() == '=') {
					incrementFontSize();
				} else if (event.isControlDown() && event.getKeyChar() == '-') {
					decrementFontSize();
				} else if (event.isControlDown() && event.getKeyChar() == '0') {
					defaultFontSize();
				}

				try {
					setCurrentLineNumber(((JTextArea) event.getSource()).getCaretPosition());
					setCurrentColumnNumber(getCurrentLineNumber() - 1);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void keyReleased(KeyEvent event) {
				try {
					setCurrentLineNumber(((JTextArea) event.getSource()).getCaretPosition());
					setCurrentColumnNumber(getCurrentLineNumber() - 1);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
	}

	void initTextAreaPanel() {
		textAreaScrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		textAreaScrollBar.setBorder(null);
	}

	public JScrollPane getElement() {
		return this.textAreaScrollBar;
	}

	public TextEditorTextArea() {
		initTextArea();
		initTextAreaPanel();
	}

	public void addEvent(TextEditorTextAreaEvent event) {
		this.event = event;
	}

	public void removeEvent() {
		this.event = new TextEditorTextAreaEvent() {
			@Override
			public void onZoom(int zoomLevel) {
			}
		};
	}
}
