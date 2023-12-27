import TextEditorUI.TextEditor;

public class App {
	public static void main(String[] args) {

		System.out.println("Loggin is only for debugging and catching bugs.");
		System.out.println("Only use in development, will removed when deployed.");
		System.out.println();
		System.out.println("App stated");

		TextEditor textEditor = new TextEditor();

		textEditor.run();
	}
}
