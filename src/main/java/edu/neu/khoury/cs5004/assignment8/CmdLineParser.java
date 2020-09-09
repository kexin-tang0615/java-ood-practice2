package edu.neu.khoury.cs5004.assignment8;

import java.util.Objects;

/**
 * Class CmdLineParser contains information about a commandline parser.
 */
public class CmdLineParser implements ICmdLineParser {

  private static final String HELP_MSG =
      "Usage:\n"
          + " --email only generate email messages\n"
          + " --email-template <file> accept a filename that holds the\n"
          + "email template.\n"
          + " Required if --email is used\n"
          + " --letter only generate letters\n"
          + " --letter-template <file> accept a filename that holds\n"
          + "the email template.\n"
          + " Required if --letter is used\n"
          + " --output-dir <path> accept the name of a folder, all\n"
          + "output is placed in this folder\n"
          + " --csv-file <path> accept the name of the csv file to\n"
          + "process\n\n"
          + "Examples:\n"
          + " --email --email-template email-template.txt --output-dir emails\n"
          + " --csv-file customer.csv\n"
          + " --letter --letter-template letter-template.txt "
          + "--output-dir letters --csv-file customer.csv\n";

  private String emailTemplate;
  private boolean isEmail;

  private String letterTemplate;
  private boolean isLetter;

  private String csvFile;
  private String outputDir;

  /**
   * Method to get input email template argument.
   *
   * @return emailTemplate as a String
   */
  @Override
  public String getEmailTemplate() {
    return emailTemplate;
  }

  /**
   * Method to determine if it is to generate emails.
   *
   * @return true or false
   */
  @Override
  public boolean isEmailFlag() {
    return isEmail;
  }

  /**
   * Method to get input letter template argument.
   *
   * @return letter template input argument in String
   */
  @Override
  public String getLetterTemplate() {
    return letterTemplate;
  }

  /**
   * Method to determine if it is to generate letters.
   *
   * @return true or false
   */
  @Override
  public boolean isLetterFlag() {
    return isLetter;
  }

  /**
   * Method to get input csv file argument.
   *
   * @return csv file input argument in String
   */
  @Override
  public String getCsvFile() {
    return csvFile;
  }

  /**
   * Method to get output directory path argument.
   *
   * @return output directory path argument in String
   */
  @Override
  public String getOutputDir() {
    return outputDir;
  }

  /**
   * throw IllegalArgException.
   */
  private void throwIllegalArgException(String errMsg) {
    throw new IllegalArgumentException(errMsg + HELP_MSG);
  }

  /**
   * Determine if an argument is valid or not from a general perspective. This index of this
   * argument shouldn't exceed the length of input arguments array length. This argument shouldn't
   * be a FLAG.
   *
   * @param args Input arguments as string array
   * @param index location of current argument
   * @return true or false
   */
  @Override
  public boolean isValidInput(String[] args, int index) {
    if (index >= args.length) {
      return false;
    }
    return Flag.checkFlagType(args[index]) == null;
  }

  /**
   * Method to parse each piece of argument in the input argument array.
   *
   * @param args arguments as array of Strings
   * @throws IllegalArgumentException if an argument is illegal
   */
  @Override
  public void parse(String[] args) throws IllegalArgumentException {
    if (args.length < 1) {
      String errMsg = "Error: illegal arguments.\n";
      throwIllegalArgException(errMsg);
    }

    int index = 0;
    while (index < args.length) {
      String token = args[index];
      Flag flag = Flag.checkFlagType(token);
      if (flag == Flag.EMAIL) {
        isEmail = true;
      } else if (flag == Flag.EMAIL_TEMPLATE) {
        index++;
        if (isValidInput(args, index)) {
          emailTemplate = args[index];
        } else {
          String errMsg = "Error: --email-template is missing.\n";
          throwIllegalArgException(errMsg);
        }
      } else if (flag == Flag.LETTER) {
        isLetter = true;
      } else if (flag == Flag.LETTER_TEMPLATE) {
        index++;
        if (isValidInput(args, index)) {
          letterTemplate = args[index];
        } else {
          String errMsg = "Error: --letter-template is missing.\n";
          throwIllegalArgException(errMsg);
        }
      } else if (flag == Flag.CSV_FILE) {
        index++;
        if (isValidInput(args, index)) {
          csvFile = args[index];
        } else {
          String errMsg = "Error: --csv-file is missing.\n";
          throwIllegalArgException(errMsg);
        }
      } else if (flag == Flag.OUTPUT_DIR) {
        index++;
        if (isValidInput(args, index)) {
          outputDir = args[index];
        } else {
          String errMsg = "Error: --output-dir is missing.\n";
          throwIllegalArgException(errMsg);
        }
      } else {
        String errMsg = "Error: Unrecognized parameter.\n";
        throwIllegalArgException(errMsg);
      }
      index++;
    }

    if (isEmail && emailTemplate == null) {
      String errMsg = "Error:  --email provided but no --email-template was given.\n";
      throwIllegalArgException(errMsg);
    }

    if (isLetter && letterTemplate == null) {
      String errMsg = "Error:  --letter provided but no --letter-template was given.\n";
      throwIllegalArgException(errMsg);
    }

    if (csvFile == null) {
      String errMsg = "Error:  --csv-file is not set.\n";
      throwIllegalArgException(errMsg);
    }

    if (outputDir == null) {
      String errMsg = "Error:  --output-dir is not set.\n";
      throwIllegalArgException(errMsg);
    }
  }

  /**
   * Returns the boolean value whether this equals to the given obj.
   *
   * @return the boolean value whether this equals to the given obj
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    CmdLineParser parser = (CmdLineParser) obj;
    return isEmailFlag() == parser.isEmailFlag()
        && isLetterFlag() == parser.isLetterFlag()
        && Objects.equals(getEmailTemplate(), parser.getEmailTemplate())
        && Objects.equals(getLetterTemplate(), parser.getLetterTemplate())
        && Objects.equals(getCsvFile(), parser.getCsvFile())
        && Objects.equals(getOutputDir(), parser.getOutputDir());
  }

  /**
   * Returns hashCode of this object.
   *
   * @return hashCode of this object
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(getEmailTemplate(), isEmailFlag(), getLetterTemplate(), isLetterFlag(), getCsvFile(),
            getOutputDir());
  }

  /**
   * Returns the string representation of this object.
   *
   * @return the string representation of this object
   */
  @Override
  public String toString() {
    return "This is a commandline parser -- "
        + "CmdLineParser{"
        + "emailTemplate='" + emailTemplate + '\''
        + ", isEmail=" + isEmail
        + ", letterTemplate='" + letterTemplate + '\''
        + ", isLetter=" + isLetter
        + ", csvFile='" + csvFile + '\''
        + ", outputDir='" + outputDir + '\''
        + '}';
  }
}
