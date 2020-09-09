package edu.neu.khoury.cs5004.assignment8;

import java.util.List;

/**
 * The interface Csv file reader.
 */
public interface ICsvFileReader {

  /**
   * Read csv file header, returning as a string.
   *
   * @return the string
   */
  String readCsvFileHeader();

  /**
   * Read csv file content, returning as a list.
   *
   * @return the list
   */
  List<String> readCsvFileContent();

}
