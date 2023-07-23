package TextEditorUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TextEditorAreaPanel extends JPanel {
    public TextEditorAreaPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(249, 249, 249));
        // setBackground(Color.MAGENTA);
        setBorder(new EmptyBorder(15, 15, 10, 10));
    }
}
