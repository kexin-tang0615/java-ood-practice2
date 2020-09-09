package edu.neu.khoury.cs5004.assignment8;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileUtils {

  public static String readFromFile(String filePath) throws Exception {
    StringBuilder contents = new StringBuilder();
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String line = reader.readLine();
    while (line != null) {
      contents.append(line).append("\n");
      line = reader.readLine();
    }
    return contents.toString();
  }
}
