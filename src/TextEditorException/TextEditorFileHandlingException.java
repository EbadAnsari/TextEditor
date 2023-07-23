package TextEditorException;

public class TextEditorFileHandlingException {

    public static class UnknownFileExtensioException extends Exception {
        public UnknownFileExtensioException(String stringCause) {
            super(stringCause);
        }
    }

}
