package ru.bluewhale;

/**
 * Please review the class below and suggest improvements. How would
 * you refactor this class if it would be in a real-life project?
 * There are many problems here, both high-level design mistakes,
 * and low-level implementation bugs. We're interested to see high-level
 * problems first, since they are most critical. The more mistakes
 * you can spot, the better programmer you are.
 */

import java.io.*;

/**
 * This class is thread safe.
 */
public class Parser {
    private File file;

    public void setFile(File f) {
        file = f;
    }

    public File getFile() {
        return file;
    }

    public String getContent() throws IOException {
        InputStream i = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = i.read()) > 0) {
            output += (char) data;
        }

        return output;
    }

    public String getContentWithoutUnicode() {
        String output = "";
        try (InputStream inputStream = new FileInputStream(file)) {
            int data = 0;
            while (true) {
                try {
                    if (!((data = inputStream.read()) > 0)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (data < 0x80) {
                    output += (char) data;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

    public void saveContent(String content) {
        OutputStream o = null;
        try {
            o = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < content.length(); i += 1) {
            try {
                o.write(content.charAt(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
