package TextEditorUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import TextEditorClasses.MenuItem;

public class TextEditorMenuBar extends JMenuBar {

	public ArrayList<JMenu> menuList = new ArrayList<JMenu>();

	public TextEditorMenuBar() {
		this.setPreferredSize(new Dimension(0, 43));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
		this.setBackground(new Color(243, 243, 243));
	}

	public JMenuItem createMenuItem(String menuItemText, KeyStroke keyStroke, ActionListener l) {
		JMenuItem menuItem = new JMenuItem(menuItemText);
		menuItem.setPreferredSize(new Dimension(200, 33));
		menuItem.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		menuItem.setBackground(new Color(243, 243, 243));
		menuItem.setAccelerator(keyStroke);
		menuItem.addActionListener(l);
		return menuItem;
	}

	public void addMenuItems(String menuItemFor, MenuItem menuItem) {
		int currentMenuIndex = -1;

		for (int i = 0; i < menuList.size(); i++) {
			if (menuList.get(i).getText().equals(menuItemFor)) {
				currentMenuIndex = i;
			}
		}
		if (currentMenuIndex == -1)
			return;

		menuList.get(currentMenuIndex).add(createMenuItem(menuItem.getItemText(),
				menuItem.getKeyStroke(), menuItem.l));
	}

	public void addMenus(String... menusArray) {
		for (String menuText : menusArray) {
			menuList.add(new JMenu(menuText));
			menuList.get(menuList.size() - 1)
					.setBorder(BorderFactory.createMatteBorder(0, 15, 1, 15, new Color(230, 230, 230, 0)));
			menuList.get(menuList.size() - 1).setFocusPainted(false);
			menuList.get(menuList.size() - 1).setPreferredSize(new Dimension(60, 10));
			menuList.get(menuList.size() - 1).setForeground(new Color(92, 92, 92));
			// menuList.get(menuList.size() - 1).addMouseListener(new MouseAdapter() {
			// @Override
			// public void mouseEntered(MouseEvent e) {
			// menuList.get(menuList.size() - 1).setForeground(new Color(92, 92, 92, 200));
			// }

			// @Override
			// public void mouseExited(MouseEvent e) {
			// menuList.get(menuList.size() - 1).setForeground(new Color(92, 92, 92));
			// }
			// });
			this.add(menuList.get(menuList.size() - 1));
		}
	}
}
