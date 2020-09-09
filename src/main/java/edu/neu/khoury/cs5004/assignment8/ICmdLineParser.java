package edu.neu.khoury.cs5004.assignment8;

/**
 * Interface for command line parser.
 */
public interface ICmdLineParser {

  /**
   * Method to get input email template argument.
   *
   * @return email template input argument in String
   */
  String getEmailTemplate();

  /**
   * Method to determine if it is to generate emails.
   *
   * @return true or false
   */
  boolean isEmailFlag();

  /**
   * Method to get input letter template argument.
   *
   * @return letter template input argument in String
   */
  String getLetterTemplate();

  /**
   * Method to determine if it is to generate letters.
   *
   * @return true or false
   */
  boolean isLetterFlag();

  /**
   * Method to get input csv file argument.
   *
   * @return csv file input argument in String
   */
  String getCsvFile();

  /**
   * Method to get output directory path argument.
   *
   * @return output directory path argument in String
   */
  String getOutputDir();

  /**
   * Determine if an argument is valid or not from a general perspective. This index of this
   * argument shouldn't exceed the length of input arguments array length. This argument shouldn't
   * be a FLAG.
   *
   * @param args Input arguments as string array
   * @param index location of current argument
   * @return true or false
   */
  boolean isValidInput(String[] args, int index);

  /**
   * Method to parse each piece of argument in the input argument array.
   *
   * @param args arguments as array of Strings
   * @throws IllegalArgumentException if an argument is illegal
   */
  void parse(String[] args) throws IllegalArgumentException;

}
