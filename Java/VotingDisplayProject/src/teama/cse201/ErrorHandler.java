package teama.cse201;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ErrorHandler {
    public File errorFile;
    public Set<String> errors;

    public ErrorHandler() {
        this.errorFile = null;
        this.errors = new HashSet<>();
    }

    public void reportIllegalVotingData(String countyName) {
        reportError("ILLEGAL VOTING DATA for county: " + countyName);
    }

    public void reportInvalidRecord(int lineNumber) {
        reportError("INVALID VOTING RECORD on line " + lineNumber + " in " + FileReader.fileName);
    }

    public void reportError(String errorString) {
        if (errorFile == null) {
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String fileName = "errorlog-" + timeStamp + ".txt";
            errorFile = new File(fileName);
            try {
                errorFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!errors.contains(errorString)) {
            errors.add(errorString);
            try {
                FileWriter fw = new FileWriter(errorFile, true);
                fw.write(errorString + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        EventBus.fire("errorsFound", errorFile);
    }
}
