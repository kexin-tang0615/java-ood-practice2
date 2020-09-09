package edu.neu.khoury.cs5004.assignment8;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Class OutputWriter contains information about the outputs of new written files.
 */
public class OutputWriter implements IOutputWriter {

  private String outputDir;

  /**
   * Constructor that creates a new OutputWriter object, based upon all of the provided input
   * parameters.
   *
   * @param outputDir the output dir
   */
  public OutputWriter(String outputDir) {
    File directory = new File(outputDir);
    if (directory.exists()) {
      String[] entries = directory.list();
      if (entries != null) {
        boolean res = true;
        for (String fileName : entries) {
          File curFile = new File(directory.getPath(), fileName);
          res = curFile.delete() && res;
        }
        System.out.println("Previous sub files are deleted successfully: " + res);
      }
      System.out.println("Previous directory deleted successfully: " + directory.delete());
    }
    System.out.println("New destination directory made successfully: " + directory.mkdirs());

    this.outputDir = outputDir;
  }

  /**
   * Write to files.
   *
   * @param fileName as a String
   * @param content as a String
   */
  private void writeToFile(String fileName, String content) {
    File file = new File(outputDir + File.separator + fileName + ".txt");
    try {

      OutputStream outputStream = new FileOutputStream(file);
      Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
      BufferedWriter bufferedWriter = new BufferedWriter(writer);
      bufferedWriter.write(content);
      bufferedWriter.close();
      outputStream.close();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method to write content into mails.txt.
   *
   * @param templateType template file input argument as String
   * @param csvFile csv File input argument as String
   */
  @Override
  public void writeMails(String templateType, String csvFile, boolean isEmail) {
    ICsvFileReader fileReader = new CsvFileReader(csvFile);
    ICsvFileParser fileParser = new CsvFileParser();

    String header = fileReader.readCsvFileHeader();
    System.out.println("Reading from input CSV file...");
    List<String> content = fileReader.readCsvFileContent();

    ITemplateFileReader templateReader = new TemplateFileReader(templateType);
    String readTemplate = templateReader.readTemplateFile();

    HashSet<String> headerSet = fileParser.constructHeaderSet(header);

    TemplateParser templateParser = new TemplateParser();

    FileGenerator fileGenerator = new FileGenerator();
    System.out.println("Generating the output...");
    List<String> generatedMails = fileGenerator
        .generateFile(templateParser, readTemplate, fileParser.parseCsvFile(content, header),
            headerSet);

    System.out.println("Writing the output to the destination directory...");
    for (int i = 0; i < generatedMails.size(); i++) {
      String fileName;
      if (isEmail) {
        fileName = "Email" + i;
      } else {
        fileName = "Letter" + i;
      }

      writeToFile(fileName, generatedMails.get(i));
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
    OutputWriter that = (OutputWriter) obj;
    return Objects.equals(outputDir, that.outputDir);
  }

  /**
   * Returns hashCode of this object.
   *
   * @return hashCode of this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(outputDir);
  }

  /**
   * Returns the string representation of this object.
   *
   * @return the string representation of this object
   */
  @Override
  public String toString() {
    return "This is a output writer.\n"
        + "The output dir is " + outputDir + '\''
        + '}';
  }
}