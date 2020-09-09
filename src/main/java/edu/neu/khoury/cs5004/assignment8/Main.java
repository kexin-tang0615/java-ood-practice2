package edu.neu.khoury.cs5004.assignment8;

/**
 * The type Main.
 */
public class Main {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    try {
      CmdLineParser cmdLineParser = new CmdLineParser();
      cmdLineParser.parse(args);

      String csvFile = cmdLineParser.getCsvFile();
      String outDir = cmdLineParser.getOutputDir();
      boolean isEmail = cmdLineParser.isEmailFlag();
      String emailTemplate = cmdLineParser.getEmailTemplate();
      boolean isLetter = cmdLineParser.isLetterFlag();
      String letterTemplate = cmdLineParser.getLetterTemplate();

      OutputWriter outputWriter = new OutputWriter(outDir);
      if (isEmail) {
        System.out.println("Preparing emails...");
        outputWriter.writeMails(emailTemplate, csvFile, true);
      }
      if (isLetter) {
        System.out.println("Preparing letters...");
        outputWriter.writeMails(letterTemplate, csvFile, false);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}