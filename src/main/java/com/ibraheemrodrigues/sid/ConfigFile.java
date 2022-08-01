package com.ibraheemrodrigues.sid;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigFile {
    private final File file;
    public ConfigFile(String pathname, String[] defaultContents) {
        this.file = new File(pathname);
        if (!this.file.isFile()) {
            try {
                this.file.createNewFile();
                FileWriter writer = new FileWriter(this.file);
                for (String line : defaultContents) {
                    writer.write(line + "\n");
                }
                writer.close();
                SIDMain.LOGGER.info("No " + pathname + " file exists. Creating it...");
            } catch (IOException e) {
                SIDMain.LOGGER.warn("Failed to create " + pathname + " !");
            }
        }
    }

    public ConfigFile(String pathname) {
        this(pathname, new String[] {});
    }
    public String[] readAllLines() {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            Scanner reader = new Scanner(this.file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.startsWith("#")) {
                    lines.add(line.replace("\\n", "\n"));
                }
            }
        } catch (FileNotFoundException e) {
            SIDMain.LOGGER.warn("Failed to read " + this.file.getName() + " !");
        }
        String[] out = new String[lines.size()];
        return lines.toArray(out);
    }

    public String readFirstLine() {
        try {
            Scanner reader = new Scanner(this.file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.startsWith("#")) {
                    return line.replace("\\n", "\n");
                }
            }
        } catch (FileNotFoundException e) {
            SIDMain.LOGGER.warn("Failed to read " + this.file.getName() + " !");
        }

        return null;
    }
}
