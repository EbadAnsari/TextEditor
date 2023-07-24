package TextEditorUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TextEditorWindow extends JFrame {

    public TextEditorWindow(String titleText) {
        this.setTitle(titleText);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setResizable(true);
        this.setMinimumSize(new Dimension(400, 400));
        this.setLayout(new BorderLayout());
    }
}
