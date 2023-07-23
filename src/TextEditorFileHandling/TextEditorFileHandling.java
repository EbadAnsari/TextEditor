package TextEditorFileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import java.util.Scanner;

import java.awt.Frame;
import java.awt.FileDialog;

import TextEditorEvents.TextEditorFileHandlingEvent;
import TextEditorException.TextEditorFileHandlingException;

public class TextEditorFileHandling {

	private StringBuilder textEditorFileText = new StringBuilder();
	String fileNameForDisplay = "Untitled";
	String fileNameWithFullPath = "";
	boolean isSaved = true;

	/**
	 * @param isSaved the isSaved to set
	 */
	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}

	/**
	 * @return the isSaved
	 */
	public boolean isSaved() {
		return isSaved;
	}

	FilenameFilter textFileFilter = new FilenameFilter() {

		@Override
		public boolean accept(File dir, String name) {
			return name.toLowerCase().endsWith(".txt");
		}
	};

	String extension(String file) throws TextEditorFileHandlingException {
		int index = file.lastIndexOf(".");
		if (index > 0) {
			return file.substring(index + 1);
		}
		throw new TextEditorFileHandlingException("File name does not contain extension.");
	}

	long sizeInMegaBytes(File file) {
		return file.length() / (1024 * 1024);
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileNameForDisplay(String fileName) {
		this.fileNameForDisplay = fileName;
	}

	/**
	 * @return the fileName
	 */
	public String getFileNameForDisplay() {
		return fileNameForDisplay;
	}

	TextEditorFileHandlingEvent event = new TextEditorFileHandlingEvent() {

		@Override
		public void beforeTextChange(TextEditorFileHandling event) {
			// Method is only for initialization of the `event` object.
		}

		@Override
		public void afterTextChange(TextEditorFileHandling event) {
			isSaved = false;
			// Method is only for initialization of the `event` object.
		}

		@Override
		public void onSave(TextEditorFileHandling event) {
			isSaved = true;
			// Method is only for initialization of the `event` object.
		}

		@Override
		public void onFileOpen(TextEditorFileHandling event) {
			isSaved = true;
			// Method is only for initialization of the `event` object.
		}

		@Override
		public void onFileChoose(TextEditorFileHandling event) {
			isSaved = true;
			// Method is only for initialization of the `event` object.
		}
	};

	File saveFileDialog(String titleText, String fileName) {
		FileDialog saveDialogFileChooser = new FileDialog(new Frame(titleText), titleText, FileDialog.SAVE);
		saveDialogFileChooser.setFile(fileName);
		saveDialogFileChooser.setVisible(true);
		return saveDialogFileChooser.getFile() != null
				? new File(saveDialogFileChooser.getDirectory() + saveDialogFileChooser.getFile())
				: null;
	}

	File openFileDialog() {
		FileDialog openDialogFileChooser = new FileDialog(new Frame("Open file"), "Open File", FileDialog.LOAD);
		openDialogFileChooser.setVisible(true);
		return openDialogFileChooser.getFile() != null
				? new File(openDialogFileChooser.getDirectory() + openDialogFileChooser.getFile())
				: null;
	}

	void writeInFile(File fileReader) throws IOException {
		FileWriter fileWriter = new FileWriter(fileReader);
		fileWriter.write(textEditorFileText.toString());
		fileWriter.close();
		this.event.onSave(this);
	}

	public void open() throws FileNotFoundException {
		File fileReader = openFileDialog();

		if (sizeInMegaBytes(fileReader) > 20) {
			return;
		}

		if (fileReader == null)
			throw new FileNotFoundException();

		Scanner fileReadScanner;
		try {
			fileReadScanner = new Scanner(fileReader);
			clearTextEditorFileText();
			while (fileReadScanner.hasNextLine()) {
				appendTextEditorFileText(fileReadScanner.nextLine());
				this.fileNameWithFullPath = fileReader.getAbsolutePath();
				this.fileNameForDisplay = fileReader.getName();
			}
			this.event.onFileChoose(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void save() throws IOException {
		if (this.textEditorFileText.length() == 0)
			return;

		File fileReader;

		if ("Untitled".equals(this.fileNameForDisplay)) {
			fileReader = saveFileDialog("Save", fileNameForDisplay);
			if (fileReader == null)
				throw new FileNotFoundException();
			fileReader.createNewFile();
		} else {
			fileReader = new File(this.fileNameWithFullPath);
		}

		this.writeInFile(fileReader);
	}

	public void addEvent(TextEditorFileHandlingEvent event) {
		this.event = event;
	}

	public void removeEvent() {
		this.event = new TextEditorFileHandlingEvent() {
			@Override
			public void beforeTextChange(TextEditorFileHandling event) {
				// Method is only for initialization of the `event` object.
			}

			@Override
			public void afterTextChange(TextEditorFileHandling event) {
				// Method is only for initialization of the `event` object.
			}

			@Override
			public void onSave(TextEditorFileHandling event) {
				// Method is only for initialization of the `event` object.
			}

			@Override
			public void onFileOpen(TextEditorFileHandling event) {
				// Method is only for initialization of the `event` object.
			}

			@Override
			public void onFileChoose(TextEditorFileHandling event) {
				// Method is only for initialization of the `event` object.
			}
		};
	}

	public void clearTextEditorFileText() {
		this.event.beforeTextChange(this);
		textEditorFileText = new StringBuilder();
		this.event.afterTextChange(this);
	}

	public void appendTextEditorFileText(String text) {
		this.event.beforeTextChange(this);
		textEditorFileText.append(text);
		this.event.afterTextChange(this);
	}

	public void setTextEditorFileText(String text) {
		this.event.beforeTextChange(this);
		textEditorFileText = new StringBuilder(text);
		this.event.afterTextChange(this);
	}

	public String getTextEditorFileText() {
		return textEditorFileText.toString();
	}
}
