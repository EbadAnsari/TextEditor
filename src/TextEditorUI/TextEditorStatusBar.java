package TextEditorUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextEditorStatusBar extends JPanel {

	final JLabel textEditorLineInto = new JLabel("Ln 1, Col 1");
	final JLabel textEditorZoomPercentage = new JLabel("100%");
	final JLabel textEditorTemp = new JLabel("Window (CRLF)");
	final JLabel textEditorCharset = new JLabel("UTF8");

	final JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
	final JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 30, 10));

	final String textEditorStatusBarFontName = "Calibri";
	final int textEditorStatusBarFontStyle = Font.BOLD;

	void initStatusBar() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(0, 33));
		this.setBackground(new Color(243, 243, 243));
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));
	}

	void initLineInfo() {
		textEditorLineInto.setForeground(new Color(92, 92, 92));
		Font font = textEditorLineInto.getFont();
		textEditorLineInto.setFont(new Font(textEditorStatusBarFontName, textEditorStatusBarFontStyle, font.getSize()));
	}

	void initZoomPercentage() {
		textEditorZoomPercentage.setForeground(new Color(92, 92, 92));
		Font font = textEditorZoomPercentage.getFont();
		textEditorZoomPercentage
				.setFont(new Font(textEditorStatusBarFontName, textEditorStatusBarFontStyle, font.getSize()));
	}

	void initTemp() {
		textEditorTemp.setForeground(new Color(92, 92, 92));
		Font font = textEditorTemp.getFont();
		textEditorTemp.setFont(new Font(textEditorStatusBarFontName, textEditorStatusBarFontStyle, font.getSize()));
	}

	void initCharset() {
		textEditorCharset.setForeground(new Color(92, 92, 92));
		Font font = textEditorCharset.getFont();
		textEditorCharset.setFont(new Font(textEditorStatusBarFontName, textEditorStatusBarFontStyle, font.getSize()));
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

	void addComponentToLeftPanel() {
		this.leftPanel.setBackground(new Color(243, 243, 243));

		this.leftPanel.add(this.textEditorLineInto);

		this.add(this.leftPanel, BorderLayout.WEST);
	}

	void addComponentToRightPanel() {
		this.rightPanel.setBackground(new Color(243, 243, 243));

		this.rightPanel.add(this.textEditorZoomPercentage);
		this.rightPanel.add(this.textEditorTemp);
		this.rightPanel.add(this.textEditorCharset);

		this.add(this.rightPanel, BorderLayout.EAST);
	}

	public TextEditorStatusBar() {
		initStatusBar();

		initLineInfo();
		initZoomPercentage();
		initTemp();
		initCharset();

		// this.textEditorLineInto

		addComponentToLeftPanel();
		addComponentToRightPanel();
	}
}
