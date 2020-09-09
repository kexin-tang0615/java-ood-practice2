package edu.neu.khoury.cs5004.assignment8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class TemplateParser contains information about a template parser.
 */
public class TemplateParser implements ITemplateParser {

  private static final String MATCHED_CHAR_STRING = "\\[\\[[a-zA-Z\\_]*\\]\\]";

  /**
   * Class HeaderPair contains information about a header pair.
   */
  protected static class HeaderPair {

    private Integer placeholderFrontPointer;
    private String headerType;

    /**
     * Constructor that creates a new HeaderPair object, based upon all of the provided input
     * parameters.
     *
     * @param placeholderFrontPointer the placeholder front pointer
     * @param headerType the header type
     */
    public HeaderPair(Integer placeholderFrontPointer, String headerType) {
      this.placeholderFrontPointer = placeholderFrontPointer;
      this.headerType = headerType;
    }

    /**
     * Returns the string representation of this object.
     *
     * @return the string representation of this object
     */
    @Override
    public String toString() {
      return "HeaderPair{"
          + "placeholderFrontPointer=" + placeholderFrontPointer
          + ", headerType='" + headerType + '\''
          + '}';
    }

    /**
     * Gets placeholder front pointer.
     *
     * @return the placeholder front pointer
     */
    public Integer getPlaceholderFrontPointer() {
      return placeholderFrontPointer;
    }

    /**
     * Gets header type.
     *
     * @return the header type
     */
    public String getHeaderType() {
      return headerType;
    }
  }

  private List<HeaderPair> toBeReplaced;

  /**
   * Constructor that creates a new TemplateParser object.
   */
  public TemplateParser() {
    this.toBeReplaced = new ArrayList<>();
  }

  /**
   * Find placeholder front pointer.
   *
   * @param headerSet the header set
   * @param template the template
   */
  @Override
  public void findPlaceholderFrontPointer(HashSet<String> headerSet, String template) {
    Matcher matcher = Pattern.compile(MATCHED_CHAR_STRING).matcher(template);
    while (matcher.find()) {
      if (headerSet.contains(matcher.group())) {
        HeaderPair headerPair = new HeaderPair(matcher.start(), matcher.group());
        this.toBeReplaced.add(headerPair);
      }
    }
  }

  /**
   * Gets to be replaced.
   *
   * @return the to be replaced
   */
  public List<HeaderPair> getToBeReplaced() {
    return toBeReplaced;
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
    TemplateParser that = (TemplateParser) obj;
    return Objects.equals(getToBeReplaced(), that.getToBeReplaced());
  }

  /**
   * Returns hashCode of this object.
   *
   * @return hashCode of this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(getToBeReplaced());
  }

  /**
   * Returns the string representation of this object.
   *
   * @return the string representation of this object
   */
  @Override
  public String toString() {
    return "This is a template parser.\n"
        + "places to be replaced are as follows:\n" + toBeReplaced;
  }
}
