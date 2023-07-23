package TextEditorClasses;

import java.awt.event.ActionListener;

import javax.swing.KeyStroke;

public class MenuItem {

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

    public ActionListener l;

    public MenuItem(String itemText, KeyStroke keyStroke, ActionListener l) {
        this.itemText = itemText;
        this.keyStroke = keyStroke;
        this.l = l;
    }
}
