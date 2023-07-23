package TextEditorClasses;

import java.awt.event.ActionListener;

import javax.swing.KeyStroke;

public class MenuBar {
    public static class MenuItem {

        String itemText = "";

        /**
         * @return the itemString
         */
        public String getItemText() {
            return itemText;
        }

        KeyStroke keyStroke = null;

        /**
         * @return the keyStroke
         */
        public KeyStroke getKeyStroke() {
            return keyStroke;
        }

        int mnemonic;

        /**
         * @return the mnemonic
         */
        public int getMnemonic() {
            return mnemonic;
        }

        public ActionListener l;

        public MenuItem(String itemText, int mnemonic, KeyStroke keyStroke, ActionListener l) {
            this.itemText = itemText;
            this.mnemonic = mnemonic;
            this.keyStroke = keyStroke;
            this.l = l;
        }
    }

    public static class Menu {

        String menuText = "";

        /**
         * @return the menuText
         */
        public String getMenuText() {
            return menuText;
        }

        int mnemonic;

        /**
         * @return the mnemonic
         */
        public int getMnemonic() {
            return mnemonic;
        }

        public Menu(String menuText, int mnemonic) {
            this.menuText = menuText;
            this.mnemonic = mnemonic;
        }
    }
}