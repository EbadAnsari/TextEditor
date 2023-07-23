package TextEditorUI;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

public class TextEditorStatusBar extends JPanel {

	final JLabel textEditorLineInto = new JLabel("Ln 1, Col 1");
	final JLabel textEditorZoomPercentage = new JLabel("100%");
	final JLabel textEditorTemp = new JLabel("Window (CRLF)");
	final JLabel textEditorCharset = new JLabel("UTF8");

	final String textEditorStatusBarFontName = "Calibri";
	final int textEditorStatusBarFontStyle = Font.BOLD;

	void initStatusBar() {
		this.setLayout(new GridLayout(1, 4));
		this.setPreferredSize(new Dimension(0, 33));
		this.setBackground(new Color(243, 243, 243));
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));
	}

	void initLineInfo() {
		textEditorLineInto.setForeground(new Color(92, 92, 92));
		textEditorLineInto.setBorder(BorderFactory.createEmptyBorder(0, 23, 0, 0));
		Font font = textEditorLineInto.getFont();
		textEditorLineInto.setFont(new Font(textEditorStatusBarFontName, textEditorStatusBarFontStyle, font.getSize()));
		this.add(textEditorLineInto);
	}

	void initZoomPercentage() {
		textEditorZoomPercentage.setForeground(new Color(92, 92, 92));
		textEditorZoomPercentage.setBorder(BorderFactory.createEmptyBorder(0, 23, 0, 0));
		Font font = textEditorZoomPercentage.getFont();
		textEditorZoomPercentage
				.setFont(new Font(textEditorStatusBarFontName, textEditorStatusBarFontStyle, font.getSize()));
		this.add(textEditorZoomPercentage);
	}

	void initTemp() {
		textEditorTemp.setForeground(new Color(92, 92, 92));
		textEditorTemp.setBorder(BorderFactory.createEmptyBorder(0, 23, 0, 0));
		Font font = textEditorTemp.getFont();
		textEditorTemp.setFont(new Font(textEditorStatusBarFontName, textEditorStatusBarFontStyle, font.getSize()));
		this.add(textEditorTemp);
	}

	void initCharset() {
		textEditorCharset.setForeground(new Color(92, 92, 92));
		textEditorCharset.setBorder(BorderFactory.createEmptyBorder(0, 23, 0, 0));
		Font font = textEditorCharset.getFont();
		textEditorCharset.setFont(new Font(textEditorStatusBarFontName, textEditorStatusBarFontStyle, font.getSize()));
		this.add(textEditorCharset);
	}

	public void setLineInfo(int lineNumber, int columnNumber) {
		textEditorLineInto.setText("Ln " + lineNumber + ", Col " + columnNumber);
	}

	public void setZoomLevel(int zoomPercentage) {
		textEditorZoomPercentage.setText("" + zoomPercentage + "%");
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
}
