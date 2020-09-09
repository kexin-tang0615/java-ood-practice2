package edu.neu.khoury.cs5004.assignment8;

/**
 * Interface for output writer.
 */
public interface IOutputWriter {

  /**
   * Method to write content into mails.txt.
   *
   * @param templateType template file input argument as String
   * @param csvFile csv File input argument as String
   */
  void writeMails(String templateType, String csvFile, boolean isEmail);

}
