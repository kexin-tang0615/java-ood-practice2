package edu.neu.khoury.cs5004.assignment8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * Class TemplateFileReader contains information about the output of reading a template.
 */
public class TemplateFileReader extends AFileReader implements ITemplateFileReader {

  private static final String LINE_SEPARATOR = System.getProperty("line.separator");

  /**
   * Constructor that creates a new TemplateFileReader object, based upon all of the provided input
   * parameters.
   *
   * @param fileName the file name
   */
  public TemplateFileReader(String fileName) {
    super(fileName);
  }

  /**
   * Read template file string.
   *
   * @return the string
   */

  public String readTemplateFile() {
    StringBuilder contents = new StringBuilder();
    try {
      InputStream inputStream = new FileInputStream(getFileName());
      Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line = bufferedReader.readLine();
      while (line != null) {
        contents.append(line).append(LINE_SEPARATOR);
        line = bufferedReader.readLine();
      }
      inputStream.close();
      reader.close();
      bufferedReader.close();
    } catch (IOException e) {
      System.err.println("Error reading a file " + getFileName());
      e.printStackTrace();
    }
    return contents.toString();
  }

  /**
   * Returns the boolean value whether this equals to the given obj.
   *
   * @return the boolean value whether this equals to the given obj
   */
  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  /**
   * Returns hashCode of this object.
   *
   * @return hashCode of this object
   */
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * Returns the string representation of this object.
   *
   * @return the string representation of this object
   */
  @Override
  public String toString() {
    return super.toString() + "with reading a template: " + getFileName();
  }
}