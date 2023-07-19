package TextEditorUI;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

public class TextEditorStatusBar {

	final JPanel textEditorStatusBar = new JPanel();
	final JLabel textEditorLineInto = new JLabel("Line");
	final JLabel textEditorZoomPercentage = new JLabel("100%");
	final JLabel textEditorTemp = new JLabel("Window (CRLF)");
	final JLabel textEditorCharset = new JLabel("UTF - 8");

	void initStatusBar() {
		textEditorStatusBar.setLayout(new GridLayout(1, 4));
		textEditorStatusBar.setPreferredSize(new Dimension(0, 33));
		textEditorStatusBar.setBackground(new Color(243, 243, 243));
		textEditorStatusBar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));
	}

	void initLineInfo() {
		textEditorLineInto.setForeground(new Color(92, 92, 92));
		textEditorLineInto.setBorder(BorderFactory.createEmptyBorder(0, 23, 0, 0));
		Font font = textEditorLineInto.getFont();
		textEditorLineInto.setFont(font.deriveFont((float) (font.getSize() - 2)));
		textEditorStatusBar.add(textEditorLineInto);
	}

	void initZoomPercentage() {
		textEditorZoomPercentage.setForeground(new Color(92, 92, 92));
		textEditorZoomPercentage.setBorder(BorderFactory.createEmptyBorder(0, 23, 0, 0));
		Font font = textEditorZoomPercentage.getFont();
		textEditorZoomPercentage.setFont(font.deriveFont((float) (font.getSize() - 2)));
		textEditorStatusBar.add(textEditorZoomPercentage);
	}

	void initTemp() {
		textEditorTemp.setForeground(new Color(92, 92, 92));
		textEditorTemp.setBorder(BorderFactory.createEmptyBorder(0, 23, 0, 0));
		Font font = textEditorTemp.getFont();
		textEditorTemp.setFont(font.deriveFont((float) (font.getSize() - 2)));
		textEditorStatusBar.add(textEditorTemp);
	}

	void initCharset() {
		textEditorCharset.setForeground(new Color(92, 92, 92));
		textEditorCharset.setBorder(BorderFactory.createEmptyBorder(0, 23, 0, 0));
		Font font = textEditorCharset.getFont();
		textEditorCharset.setFont(font.deriveFont((float) (font.getSize() - 2)));
		textEditorStatusBar.add(textEditorCharset);
	}

	public void setLineInfo(int lineNumber, int columnNumber) {
		textEditorLineInto.setText("Ln " + lineNumber + ", Col " + columnNumber);
	}

	public void setZoomLevel(int zoomLevel) {
		textEditorZoomPercentage.setText("" + zoomLevel + "%");
	}

	public void setCharacterSet(String characterSet) {
		textEditorCharset.setText(characterSet);
	}

	public TextEditorStatusBar() {
		initStatusBar();

		initLineInfo();
		initZoomPercentage();
		initTemp();
		initCharset();
	}

	public JPanel getStatusBar() {
		return textEditorStatusBar;
	}

}
