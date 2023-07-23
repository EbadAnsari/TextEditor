package TextEditorUI;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

import TextEditorClasses.MenuItem;
import TextEditorEvents.TextEditorFileHandlingEvent;
import TextEditorEvents.TextEditorTextAreaEvent;
import TextEditorFileHandling.TextEditorFileHandling;

public class TextEditor implements ActionListener {

	final TextEditorFileHandling textEditorFileHandling = new TextEditorFileHandling();

	final TextEditorWindow textEditorWindow = new TextEditorWindow("Untitled - Notepad");
	final TextEditorMenuBar textEditorMenuBar = new TextEditorMenuBar();
	final TextEditorAreaPanel textEditorAreaPanel = new TextEditorAreaPanel();
	final TextEditorTextArea textEditorTextArea = new TextEditorTextArea();
	final TextEditorStatusBar textEditorStatusBar = new TextEditorStatusBar();

	final String defaultFileName = "Untitled";

	String generateTitle(String text, boolean isSaved) {
		return (isSaved ? "" : "*") + text + " - Notepad";
	}

	void createWindow() {
		this.textEditorWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				if (!textEditorFileHandling.isSaved()) {
					int result = TextEditorPrompt.fileNotSavedDialog(textEditorWindow);
					if (result == TextEditorPrompt.YES_OPTION) {
						try {
							textEditorFileHandling.save();
						} catch (IOException error) {
							error.printStackTrace();
						}
					} else if (result == TextEditorPrompt.CANCEL_OPTION || result == TextEditorPrompt.CLOSED_OPTION) {
						System.out.println(result);
						return;
					}
				}
				System.exit(0);
			}
		});

	}

	void createFileMenu() {
		textEditorMenuBar.addMenuItems("File",
				new MenuItem("New", KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.VK_CONTROL), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						textEditorTextArea.setText(null);
						textEditorFileHandling.clearTextEditorFileText();
						textEditorFileHandling.setFileNameForDisplay(defaultFileName);
						textEditorWindow.setTitle(generateTitle(defaultFileName, true));
					}
				}));

		textEditorMenuBar.addMenuItems("File",
				new MenuItem("Open", KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.VK_CONTROL), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						try {
							textEditorFileHandling.open();
						} catch (FileNotFoundException e) {
							System.out.println("File not found.");
						}
					}
				}));

		textEditorMenuBar.addMenuItems("File",
				new MenuItem("Save", KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.VK_CONTROL), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						try {
							textEditorFileHandling.save();
						} catch (FileNotFoundException e) {
							System.out.println("File not found, during save.");
						} catch (IOException e) {
							System.out.println("Something went wrong");
						}
					}
				}));
	}

	void createEditMenu() {
		textEditorMenuBar.addMenuItems("Edit",
				new MenuItem("Copy", KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.VK_CONTROL), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						textEditorFileHandling.setTextToClipBoard(textEditorTextArea.getSelectedText());
					}
				}));

		textEditorMenuBar.addMenuItems("Edit",
				new MenuItem("Paste", KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.VK_CONTROL), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						try {
							textEditorTextArea.getDocument().insertString(textEditorTextArea.getCaretPosition(),
									textEditorFileHandling.getTextFromClipBoard(), SimpleAttributeSet.EMPTY);
						} catch (HeadlessException | UnsupportedFlavorException | IOException
								| BadLocationException error) {
							error.printStackTrace();
						}
					}
				}));
	}

	void createFileHandling() {
		textEditorFileHandling.addEvent(new TextEditorFileHandlingEvent() {

			@Override
			public void beforeTextChange(TextEditorFileHandling event) {
			}

			@Override
			public void afterTextChange(TextEditorFileHandling event) {
				textEditorWindow.setTitle(generateTitle(event.getFileNameForDisplay(), false));
			}

			@Override
			public void onSave(TextEditorFileHandling event) {
				textEditorWindow.setTitle(generateTitle(event.getFileNameForDisplay(), true));
			}

			@Override
			public void onFileOpen(TextEditorFileHandling event) {
				textEditorWindow.setTitle(generateTitle(event.getFileNameForDisplay(), true));
			}

			@Override
			public void onFileChoose(TextEditorFileHandling event) {
				textEditorWindow.setTitle(generateTitle(event.getFileNameForDisplay(), true));
				textEditorTextArea.setText(event.getTextEditorFileText());
			}
		});
	}

	void createTextEditorArea() {
		this.textEditorTextArea.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				textEditorFileHandling.setTextEditorFileText(textEditorTextArea.getText());
				textEditorFileHandling.setSaved(false);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				textEditorStatusBar.setLineInfo(textEditorTextArea.getCurrentLineNumber(),
						textEditorTextArea.getCurrentColumnNumber());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				textEditorStatusBar.setLineInfo(textEditorTextArea.getCurrentLineNumber(),
						textEditorTextArea.getCurrentColumnNumber());
			}

		});

		this.textEditorTextArea.addEvent(new TextEditorTextAreaEvent() {

			@Override
			public void onZoom(int zoomLevel) {
				textEditorStatusBar.setZoomLevel(zoomLevel);
			}
		});
	}

	void createTextEditorAreaPanel() {
		this.textEditorAreaPanel.add(this.textEditorTextArea.getElement());
	}

	void addAllComponentInWindow() {
		this.textEditorWindow.setJMenuBar(this.textEditorMenuBar);
		this.textEditorWindow.add(this.textEditorAreaPanel, BorderLayout.CENTER);
		this.textEditorWindow.add(this.textEditorStatusBar, BorderLayout.SOUTH);
	}

	// : Constructor function
	public TextEditor() {

		this.textEditorMenuBar.addMenus("File", "Edit");

		// Adding menus to menubar
		this.createFileMenu();
		this.createEditMenu();

		this.createTextEditorArea();
		this.createTextEditorAreaPanel();
		this.createFileHandling();
		this.createWindow();

		this.addAllComponentInWindow();
	}

	public void visible() {
		textEditorWindow.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
