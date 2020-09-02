package com.company.Lesson_4.task2.client;

import java.io.*;

public class ClientHistory {
    private File file;
    private BufferedWriter writer;
    private int limitedHistoryMassage = 10;
    private int count = 0;

    public ClientHistory(String name) throws IOException {
        file = new File(String.format("%s_history.txt", name)).getAbsoluteFile();
        writer = new BufferedWriter(new FileWriter(file, true));
    }

    public void writeFile(String message) throws IOException {
        writer.append(message);
        writer.flush();
    }

    public String getHistory() throws IOException {
        long startLine = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
        StringBuilder lines = new StringBuilder();
        while (lineNumberReader.readLine() != null) {
            count++;
        }
        if (count > limitedHistoryMassage) {
            startLine = count - limitedHistoryMassage;
        }
        String text = "";
        count = 0;
        while ((text = reader.readLine()) != null) {
            if (count >= startLine) {
                lines.append(text).append("\n");
            }
            count++;
        }
        return lines.toString();
    }

}
