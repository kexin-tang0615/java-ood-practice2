package edu.neu.khoury.cs5004.assignment8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class CsvFileReader contains information about the output of reading a csv file.
 */
public class CsvFileReader extends AFileReader implements ICsvFileReader {

  private static final String MATCH_ALPHABET = ".*[a-zA-Z]+.*";
  private static final Integer HEADER_ROW_NUM = 1;

  /**
   * Constructor that creates a new CsvFileReader object, based upon all of the provided input
   * parameters.
   *
   * @param fileName the file name
   */
  public CsvFileReader(String fileName) {
    super(fileName);
  }

  /**
   * Read csv file header as a String.
   *
   * @return the string
   */
  public String readCsvFileHeader() {
    String header = "";
    try {
      InputStream inputStream = new FileInputStream(getFileName());
      Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
      BufferedReader bufferedReader = new BufferedReader(reader);
      header = bufferedReader.readLine();
      inputStream.close();
      bufferedReader.close();
      reader.close();
    } catch (IOException e) {
      System.err.println("Error reading a file " + this.getFileName());
      e.printStackTrace();
    }
    return header;
  }

  /**
   * Read csv file content as a List of strings.
   *
   * @return the list
   */
  @Override
  public List<String> readCsvFileContent() {
    List<String> csvFileContents = new ArrayList<>();
    try {
      InputStream inputStream = new FileInputStream(getFileName());
      Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
      BufferedReader bufferedReader = new BufferedReader(reader);
      csvFileContents = bufferedReader.lines().skip(HEADER_ROW_NUM)
          .filter(line -> line.trim().matches(MATCH_ALPHABET)).collect(Collectors.toList());
      inputStream.close();
      bufferedReader.close();
      reader.close();
    } catch (IOException e) {
      System.err.println("Error reading file" + getFileName());
      e.printStackTrace();
    }
    return csvFileContents;
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
    return super.toString() + "with reading a csv file: " + getFileName();
  }
}
