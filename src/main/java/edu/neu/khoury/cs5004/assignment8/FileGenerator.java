package edu.neu.khoury.cs5004.assignment8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * Class FileGenerator contains information about a file generator.

 */
public class FileGenerator implements IFileGenerator {

  private List<String> listOfNewFiles;

  /**
   * Constructor that creates a new AFileReader object.
   */
  public FileGenerator() {
    this.listOfNewFiles = new ArrayList<>();
  }

  /**
   * Generate new file as a list.
   *
   * @param templateParser the template parser
   * @param template the template
   * @param csvData the csv data
   * @param headerSet the header set
   * @return the list
   */
  public List<String> generateFile(TemplateParser templateParser,
      String template, List<Map<String, String>> csvData, HashSet<String> headerSet) {
    templateParser.findPlaceholderFrontPointer(headerSet, template);
    for (Map<String, String> row : csvData) {
      StringBuilder buffer = new StringBuilder(template);
      for (int i = templateParser.getToBeReplaced().size() - 1; i >= 0; i--) {
        String typeToBeReplaced = templateParser.getToBeReplaced().get(i).getHeaderType();
        int position = templateParser.getToBeReplaced().get(i).getPlaceholderFrontPointer();
        buffer.replace(position, position + typeToBeReplaced.length(), row.get(typeToBeReplaced));
      }
      listOfNewFiles.add(buffer.toString());
    }

    return listOfNewFiles;
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
    FileGenerator that = (FileGenerator) obj;
    return Objects.equals(listOfNewFiles, that.listOfNewFiles);
  }

  /**
   * Returns hashCode of this object.
   *
   * @return hashCode of this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(listOfNewFiles);
  }

  /**
   * Returns the string representation of this object.
   *
   * @return the string representation of this object
   */
  @Override
  public String toString() {
    return "This is a file generator!\n"
        + "List of new files are as follows:\n" + listOfNewFiles;
  }
}