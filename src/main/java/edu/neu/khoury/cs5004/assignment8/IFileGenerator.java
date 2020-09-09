package edu.neu.khoury.cs5004.assignment8;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * The interface File generator.
 */
public interface IFileGenerator {

  /**
   * Generate new files, returning as a list.
   *
   * @param templateParser the template parser
   * @param template the template
   * @param csvData the csv data
   * @param headerSet the header set
   * @return the list
   */
  List<String> generateFile(TemplateParser templateParser, String template,
      List<Map<String, String>> csvData, HashSet<String> headerSet);
}
