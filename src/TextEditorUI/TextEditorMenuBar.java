package TextEditorUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import TextEditorClasses.MenuBar.MenuItem;
import TextEditorClasses.MenuBar.Menu;

public class TextEditorMenuBar extends JMenuBar {

	ArrayList<JMenu> menuList = new ArrayList<>();

	public TextEditorMenuBar() {
		this.setPreferredSize(new Dimension(0, 43));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
		this.setBackground(new Color(243, 243, 243));
	}

	public JMenuItem createMenuItem(MenuItem item) {
		JMenuItem menuItem = new JMenuItem(item.getItemText());
		menuItem.setPreferredSize(new Dimension(200, 33));
		menuItem.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		menuItem.setBackground(new Color(243, 243, 243));
		menuItem.setAccelerator(item.getKeyStroke());
		menuItem.setMnemonic(item.getMnemonic());
		menuItem.addActionListener(item.l);
		return menuItem;
	}

	public void addMenuItems(String menuItemFor, MenuItem item) {
		int currentMenuIndex = -1;

		for (int i = 0; i < menuList.size(); i++) {
			if (menuList.get(i).getText().equals(menuItemFor)) {
				currentMenuIndex = i;
			}
		}
		if (currentMenuIndex == -1)
			return;

		menuList.get(currentMenuIndex).add(createMenuItem(item));
	}

	public void addMenus(Menu... menusArray) {
		for (Menu menu : menusArray) {
			final String menuText = menu.getMenuText();
			final int mnemonic = menu.getMnemonic();

			menuList.add(new JMenu(menuText));
			menuList.get(menuList.size() - 1)
					.setBorder(BorderFactory.createMatteBorder(0, 15, 1, 15, new Color(230, 230, 230, 0)));
			menuList.get(menuList.size() - 1).setFocusPainted(false);
			menuList.get(menuList.size() - 1).setPreferredSize(new Dimension(60, 10));
			menuList.get(menuList.size() - 1).setForeground(new Color(92, 92, 92));
			menuList.get(menuList.size() - 1).setMnemonic(mnemonic);
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
