package TextEditorFileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

import java.awt.Frame;
import java.awt.FileDialog;

public class TextEditorFileHandling {

	private StringBuilder textEditorFileText = new StringBuilder();
	private boolean saved = false;

	protected File openFileDialog() {
		FileDialog openDialogFileChooser = new FileDialog(new Frame("Open file"), "Open File", FileDialog.LOAD);
		openDialogFileChooser.setVisible(true);
		return new File(openDialogFileChooser.getDirectory() + openDialogFileChooser.getFile());
	}

	protected void openFile() {
		Scanner fileReadScanner;
		System.out.println("File");
		try {
			fileReadScanner = new Scanner(openFileDialog());
			clearTextEditorFileText();
			while (fileReadScanner.hasNextLine()) {
				appendTextEditorFileText(fileReadScanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void saveFile() {
		File fileReader = openFileDialog();
		try {
			fileReader.createNewFile();

			try (FileWriter fileWriter = new FileWriter(fileReader)) {
				fileWriter.write(textEditorFileText.toString());
				fileWriter.close();
				saved = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void clearTextEditorFileText() {
		textEditorFileText = new StringBuilder();
	}

	protected void appendTextEditorFileText(String text) {
		textEditorFileText.append(text);
		saved = false;
	}

	protected void setTextEditorFileText(String text) {
		textEditorFileText = new StringBuilder(text);
		saved = false;
	}

	protected StringBuilder getTextEditorFileText() {
		return textEditorFileText;
	}

	protected boolean isSaved() {
		return saved;
	}

}
