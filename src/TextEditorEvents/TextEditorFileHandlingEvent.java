package TextEditorEvents;

import TextEditorFileHandling.TextEditorFileHandling;

public interface TextEditorFileHandlingEvent {

    void beforeTextChange(TextEditorFileHandling event);

    void afterTextChange(TextEditorFileHandling event);

    void onSave(TextEditorFileHandling event);

    void onFileOpen(TextEditorFileHandling event);

    void onFileChoose(TextEditorFileHandling event);
}
