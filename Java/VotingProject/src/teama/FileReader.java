package teama;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class FileReader {
    // temporary quickfix
    public static String fileName;

    /**
     * Get all the lines from each CSV file
     * in a directory.
     *
     * @return A line-by-line list of the file contents.
     */
    public ArrayList<String> readDirectory(File directory) {
        ArrayList<String> contents = new ArrayList<>();
        File[] files = directory.listFiles();
        for (File file : files) {
            fileName = file.getAbsolutePath();

            ArrayList<String> fileLines = readFile(file);
            if (fileLines != null) {
                contents.addAll(fileLines);
            }
        }

        return contents;
    }

    /**
     * Read a file and return its contents, line by line,
     * in an array list.
     *
     * @return A list with the files contents.
     */
    public ArrayList<String> readFile(File file) {
        ArrayList<String> lines = new ArrayList<>();
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (in.hasNextLine()) {
            lines.add(in.nextLine());
        }
        return lines;
    }
}
