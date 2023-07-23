package TextEditorEvents;

import TextEditorFileHandling.TextEditorFileHandling;

public interface TextEditorFileHandlingEventListener {

    void beforeTextChange(TextEditorFileHandling event);

    void afterTextChange(TextEditorFileHandling event);

    void onSave(TextEditorFileHandling event);

    void onFileOpen(TextEditorFileHandling event);

    void onFileLoad(TextEditorFileHandling event);
}
