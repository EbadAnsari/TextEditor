package TextEditorUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import TextEditorFileHandling.TextEditorFileHandling;

public class TextEditorMenuBar extends TextEditorFileHandling {

	final JMenuBar textEditorMenuBar = new JMenuBar();
	JMenu[] menus = {
			new JMenu("File"),
			new JMenu("Edit"),
	};

	void initFileMenu() {
		menus[0].add(createMenuItem("New",
				KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK)));
		menus[0].add(createMenuItem("Open", KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK)));
		menus[0].add(createMenuItem("Save", KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK)));
		menus[0].add(createMenuItem("New Window",
				KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK)));
	}

	void initMenus() {
		for (JMenu jMenu : menus) {
			jMenu.setBorder(BorderFactory.createMatteBorder(0, 15, 1, 15, new Color(230, 230, 230, 0)));
			jMenu.setFocusPainted(false);
			jMenu.setPreferredSize(new Dimension(60, 10));
			jMenu.setForeground(new Color(92, 92, 92));
			jMenu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					jMenu.setForeground(new Color(92, 92, 92, 200));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					jMenu.setForeground(new Color(92, 92, 92));
				}
			});
		}
		initFileMenu();
	}

	public JMenuBar createMenuBar() {

		initMenus();

		textEditorMenuBar.setPreferredSize(new Dimension(0, 45));
		textEditorMenuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
		textEditorMenuBar.setBackground(new Color(243, 243, 243));

		for (JMenu jMenu : menus) {
			textEditorMenuBar.add(jMenu);
		}

		return textEditorMenuBar;
	}

	// , MouseListener event

	public static JMenuItem createMenuItem(String menuItemText, KeyStroke keyStroke) {
		JMenuItem menuItem = new JMenuItem(menuItemText);
		menuItem.setPreferredSize(new Dimension(200, 33));
		menuItem.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		menuItem.setBackground(new Color(243, 243, 243));

		menuItem.setAccelerator(keyStroke);

		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked");
			}
		});
		return menuItem;
	}
}
