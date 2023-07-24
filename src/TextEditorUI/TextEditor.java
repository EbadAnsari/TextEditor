package TextEditorUI;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

import TextEditorClasses.MenuBar.Menu;
import TextEditorClasses.MenuBar.MenuItem;
import TextEditorEvents.TextEditorFileHandlingEventListener;
import TextEditorEvents.TextEditorTextAreaEvent;
import TextEditorFileHandling.TextEditorFileHandling;

public class TextEditor implements ActionListener {

	final TextEditorFileHandling textEditorFileHandling = new TextEditorFileHandling();

	final TextEditorWindow textEditorWindow = new TextEditorWindow("Untitled - Notepad");
	final TextEditorMenuBar textEditorMenuBar = new TextEditorMenuBar();
	final TextEditorAreaPanel textEditorAreaPanel = new TextEditorAreaPanel();
	final TextEditorTextArea textEditorTextArea = new TextEditorTextArea();
	final TextEditorStatusBar textEditorStatusBar = new TextEditorStatusBar();

	String defaultFileName = "Untitled";

	String generateTitle(String text, boolean isSaved) {
		return (isSaved ? "" : "*") + text + " - Notepad";
	}

	void createWindow() {
		this.textEditorWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				if (!textEditorFileHandling.isSaved()) {
					int result = TextEditorPrompt.fileNotSavedDialog(textEditorWindow);
					if (result == JOptionPane.YES_OPTION) {
						try {
							textEditorFileHandling.save();
						} catch (IOException error) {
							System.out.println("File not saved.");
						}
					} else if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
						return;
					}
				}
				System.exit(0);
			}
		});

	}

	void createFileMenu() {
		textEditorMenuBar.addMenuItems("File",
				new MenuItem("New", KeyEvent.VK_N, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK),
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent event) {
								textEditorTextArea.setText(null);
								textEditorFileHandling.clearTextEditorFileText();
								textEditorFileHandling.setFileNameForDisplay(defaultFileName);
								textEditorWindow.setTitle(generateTitle(defaultFileName, true));
							}
						}));

		textEditorMenuBar.addMenuItems("File",
				new MenuItem("Open", KeyEvent.VK_O, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK),
						new ActionListener() {
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
				new MenuItem("Save", KeyEvent.VK_S, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK),
						new ActionListener() {
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

		textEditorMenuBar.addMenuItems("File",
				new MenuItem("Print", KeyEvent.VK_P, KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK),
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent event) {
								PrinterJob printerJob = PrinterJob.getPrinterJob();
								if (printerJob.printDialog()) {
									try {
										printerJob.print();
									} catch (PrinterException e) {
										System.out.println("Error in printing.");
									}
								}
							}
						}));
	}

	void createEditMenu() {
		textEditorMenuBar.addMenuItems("Edit",
				new MenuItem("Cut", KeyEvent.VK_X, KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK),
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								int selectionStart = textEditorTextArea.getSelectionStart();
								int selectionEnd = textEditorTextArea.getSelectionEnd();
								int selectedLength = selectionEnd - selectionStart;

								try {
									String cuttedText = textEditorTextArea.getText(selectionStart,
											selectedLength);

									if (cuttedText.length() > 0) {
										textEditorFileHandling.setTextToClipBoard(cuttedText);
									}

									textEditorTextArea.setText(new StringBuilder(textEditorTextArea.getText())
											.delete(selectionStart, selectionEnd).toString());
								} catch (BadLocationException e1) {
									System.out.println("Unable to cut.");
								}
							}
						}));

		textEditorMenuBar.addMenuItems("Edit",
				new MenuItem("Copy", KeyEvent.VK_C, KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK),
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String selectedText = textEditorTextArea.getSelectedText();
								if (selectedText.length() > 0) {
									textEditorFileHandling.setTextToClipBoard(selectedText);
								}
							}
						}));

		textEditorMenuBar.addMenuItems("Edit",
				new MenuItem("Paste", KeyEvent.VK_P, KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK),
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent event) {
								try {
									System.out.println(textEditorFileHandling.getTextFromClipBoard());
									textEditorTextArea.getDocument().insertString(textEditorTextArea.getCaretPosition(),
											textEditorFileHandling.getTextFromClipBoard(), SimpleAttributeSet.EMPTY);
								} catch (HeadlessException | UnsupportedFlavorException | IOException
										| BadLocationException error) {
									System.out.println(error.getMessage());
								}
							}
						}));
	}

	void createZoomMenu() {
		textEditorMenuBar.addMenuItems("Zoom",
				new MenuItem("Zoom in", KeyEvent.VK_I,
						KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK),
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent event) {
								textEditorTextArea.incrementFontSize();
							}
						}));

		textEditorMenuBar.addMenuItems("Zoom",
				new MenuItem("Zoom out", KeyEvent.VK_O,
						KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK),
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent event) {
								textEditorTextArea.decrementFontSize();
							}
						}));

		textEditorMenuBar.addMenuItems("Zoom",
				new MenuItem("100%", KeyEvent.VK_0,
						KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK),
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent event) {
								textEditorTextArea.defaultFontSize();
							}
						}));
	}

	void createFileHandling() {
		textEditorFileHandling.addEvent(new TextEditorFileHandlingEventListener() {

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
			public void onFileLoad(TextEditorFileHandling event) {
				textEditorWindow.setTitle(generateTitle(event.getFileNameForDisplay(), true));
				textEditorTextArea.setText(event.getTextEditorFileText());
				textEditorStatusBar.setCharacterSet(textEditorFileHandling.getCharSet());
			}
		});
	}

	void createTextEditorArea() {
		this.textEditorTextArea.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (!(e.isAltDown() || e.isControlDown())) {
					textEditorFileHandling.setTextEditorFileText(textEditorTextArea.getText());
					textEditorFileHandling.setSaved(false);
				}
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

		// this.textEditorMenuBar.addMenus("File", "Edit");
		this.textEditorMenuBar.addMenus(new Menu("File", KeyEvent.VK_F), new Menu("Edit", KeyEvent.VK_E),
				new Menu("Zoom", KeyEvent.VK_Z));

		// Adding menus to menubar
		this.createFileMenu();
		this.createEditMenu();
		this.createZoomMenu();

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
