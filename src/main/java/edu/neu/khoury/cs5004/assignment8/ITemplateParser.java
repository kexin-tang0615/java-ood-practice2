package edu.neu.khoury.cs5004.assignment8;

import java.util.HashSet;

/**
 * The interface Template parser.
 */
public interface ITemplateParser {


  /**
   * Find placeholder front pointer.
   *
   * @param headerSet the header set
   * @param template the template
   */
  void findPlaceholderFrontPointer(HashSet<String> headerSet, String template);

}
