# Welcome to TextEditor

-   This TextEditor is build in java using it's framework like `swing` & `awt`
-   It provide features like:
    -   Editing text in file.
    -   Save text into file.
    -   Open existing file or create new one.
    -   Provide facility of printing the document.
    -   It gives the information about the type of encoding applied on the document.
    -   Can load file upto 20 MB.

## How to run.

#### Run the application [`TextEditor.jar`](TextEditor.jar) directly on your PC without compiling the code.

<hr />

### Or run the following commands in the terminal.

First current working directory to `src` directory:

```powershell
cd src
```

Then run the following command to delete all the `*.class` files and compile the `*.java` file to `*.class` file and run the class file.

```powershell
cls;del ./**/*.class;del ./*.class;javac App.java; if ($?) { java App; }
```
