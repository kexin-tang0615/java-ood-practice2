package edu.neu.khoury.cs5004.assignment8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Class CsvFileParser contains information about a csv file parser.
 */
public class CsvFileParser implements ICsvFileParser {

  private static final String HEADER_SPLIT = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
  private static final String INFO_SPLIT = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
  private static final String FRONT_PLACEHOLDER = "[[";
  private static final String BACK_PLACEHOLDER = "]]";
  private List<Map<String, String>> customerInfoMap;

  /**
   * Constructor that creates a new CsvFileParser object.
   */
  public CsvFileParser() {
    this.customerInfoMap = new ArrayList<>();
  }

  /**
   * Construct header set.
   *
   * @param header the header
   * @return the hashset
   */
  @Override
  public HashSet<String> constructHeaderSet(String header) {
    HashSet<String> headerSet = new HashSet<>();
    String[] headerArray = header.split(HEADER_SPLIT);
    for (int i = 0; i < headerArray.length; i++) {
      headerSet.add(FRONT_PLACEHOLDER + headerArray[i].substring(1, headerArray[i].length() - 1)
          + BACK_PLACEHOLDER);
    }
    return headerSet;
  }

  /**
   * Process csv file.
   *
   * @param customerInfo the customer info
   * @param header the header
   * @return the list
   */
  @Override
  public List<Map<String, String>> parseCsvFile(List<String> customerInfo, String header) {
    for (String infoInRow : customerInfo) {
      String[] headerArray = header.split(HEADER_SPLIT);
      String[] contentInRow = infoInRow.split(INFO_SPLIT);
      Map<String, String> map = new HashMap<>();
      for (int i = 0; i < headerArray.length; i++) {
        String key = FRONT_PLACEHOLDER + headerArray[i].substring(1, headerArray[i].length() - 1)
            + BACK_PLACEHOLDER;
        String value = contentInRow[i].substring(1, contentInRow[i].length() - 1);
        map.put(key, value);
      }
      customerInfoMap.add(map);

    }
    return this.customerInfoMap;
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
    CsvFileParser that = (CsvFileParser) obj;
    return Objects.equals(customerInfoMap, that.customerInfoMap);
  }

  /**
   * Returns hashCode of this object.
   *
   * @return hashCode of this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(customerInfoMap);
  }

  /**
   * Returns the string representation of this object.
   *
   * @return the string representation of this object
   */
  @Override
  public String toString() {
    return "This is a csv file parser!";
  }
}