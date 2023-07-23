
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class Test {

	static String getTextFromClipBoard() throws HeadlessException, UnsupportedFlavorException, IOException {
		return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
	}

	static void setTextToClipBoard(String textToClipBoard) {
	}

	public static void main(String[] args) throws HeadlessException, UnsupportedFlavorException, IOException {
		// Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
		System.out.println(getTextFromClipBoard());
	}
}