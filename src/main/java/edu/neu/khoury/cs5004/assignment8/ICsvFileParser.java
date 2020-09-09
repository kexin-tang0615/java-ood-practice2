package edu.neu.khoury.cs5004.assignment8;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * The interface Csv file parser.
 */
public interface ICsvFileParser {

  /**
   * Construct header set as a hash set.
   *
   * @param header the header
   * @return the hash set
   */
  HashSet<String> constructHeaderSet(String header);

  /**
   * Process csv file as a list.
   *
   * @param customerInfo the customer info
   * @param header the header
   * @return the list
   */
  List<Map<String, String>> parseCsvFile(List<String> customerInfo, String header);

}
